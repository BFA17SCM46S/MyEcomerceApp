package com.example.myecomerceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myecomerceapp.Model.Order;
import com.example.myecomerceapp.Model.Products;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.myecomerceapp.ProductListActivity.*;

public class OrdersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    List<Order> list;
    RecyclerView.LayoutManager layoutManager;
    private String urlstring = "http://rjtmobile.com/aamir/e-commerce/android-app/order.php";
    TextView textView;
    Button payment;
    TextView tatalprice;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        textView = findViewById(R.id.tv);


        SharedPreferences sharedPreferences = getSharedPreferences("userPre", MODE_PRIVATE);
        String productID = sharedPreferences.getString("pid", "");
        String productname = sharedPreferences.getString("pname", "");
        String discription = sharedPreferences.getString("discription", "");
        payment = findViewById(R.id.payment);
        tatalprice = findViewById(R.id.toatlprice);
        price = findViewById(R.id.price);



        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdersActivity.this,PaymentActivity.class);
                startActivity(intent);


            }
        });
        //take the handle of totalprice textview and get the total

        for (int i = 0; i < 10; i++) {
               String itemnumber = list1.get(i).getNumber();

                String text = textView.getText().toString();
                try {
                    data = jsonArray.getJSONObject(i);


//                    textView.setText(data.getString("id") + "  " +
//                            data.getString("pname")+"  "+data.getString("prize"));

                    if (Integer.parseInt(itemnumber)>0) {
                        textView.setText(text +
                                jsonArray.getJSONObject(i).getString("id") + "    " +
                                jsonArray.getJSONObject(i).getString("pname") + "    " +
                                jsonArray.getJSONObject(i).getString("prize") + "             " +
                                itemnumber + "\n" + "\n");

                    }
                        price.setText(
                                String.valueOf(
                                        Integer.parseInt(list1.get(0).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(0).getString("prize"))+
                                        Integer.parseInt(list1.get(1).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(1).getString("prize"))+
                                        Integer.parseInt(list1.get(2).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(2).getString("prize"))+
                                        Integer.parseInt(list1.get(3).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(3).getString("prize"))+
                                        Integer.parseInt(list1.get(4).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(4).getString("prize"))+
                                        Integer.parseInt(list1.get(5).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(6).getString("prize"))+
                                        Integer.parseInt(list1.get(6).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(6).getString("prize"))+
                                        Integer.parseInt(list1.get(7).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(7).getString("prize"))+
                                        Integer.parseInt(list1.get(8).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(8).getString("prize"))+
                                        Integer.parseInt(list1.get(9).getNumber()) *
                                        Integer.parseInt(jsonArray.getJSONObject(9).getString("prize"))


                                )
                        );


                } catch (JSONException e) {
                    e.printStackTrace();
                }





        }

    }
}