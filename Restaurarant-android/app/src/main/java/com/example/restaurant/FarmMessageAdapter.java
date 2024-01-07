package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FarmMessageAdapter extends BaseAdapter {

    Context context;
    ArrayList<FarmMessageModel> arraylistfarmmssg;
    FarmMessageModel messageModel;


    public FarmMessageAdapter(Context context, ArrayList<FarmMessageModel> arraylistfarmmssg) {
        this.context = context;
        this.arraylistfarmmssg = arraylistfarmmssg;

    }

    @Override
    public int getCount() {
        return arraylistfarmmssg.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylistfarmmssg.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_farm_message,null,true);

        TextView txt_farm_regid=rowView.findViewById(R.id.txt_farm_regid);
        TextView txt_loginid=rowView.findViewById(R.id.txt_loginid);
        TextView txt_message=rowView.findViewById(R.id.txt_message);
        TextView txt_reply=rowView.findViewById(R.id.txt_reply);

        txt_farm_regid.setText(arraylistfarmmssg.get(position).getFarm_reg_id());
        txt_loginid.setText(arraylistfarmmssg.get(position).getLogin_id());
        txt_message.setText("Requested Message :"+arraylistfarmmssg.get(position).getMessage());
        txt_reply.setText("Reply :"+arraylistfarmmssg.get(position).getReply());

        return rowView;
    }
}
