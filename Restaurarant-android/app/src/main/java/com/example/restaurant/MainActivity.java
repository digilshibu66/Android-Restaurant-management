package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    String login_id,login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (login_type.equals("1"))
                {
                    Intent intent=new Intent(getApplicationContext(),Waiter_Dashboard.class);
                    startActivity(intent);
                }
                else if (login_type.equals("2"))
                {
                    Intent intent=new Intent(getApplicationContext(),Chef_Dashboard.class);
                    startActivity(intent);
                }
                else if (login_type.equals("3"))
                {
                    Intent intent=new Intent(getApplicationContext(),Deliveryboy_Dashboard.class);
                    startActivity(intent);
                }
                else if (login_type.equals("4"))
                {
                    Intent intent=new Intent(getApplicationContext(), AddParking.class);
                    startActivity(intent);
                }
                else if (login_type.equals("5"))
                {
                    Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(intent);
                }

                else if (login_type.equals("6"))
                {
                    Intent intent=new Intent(getApplicationContext(),trust_dashboard.class);
                    startActivity(intent);
                }

                else if (login_type.equals("7"))
                {
                    Intent intent=new Intent(getApplicationContext(),farm_dashboard.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }

            }
        }, 3000);


    }
}