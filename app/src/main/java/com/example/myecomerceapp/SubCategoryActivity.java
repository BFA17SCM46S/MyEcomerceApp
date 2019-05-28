package com.example.myecomerceapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myecomerceapp.Model.Category;
import com.example.myecomerceapp.Model.SubCategoty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubCategoryActivity extends AppCompatActivity {

    private static final String TAG = SubCategoryActivity.class.getSimpleName();
    RecyclerView recyclerView;
    List<SubCategoty> list;
    RecyclerView.LayoutManager layoutManager;
    SubCategoryAdapter subCategoryAdapter;
    private String urlstring = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php";
    String categoryID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        recyclerView = findViewById(R.id.subCatoryRecyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();


        SharedPreferences pref = getSharedPreferences("userPre", MODE_PRIVATE);
        categoryID = getIntent().getStringExtra("CategoryID");
        Log.e("bin", "categoryID "+categoryID);
        String user_id = pref.getString("id", "");
        String api_key = pref.getString("appapikey", "");
        Log.e(TAG, "API key " + api_key);
        Log.e(TAG, "userID " + user_id);
        Log.e(TAG, "CategoryID: " + categoryID);


        getSubCategory(categoryID, user_id, api_key);
    }


    private void getSubCategory(final String CID, final String user_id, final String api_key) {

                    StringRequest getCategoryRequest = new StringRequest(Request.Method.POST, urlstring, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("subcategory");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Log.e("response", "onResponse: " + response.toString());
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    String SCID = data.getString("scid");
                                    String SCName = data.getString("scname");
                                    String SCDescription = data.getString("scdiscription");
                                    String SCDImage = data.getString("scimageurl");

                                    SharedPreferences sharedPreferences = getSharedPreferences("userPre",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("scid",SCID);
                                    editor.apply();

                                    //String cImageURL = jsonArray.getJSONObject(i).getString("cimagerl");
                                    list.add(new SubCategoty(SCID, SCName, SCDescription,SCDImage));
                                }

                                subCategoryAdapter = new SubCategoryAdapter(list, getApplicationContext(),categoryID);
                                recyclerView.setAdapter(subCategoryAdapter);


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

                            map.put("api_key", api_key);
                            map.put("user_id", user_id);
                            map.put("Id", CID);
                            return map;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(getCategoryRequest);

            }
        }


