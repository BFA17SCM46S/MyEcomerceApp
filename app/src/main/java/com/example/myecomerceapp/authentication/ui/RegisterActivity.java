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
import com.example.myecomerceapp.util.Constant;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button registerButton;
    private EditText fname, lname, address,password,email,mobile;
    private ProgressDialog loading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateAccount();
            }
        });

    }

    private void init() {
        registerButton = findViewById(R.id.register_button);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
    }
    /*
    registration the user
     */
    private void CreateAccount() {
        final String firstname = fname.getText().toString();
        final String lasname = lname.getText().toString();
        final String useraddress = address.getText().toString();
        final String userpassword = password.getText().toString();
        final String useremail = email.getText().toString();
        final String usermobile = mobile.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("userPre",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("usermobile",usermobile);
        editor.putString("userpassword",userpassword);
        editor.apply();

       // loading = new ProgressDialog(this);
        if (TextUtils.isEmpty(firstname)){
            Toast.makeText(this, "write your fname", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(lasname)){
            Toast.makeText(this, "write your lphone", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(useraddress)){
            Toast.makeText(this, "write your address", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(userpassword)){
            Toast.makeText(this, "write your password", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(useremail)){
            Toast.makeText(this, "write your email", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(usermobile)){
            Toast.makeText(this, "write your mobile", Toast.LENGTH_SHORT).show();
        } else {

//            loading.setTitle("ceate account");
//            loading.setMessage(" wait, we are checking the users");
//            loading.setCanceledOnTouchOutside(false);
//            loading.show();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse:+"+response.toString());

                            if (response.equals("successfully registered")) {
                                Log.e(TAG, "onResponse:"+response.toString() );
                                Toast.makeText(RegisterActivity.this, "register successful",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent  = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(RegisterActivity.this, "Mobile number already exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "error occred: " + error.getMessage());
                    Toast.makeText(RegisterActivity.this, "Register Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

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
                    params.put("password",userpassword);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

    }


//
//
//    }
}
