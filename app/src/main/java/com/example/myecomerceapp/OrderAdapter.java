package com.example.myecomerceapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myecomerceapp.Model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<Order> list;
    Context context;

    public OrderAdapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Order order = list.get(position);
        viewHolder.orderid.setText(order.getOrderid());
        viewHolder.orderstatus.setText(order.getOrderstatus());
        viewHolder.name.setText(order.getName());
        viewHolder.billingadd.setText(order.getBillingadd());
        viewHolder.deliveryadd.setText(order.getDeliveryadd());
        viewHolder.mobile.setText(order.getMobile());
        viewHolder.email.setText(order.getEmail());
        viewHolder.itemid.setText(order.getItemid());
        viewHolder.itemname.setText(order.getItemname());
        viewHolder.itemquantity.setText(order.getItemquantity());
        viewHolder.totalprice.setText(order.getTotalprice());
        viewHolder.paidprice.setText(order.getPaidprice());
        viewHolder.placedon.setText(order.getPlacedon());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderid, orderstatus, name, billingadd, deliveryadd, mobile, email, itemid, itemname, itemquantity, totalprice, paidprice, placedon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            orderid = itemView.findViewById(R.id.orderid);
            orderstatus = itemView.findViewById(R.id.orderstatus);
            name = itemView.findViewById(R.id.name);
            billingadd = itemView.findViewById(R.id.billingadd);
            deliveryadd = itemView.findViewById(R.id.deleveryadd);
            mobile = itemView.findViewById(R.id.mobile);
            email = itemView.findViewById(R.id.email);
            itemid = itemView.findViewById(R.id.itemid);
            itemname = itemView.findViewById(R.id.itemname);
            itemquantity = itemView.findViewById(R.id.itemquantity);
            totalprice = itemView.findViewById(R.id.totalprice);
            paidprice = itemView.findViewById(R.id.paidprice);
            placedon = itemView.findViewById(R.id.placedone);

        }
    }
}
