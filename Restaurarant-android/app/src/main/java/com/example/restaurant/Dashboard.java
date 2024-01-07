package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
LinearLayout menu,order,reservation,park,payment,feedbacks,delivery;
TextView logout;

    SharedPreferences preferences;
    String login_id,login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        menu=findViewById(R.id.menu);
        order=findViewById(R.id.order);
        //reservation=findViewById(R.id.reservation);
        payment=findViewById(R.id.payment);
        park=findViewById(R.id.park);
        feedbacks=findViewById(R.id.feedbacks);
        delivery=findViewById(R.id.delivery);
        logout=findViewById(R.id.logout);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent=new Intent(getApplicationContext(),Onlineorder.class);
              startActivity(intent);
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,Reservation.class);
                startActivity(i);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,Order.class);
                startActivity(i);
            }
        });


        /*reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,Reservation.class);
                startActivity(i);
            }
        });*/

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,OnlineOrderPay.class);
                startActivity(i);
            }
        });

        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,Parking.class);
                startActivity(i);
            }
        });

        feedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard.this,Feedback.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();


                Intent i=new Intent(Dashboard.this,Login.class);
                startActivity(i);
            }
        });
    }
}