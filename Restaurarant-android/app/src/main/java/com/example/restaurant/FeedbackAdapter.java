package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FeedbackAdapter extends BaseAdapter {

    Context context;
    ArrayList<FeedbackModel> arraylistfeedback;
    FeedbackModel feedbackModel;

    public FeedbackAdapter(Context context, ArrayList<FeedbackModel> arraylistfeedback) {
        this.context = context;
        this.arraylistfeedback = arraylistfeedback;
    }

    @Override
    public int getCount() {
        return arraylistfeedback.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylistfeedback.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_farm_message,null,true);

        TextView txt_feedback_id=rowView.findViewById(R.id.txt_feedback_id);
        TextView txt_login_id=rowView.findViewById(R.id.txt_login_id);
        TextView txt_name=rowView.findViewById(R.id.txt_name);
        TextView txt_feedback=rowView.findViewById(R.id.txt_feedback);
        TextView txt_feedback_reply=rowView.findViewById(R.id.txt_feedback_reply);


        txt_feedback_id.setText(arraylistfeedback.get(position).getFeedback_id());
        txt_login_id.setText(arraylistfeedback.get(position).getCustomer_login_id());
        txt_name.setText(arraylistfeedback.get(position).getName());
        txt_feedback.setText("Feedback :"+arraylistfeedback.get(position).getFeedback());
        txt_feedback_reply.setText("reply :"+arraylistfeedback.get(position).getReply());

        return rowView;
    }
}
