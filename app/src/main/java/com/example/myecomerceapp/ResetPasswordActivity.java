package com.example.myecomerceapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myecomerceapp.Model.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordActivity extends AppCompatActivity {
    private static final String TAG = ResetPasswordActivity.class.getSimpleName();
    private String urlstring = "http://rjtmobile.com/aamir/e-commerce/android-app/";
    EditText mobile, password, newpassword;
    Button reset;
    String usermobile;
    String userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mobile = findViewById(R.id.phonenumber);
        password = findViewById(R.id.currentpassword);
        newpassword = findViewById(R.id.new_password);
        reset = findViewById(R.id.submitreset);


        SharedPreferences pref = getSharedPreferences("userPre", MODE_PRIVATE);


         usermobile = pref.getString("usermobile", "");
         userpassword = pref.getString("userpassword", "");

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitreset();
            }
        });

    }

    private void submitreset() {
        final String phone = mobile.getText().toString();
        final String currentpassword = password.getText().toString();
        final String newpasswordd = newpassword.getText().toString();

        if (TextUtils.isEmpty(phone)&&TextUtils.isEmpty(currentpassword)&&TextUtils.isEmpty(newpasswordd) ) {
            Toast.makeText(this, "please type the information", Toast.LENGTH_SHORT).show();

        }else {
            StringRequest getCategoryRequest = new StringRequest(Request.Method.POST, urlstring, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String string = jsonObject.getString("msg");
                        if (string.equals("password reset successfully")){
                            Toast.makeText(ResetPasswordActivity.this, "reset successful", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(ResetPasswordActivity.this, "reset again", Toast.LENGTH_SHORT).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();

                        Log.d(TAG, "no response " + e.getMessage());

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> map = new HashMap<>();
                    map.put("mobile", usermobile);
                    map.put("password", userpassword);
                    map.put("newpassword", newpassword.getText().toString());


                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(getCategoryRequest);


        }
    }
}