package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SecurityDashboard extends AppCompatActivity {

    ImageView img_add_parking,img_edit_parking,img_logout;

    SharedPreferences preferences;
    String login_id,login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_dashboard);


        getSupportActionBar().hide();


        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        img_add_parking=findViewById(R.id.img_add_parking);
        img_edit_parking=findViewById(R.id.img_edit_parking);
        img_logout=findViewById(R.id.img_logout);



        img_edit_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),EditParking.class);
                startActivity(intent);
            }
        });

        img_add_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),AddParking.class);
                startActivity(intent);

            }
        });

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();

                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });


    }
}