package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Chef_Dashboard extends AppCompatActivity {
    LinearLayout menu,updatemenu,customer_order,feedback_food;
    TextView logout;

    SharedPreferences preferences;
    String login_id,login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef__dashboard);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        menu=findViewById(R.id.menu);
        updatemenu=findViewById(R.id.updatemenu);
        customer_order=findViewById(R.id.customer_order);
        feedback_food=findViewById(R.id.feedback_food);
        logout=findViewById(R.id.logout);

        customer_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Chef_Dashboard.this,OnlineChefCustomerOrder.class);
                startActivity(i);

            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Chef_Dashboard.this,TodaysMenuActivity.class);
                startActivity(i);
            }
        });

        updatemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Chef_Dashboard.this,OnlineChefOnlineOrder.class);
                startActivity(i);
            }
        });

        feedback_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Chef_Dashboard.this,Feedback.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();

                Intent i=new Intent(Chef_Dashboard.this,Login.class);
                startActivity(i);
            }
        });
    }
}