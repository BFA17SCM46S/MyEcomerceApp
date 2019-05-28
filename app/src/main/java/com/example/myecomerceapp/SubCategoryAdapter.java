package com.example.myecomerceapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.myecomerceapp.Model.Category;
import com.example.myecomerceapp.Model.Products;
import com.example.myecomerceapp.Model.SubCategoty;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {
    List<SubCategoty> list;
    Context context;
    RequestQueue requestQueue;
    private static SharedPreferences prefs;
    String categoryID;





    public SubCategoryAdapter(List<SubCategoty> list, Context context,String categoryID) {
        this.list = list;
        this.context = context;
        this.categoryID = categoryID;

    }

    @NonNull
    @Override
    public SubCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        prefs = context.getSharedPreferences("userPre",Context.MODE_PRIVATE);

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_category_list,
                viewGroup,false);


        return new SubCategoryAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
       final SubCategoty subCategoty= list.get(position);
        myViewHolder.id.setText(subCategoty.getScid());
        myViewHolder.name.setText(subCategoty.getScname());
        myViewHolder.deccription.setText(subCategoty.getScdiscription());
        Picasso.with(context).load(subCategoty.getScimageurl()).fit().centerInside().into(myViewHolder.image);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context,ProductListActivity.class);
                Log.e("bin", "categoryID: "+categoryID);
                intent.putExtra("CID",categoryID);
                intent.putExtra("SId",subCategoty.getScid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }


//
//        ImageRequest imageRequest = new ImageRequest(dataModel.getCimageyrl(), new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//
//            }
//        }, 350, 350, null, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("image error", error.toString());
//            }
//        });
//
//
//
//        requestQueue = Volley.newRequestQueue(myViewHolder.itemView.getContext());
//        requestQueue.add(imageRequest);




    @Override
    public int getItemCount() {
        return list.size();
    }


class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id,name,deccription;
        ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.scid);
            name = itemView.findViewById(R.id.scname);
            deccription = itemView.findViewById(R.id.scdiscription);
            image = itemView.findViewById(R.id.scimageurl);

        }
    }
}
