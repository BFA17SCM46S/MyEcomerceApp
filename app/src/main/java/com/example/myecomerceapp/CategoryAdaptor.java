package com.example.myecomerceapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myecomerceapp.Model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.MyViewHolder> {
    List<Category> list;
    Context context;
    private static final String TAG  = "tag";

    private OnItemClickListner mlistner;
   // RequestQueue requestQueue;


    public interface OnItemClickListner{
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        mlistner = listner;
    }


    public CategoryAdaptor(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_list_items,
                viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
       final Category dataModel = list.get(position);
        myViewHolder.id.setText(dataModel.getCid());
        myViewHolder.name.setText(dataModel.getCname());
        myViewHolder.deccription.setText(dataModel.getCdescription());
        Picasso.with(context).load(dataModel.getCimageyrl()).fit().centerInside().into(myViewHolder.image);




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
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,SubCategoryActivity.class);
                Log.e("bin", "CategoryID"+dataModel.getCid() );
                intent.putExtra("CategoryID",dataModel.getCid());
                //intent.putExtra("Id2",dataModel.getCid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id,name,deccription;
        ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.cid);
            name = itemView.findViewById(R.id.cname);
            deccription = itemView.findViewById(R.id.cdescription);
            image = itemView.findViewById(R.id.cimageurl);


        }
    }
}
