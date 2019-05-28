package com.example.myecomerceapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.myecomerceapp.Model.Category;
import com.example.myecomerceapp.Model.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductlistAdapter  extends RecyclerView.Adapter<ProductlistAdapter.MyViewHolder> {
    List<Products> list;
    Context context;
    RequestQueue requestQueue;
    public static int number1;
    Products products = null;




    public ProductlistAdapter(List<Products> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductlistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list,
                viewGroup, false);
        return new ProductlistAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductlistAdapter.MyViewHolder myViewHolder, int position) {

        Products products = list.get(position);


        myViewHolder.pid.setText(products.getId());
        myViewHolder.pname.setText(products.getPname());
        myViewHolder.pquantity.setText(products.getQuantity());
        myViewHolder.pprice.setText(products.getPrize());
        myViewHolder.pdiscrition.setText(products.getDiscription());
        Picasso.with(context).load(products.getImage()).fit().centerInside().into(myViewHolder.piamge);


    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pid, pname, pquantity, pprice, pdiscrition,number;
        ImageView piamge;
        Button plus,minus;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pid = itemView.findViewById(R.id.pid);
            pname = itemView.findViewById(R.id.pname);
            pquantity = itemView.findViewById(R.id.pquantity);
            pprice = itemView.findViewById(R.id.pprice);
            pdiscrition = itemView.findViewById(R.id.pdiscription);
            piamge = itemView.findViewById(R.id.pimage);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            number= itemView.findViewById(R.id.number);


            plus.setOnClickListener(this);
            minus.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == plus.getId()){

                TextView tv= itemView.findViewById(R.id.number);
                int number = Integer.parseInt(tv.getText().toString())+1;
                tv.setText(String.valueOf(number));
                ProductListActivity.list1.get(getAdapterPosition()).setNumber(String.valueOf(number));


            }else if (v.getId() == minus.getId()){

                TextView tv= itemView.findViewById(R.id.number);
                int number = Integer.parseInt(tv.getText().toString())-1;
                tv.setText(String.valueOf(number));
                ProductListActivity.list1.get(getAdapterPosition()).setNumber(String.valueOf(number));



            }

        }




    }




}