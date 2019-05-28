package com.example.myecomerceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myecomerceapp.Model.Category;
import com.example.myecomerceapp.authentication.ui.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CategoryAdaptor.OnItemClickListner {

    private static final String TAG = HomePageActivity.class.getSimpleName();
    private String urlstring = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php";
    RecyclerView recyclerView;
    List<Category> list;
    CategoryAdaptor categoryAdaptor;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //categoryAdaptor.setOnItemClickListner(HomePageActivity.this);
        list = new ArrayList<>();
       // list = new ArrayList<>();
//        list = new ArrayList<>();
//        categoryAdaptor = new CategoryAdaptor(list,getApplicationContext());
//        recyclerView.setAdapter(categoryAdaptor);
//        recyclerView.setHasFixedSize(true);


        SharedPreferences pref = getSharedPreferences("userPre",MODE_PRIVATE);
        String user_id = pref.getString("id","");
        String api_key = pref.getString("appapikey","");

        Log.e(TAG, "API key "+api_key);
        Log.e(TAG, "onCreate: "+user_id);
        getJsonData(user_id,api_key);
        
    }

    private void getJsonData(final String user_id, final String api_key ) {
        StringRequest getCategoryRequest = new StringRequest(Request.Method.POST, urlstring, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("category");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String cId = jsonArray.getJSONObject(i).getString("cid");
                        String cName = jsonArray.getJSONObject(i).getString("cname");
                        String cDescription = jsonArray.getJSONObject(i).getString("cdiscription");
                       String cImageURL = jsonArray.getJSONObject(i).getString("cimagerl");
//
                        SharedPreferences sharedPreferences = getSharedPreferences("userPre",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("cid",cId);
                        editor.apply();

                        list.add(new Category(cId, cName, cDescription,cImageURL));
                    }

                    categoryAdaptor = new CategoryAdaptor(list, getApplicationContext());
                    recyclerView.setAdapter(categoryAdaptor);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "Couldn't fetch category " + e.getMessage());
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("api_key", api_key);
                params.put("user_id", user_id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getCategoryRequest);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Toast.makeText(this, "it is good", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_update) {
            Intent intent = new Intent(HomePageActivity.this,UpdateProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_resetpassword) {
            Intent intent = new Intent(HomePageActivity.this,ResetPasswordActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_your_account) {

        } else if (id == R.id.nav_order) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}
