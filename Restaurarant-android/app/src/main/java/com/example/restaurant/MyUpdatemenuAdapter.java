package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyUpdatemenuAdapter extends ArrayAdapter<Model_Update> {
    List<Model_Update> updateList;


    Context context;


    int resource;


    public MyUpdatemenuAdapter(Context context, int resource, List<Model_Update> updateList) {
        super(context, resource, updateList);
        this.context = context;
        this.resource = resource;
        this.updateList = updateList;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(context);


        View view = layoutInflater.inflate(resource, null, false);


        TextView dishnames = view.findViewById(R.id.dishnames);

        TextView categorys= view.findViewById(R.id.categorys);
        TextView quantity = view.findViewById(R.id.quantity);


        Model_Update update = updateList.get(position);
        dishnames.setText(update.getDishname());
        categorys.setText(update.getCategorys());
        quantity.setText(update.getQuantity());


        return view;
    }
}
