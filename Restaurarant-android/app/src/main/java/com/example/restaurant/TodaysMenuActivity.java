package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TodaysMenuActivity extends AppCompatActivity {

     ListView listview_todaysmenu;

    SharedPreferences preferences;
    String login_id,login_type;

    TodaysMenuAdapter adapter;
    TodaysMenuModel model;
    public static ArrayList<TodaysMenuModel> showtodaymenuArrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_menu);

        listview_todaysmenu=findViewById(R.id.listview_todaysmenu);

        getSupportActionBar().hide();


        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        adapter=new TodaysMenuAdapter(this,showtodaymenuArrayList);

        String BASEURL=getResources().getString(R.string.host);
        String MENU=BASEURL+"gettodaymenu.php";


        JsonArrayRequest request=new JsonArrayRequest(MENU, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("res", String.valueOf(response));

                showtodaymenuArrayList.clear();

                for (int i=0;i<response.length();i++)
                {

                    try {

                        JSONObject json = response.getJSONObject(i);
                       String item_id=json.getString("item_id");
                       String item_name=json.getString("item_name");
                       String item_type=json.getString("item_type");
                       String description=json.getString("description");
                       String rate=json.getString("rate");

                        //Toast.makeText(Menu.this, rate, Toast.LENGTH_SHORT).show();

                        model=new TodaysMenuModel(item_id,item_name,item_type,description,rate);
                        showtodaymenuArrayList.add(model);
                        adapter.notifyDataSetChanged();
                        listview_todaysmenu.setAdapter(adapter);



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}