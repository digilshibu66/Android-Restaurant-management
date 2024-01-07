package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeliveryOrderAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    ArrayList<Model_Order> arraylistorder;
    Model_Order modelOrder;
    String id;
    onClickLayout clickLayout;


    public DeliveryOrderAdapter(Context context, ArrayList<Model_Order> arraylistorder) {
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

        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_delivery_orderlist, null, true);

        TextView orderlogin_id = rowView.findViewById(R.id.dorderlogin_id);
        TextView orderitem_id = rowView.findViewById(R.id.dorderitem_id);
        TextView orderorder_id = rowView.findViewById(R.id.dorderorder_id);
        TextView ordertable_id = rowView.findViewById(R.id.dordertable_id);
        TextView ordertable_no = rowView.findViewById(R.id.dordertable_no);
        TextView ordered_dishname = rowView.findViewById(R.id.dordered_dishname);
        TextView ordered_price = rowView.findViewById(R.id.dordered_price);
        TextView ordered_quantity = rowView.findViewById(R.id.dordered_quantity);
        LinearLayout btnLayout = rowView.findViewById(R.id.btn_layout);
        Button complete=rowView.findViewById(R.id.complete);


        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        modelOrder = arraylistorder.get(position);

        orderlogin_id.setText(modelOrder.getLogin_id());
        orderitem_id.setText(modelOrder.getItem_id());
        orderorder_id.setText(modelOrder.getOrder_id());
        ordertable_id.setText(modelOrder.getTable_id());
        ordertable_no.setText(modelOrder.getTable_no());
        ordered_dishname.setText(modelOrder.getOrdered_item());
        ordered_price.setText(modelOrder.getOrdered_price());
        ordered_quantity.setText(modelOrder.getOrdered_quantity());

        btnLayout.setOnClickListener(this::onClick);

        return rowView;
    }
    public void clicked(onClickLayout layout){
        this.clickLayout = layout;
    }

    @Override
    public void onClick(View v) {
        if(clickLayout!=null)clickLayout.clicked(v);
    }

    interface onClickLayout{
        void clicked(View view);
    }
}