package com.example.restaurant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingAdapter extends BaseAdapter {

     Context context;
   ArrayList<ParkingModel> arraylistparking;
   ParkingModel model;


   public ParkingAdapter(Context context,ArrayList<ParkingModel> arraylistparking)
   {
       this.context=context;
       this.arraylistparking=arraylistparking;
   }

    @Override
    public int getCount() {
        return arraylistparking.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylistparking.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View rowView=LayoutInflater.from(parent.getContext()).inflate(R.layout.my_custom_parking,null,true);


        TextView txt_parkingid=rowView.findViewById(R.id.txt_parkingid);
        TextView txt_secloginid=rowView.findViewById(R.id.txt_secloginid);
        TextView txt_secdate=rowView.findViewById(R.id.txt_secdate);
        TextView txt_totalcar=rowView.findViewById(R.id.txt_totalcar);
        TextView txt_totalbike=rowView.findViewById(R.id.txt_totalbike);

        model=arraylistparking.get(position);

        txt_parkingid.setText(model.getParkingid());
        txt_secloginid.setText(model.getSecloginid());
        txt_secdate.setText(model.getParkingdate());
        txt_totalcar.setText(model.getTotalcar());
        txt_totalbike.setText(model.getTotalbike());

        return rowView;
    }
}
