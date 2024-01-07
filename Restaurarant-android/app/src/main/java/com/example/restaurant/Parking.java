package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parking extends AppCompatActivity {

    ListView parking_listview;

    SharedPreferences preferences;
    String login_id,login_type;

    ParkingAdapter parkingAdapter;
    ParkingModel parkingModel;
    public static ArrayList<ParkingModel> showparkingArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);


        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        getSupportActionBar().hide();

        parking_listview=findViewById(R.id.parking_listview);

        parkingAdapter=new ParkingAdapter(this,showparkingArrayList);

        String BASEURL=getResources().getString(R.string.host);
        String GET_PARKING_CUSTOMER=BASEURL+"getparkingcustomer.php";

        JsonArrayRequest request=new JsonArrayRequest(GET_PARKING_CUSTOMER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                showparkingArrayList.clear();

                for (int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject json=response.getJSONObject(i);
                        String parking_id=json.getString("parking_id");
                        String sec_login_id=json.getString("sec_login_id");
                        String total_car=json.getString("total_car");
                        String total_bike=json.getString("total_bike");
                        String date=json.getString("date");

                        parkingModel=new ParkingModel(parking_id,sec_login_id,total_car,total_bike,date);
                        showparkingArrayList.add(parkingModel);
                        parkingAdapter.notifyDataSetChanged();
                        parking_listview.setAdapter(parkingAdapter);

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