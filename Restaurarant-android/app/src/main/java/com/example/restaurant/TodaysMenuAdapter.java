package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TodaysMenuAdapter extends BaseAdapter {

    Context context;
    ArrayList<TodaysMenuModel> arraylistorder;
    TodaysMenuModel modelOrder;


    public TodaysMenuAdapter(Context context, ArrayList<TodaysMenuModel> arraylistorder) {
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

        View rowView= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_todays_menulist,null,true);

      TextView  item_id=rowView.findViewById(R.id.item_id);
        TextView  dishname=rowView.findViewById(R.id.dishname);
        TextView  category=rowView.findViewById(R.id.category);
        TextView description=rowView.findViewById(R.id.description);
        TextView price=rowView.findViewById(R.id.price);


        modelOrder=arraylistorder.get(position);

        item_id.setText(arraylistorder.get(position).getItem_id());
        dishname.setText(arraylistorder.get(position).getItem_name());
        category.setText(arraylistorder.get(position).getItem_type());
        description.setText(arraylistorder.get(position).getItem_description());
        price.setText(arraylistorder.get(position).getItem_rate());

        return rowView;
    }
}
