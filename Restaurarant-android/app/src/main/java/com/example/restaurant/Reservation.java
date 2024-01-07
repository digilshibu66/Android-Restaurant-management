package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Reservation extends AppCompatActivity {

    ListView table_listview;

    SharedPreferences preferences;
    String login_id,login_type;

    MyReservationAdapter reservationAdapter;
    Model_Reservation modelReservation;
    public static ArrayList<Model_Reservation> showtableArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        table_listview = (ListView) findViewById(R.id.table_listview);

        reservationAdapter=new MyReservationAdapter(this,showtableArrayList);

        String BASEURL=getResources().getString(R.string.host);
        String GET_TABLE=BASEURL+"gettable.php";

        JsonArrayRequest request=new JsonArrayRequest(GET_TABLE, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                showtableArrayList.clear();

                for (int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject json=response.getJSONObject(i);
                        String tbl_id=json.getString("tbl_id");
                        String tbl_no=json.getString("tbl_no");
                        String chair_no=json.getString("chair_no");
                        String status=json.getString("status");

                        //Toast.makeText(Reservation.this,tbl_id, Toast.LENGTH_SHORT).show();

                        modelReservation=new Model_Reservation(tbl_id,tbl_no,chair_no,status);
                        showtableArrayList.add(modelReservation);
                        reservationAdapter.notifyDataSetChanged();
                        table_listview.setAdapter(reservationAdapter);

                    }catch (Exception e) {
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