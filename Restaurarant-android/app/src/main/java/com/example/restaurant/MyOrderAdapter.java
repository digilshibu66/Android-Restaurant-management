package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyOrderAdapter extends BaseAdapter {

    Context context;
    ArrayList<Model_Order> arraylistorder;
    Model_Order modelOrder;


    public MyOrderAdapter(Context context, ArrayList<Model_Order> arraylistorder) {
        this.context = context;
        this.arraylistorder = arraylistorder;

    }

    @Override
    public int getCount() {
        return arraylistorder.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylistorder.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView=LayoutInflater.from(parent.getContext()).inflate(R.layout.my_custom_orderlist,null,true);

        TextView orderlogin_id=rowView.findViewById(R.id.orderlogin_id);
        TextView orderitem_id=rowView.findViewById(R.id.orderitem_id);
        TextView orderorder_id=rowView.findViewById(R.id.orderorder_id);
        TextView ordertable_id=rowView.findViewById(R.id.ordertable_id);
        TextView ordertable_no=rowView.findViewById(R.id.ordertable_no);
        TextView ordered_dishname=rowView.findViewById(R.id.ordered_dishname);
        TextView ordered_price=rowView.findViewById(R.id.ordered_price);
        TextView ordered_quantity=rowView.findViewById(R.id.ordered_quantity);

        modelOrder=arraylistorder.get(position);

        orderlogin_id.setText(modelOrder.getLogin_id());
        orderitem_id.setText(modelOrder.getItem_id());
        orderorder_id.setText(modelOrder.getOrder_id());
        ordertable_id.setText(modelOrder.getTable_id());
        ordertable_no.setText("Table No :"+modelOrder.getTable_no());
        ordered_dishname.setText(modelOrder.getOrdered_item());
        ordered_price.setText(modelOrder.getOrdered_price());
        ordered_quantity.setText(modelOrder.getOrdered_quantity());

        return rowView;
    }
}
