package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Deliveryboy_Dashboard extends AppCompatActivity {

    TextView logout;
    LinearLayout cust_order,cust_feedback;

    SharedPreferences preferences;
    String login_id,login_type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryboy__dashboard);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        cust_order=findViewById(R.id.cust_order);
        cust_feedback=findViewById(R.id.cust_feedback);
        logout=findViewById(R.id.logout);

        cust_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Deliveryboy_Dashboard.this,DeliveryOrder.class);
                startActivity(i);
            }
        });

        cust_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Deliveryboy_Dashboard.this,Feedback.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();


                Intent i=new Intent(Deliveryboy_Dashboard.this,Login.class);
                startActivity(i);
            }
        });
    }
}