package com.example.myecomerceapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myecomerceapp.authentication.ui.LoginActivity;
import com.example.myecomerceapp.authentication.ui.RegisterActivity;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {


    private Button joinbotton, loginbotton;
    private ProgressDialog loadingbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        joinbotton = findViewById(R.id.Join_now1);
        loginbotton = findViewById(R.id.Join_now);
        loadingbar = new ProgressDialog(this);




        loginbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        joinbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent2);

            }
        });
//
//

        }




}
