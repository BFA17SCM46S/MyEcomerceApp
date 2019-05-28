package com.example.myecomerceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myecomerceapp.Model.Products;
import com.example.myecomerceapp.Model.SubCategoty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListActivity extends AppCompatActivity {

    private static final String TAG =  ProductListActivity.class.getSimpleName();
    RecyclerView recyclerView;
    ProductlistAdapter productlistAdapter;
    public static List<Products> list;
    public static List<Products> list1;
    RecyclerView.LayoutManager layoutManager;
    public static JSONArray jsonArray;
    public static JSONObject data;

    Button button;
    private String urlstring = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        recyclerView = findViewById(R.id.ProListrecyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        list1 = getmodel();
        button = findViewById(R.id.addtoCart);


        SharedPreferences pref = getSharedPreferences("userPre", MODE_PRIVATE);
        //String CID = pref.getString("cid", "");
        String CID = getIntent().getStringExtra("CID");
        Log.e("bin", "CategoryID"+CID);
        String SCID = getIntent().getStringExtra("SId");
        Log.e("bin", "SubCategoryID "+SCID);
        String user_id = pref.getString("id", "");
        String api_key = pref.getString("appapikey", "");
        Log.e(TAG, "API key " + api_key);
        Log.e(TAG, " SCID" + SCID);
        Log.e(TAG, "userID " + user_id);
        Log.e(TAG, "CategoryIDcaon: " + CID);

        getProdctList(CID,SCID,api_key,user_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this,OrdersActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Products> getmodel() {
        ArrayList<Products> list2 = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Products products = new Products();
            products.setNumber("0");
            list2.add(products);
        }
        return list2;
    }

    private void getProdctList(final String CID, final String SCID,final String api_key, final String user_id) {
        StringRequest getCategoryRequest = new StringRequest(Request.Method.POST, urlstring, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.e("response", "onResponse: " + response.toString());
                        data = jsonArray.getJSONObject(i);
                        String pid = data.getString("id");
                        String pname = data.getString("pname");
                        String quantity = data.getString("quantity");
                        String price = data.getString("prize");
                        String discription = data.getString("discription");
                        String pimage = data.getString("image");
                        SharedPreferences sharedPreferences = getSharedPreferences("userPre",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("SId",SCID);
                        editor.putString("pname",pname);
                        editor.putString("discription",discription);
                        editor.putString("pid",pid);

                        //String cImageURL = jsonArray.getJSONObject(i).getString("cimagerl");
                        list.add(new Products(pid, pname, quantity,price,discription,pimage));
                    }
                    productlistAdapter = new ProductlistAdapter(list, getApplicationContext());
                    recyclerView.setAdapter(productlistAdapter);
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
                Map<String, String> map = new HashMap<>();
                map.put("cid", CID);
                map.put("scid", SCID);
                map.put("api_key", api_key);
                map.put("user_id", user_id);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getCategoryRequest);

    }


}