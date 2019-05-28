package com.example.myecomerceapp.authentication.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myecomerceapp.HomePageActivity;
import com.example.myecomerceapp.R;
import com.rey.material.widget.CheckBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    EditText phonenumber,password;
    Button loginButton;
    private ProgressDialog loadingbar;
    private static String URL_LOGIN = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php";

    private static final String TAG = "login";
    private CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phonenumber = findViewById(R.id.login_phoneNumber);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.Login_button);
        loadingbar = new ProgressDialog(this);
        checkBox = findViewById(R.id.remember);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();

            }
        });
    }

    private void LoginUser() {
        String phone = phonenumber.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "please write your phone", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "please write your password", Toast.LENGTH_SHORT).show();
        }else {
            loadingbar.setTitle("login account");
            loadingbar.setMessage(" wait, we are checking the users");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();
            
            AllowAccess(phone,pass);
        }


    }

    private void AllowAccess(final String phone, final String pass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response"+response);
                loadingbar.dismiss();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.e(TAG, "onResponse: "+jsonArray);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String msg = jsonArray.getJSONObject(i).getString("msg");
                        if (msg.equals("success")){



                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String firstname = object.getString("firstname");
                            String lastname = object.getString("lastname");
                            String email = object.getString("email");
                            String mobile = object.getString("mobile");
                            String appapikey = object.getString("appapikey ");


                            Toast.makeText(LoginActivity.this, "success login"+id+" "+email,
                                    Toast.LENGTH_SHORT).show();



                            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                            startActivity(intent);

                            SharedPreferences sharedPreferences = getSharedPreferences("userPre",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id",id);
                            editor.putString("appapikey",appapikey);
                            editor.commit();



//                            Toast.makeText(LoginActivity.this, "success login"+id+" "+email,
//                                    Toast.LENGTH_SHORT).show();
//
//                            loadingbar.dismiss();
//
//                            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
//                            startActivity(intent);
                        }else {

                            Toast.makeText(LoginActivity.this, "login error", Toast.LENGTH_SHORT).show();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingbar.dismiss();
                Toast.makeText(LoginActivity.this, "Error"+error.toString(),
                        Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("mobile",phone);
                map.put("password",pass);
                return map;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
