package com.example.myecomerceapp;

import android.content.Intent;
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
import com.example.myecomerceapp.authentication.ui.RegisterActivity;
import com.example.myecomerceapp.util.Constant;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfileActivity extends AppCompatActivity {
    private static final String TAG = UpdateProfileActivity.class.getSimpleName();
    EditText fname,lname,address,email,mobile;
    Button update;
    private static String URL_UPDATE = "http://rjtmobile.com/aamir/e-commerce/android-app/edit_profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        fname = findViewById(R.id.Efname);
        lname = findViewById(R.id.Elname);
        address = findViewById(R.id.Eaddress);
        email = findViewById(R.id.Eemail);
        mobile = findViewById(R.id.Emobile);
        update = findViewById(R.id.updatebutton);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        final String firstname = fname.getText().toString();
        final String lasname = lname.getText().toString();
        final String useraddress = address.getText().toString();

        final String useremail = email.getText().toString();
        final String usermobile = mobile.getText().toString();


        if (TextUtils.isEmpty(firstname)) {
            Toast.makeText(this, "write your fname", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(lasname)) {
            Toast.makeText(this, "write your lphone", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(useraddress)) {
            Toast.makeText(this, "write your address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(useremail)) {
            Toast.makeText(this, "write your email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(usermobile)) {
            Toast.makeText(this, "write your mobile", Toast.LENGTH_SHORT).show();
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse:+"+response.toString());

                            if (response.equals("successfully updated")) {
                                Log.e(TAG, "onResponse:"+response.toString() );
                                Toast.makeText(UpdateProfileActivity.this, "updated successful",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent  = new Intent(UpdateProfileActivity.this, HomePageActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(UpdateProfileActivity.this, "Mobile no not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "error occred: " + error.getMessage());
                    Toast.makeText(UpdateProfileActivity.this, "update Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("fname", firstname);
                    params.put("lname", lasname );
                    params.put("address", useraddress);
                    params.put("email", useremail);
                    params.put("mobile", usermobile);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
    }
}
