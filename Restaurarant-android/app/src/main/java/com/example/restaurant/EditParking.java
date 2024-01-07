package com.example.restaurant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditParking extends AppCompatActivity {

    EditText edt_edate,edt_bike,edt_car;
    Button btn_eupdate;

    SharedPreferences preferences;
    String login_id,login_type;

    String strbike,strcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parking);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        edt_edate=findViewById(R.id.edt_edate);
        edt_bike=findViewById(R.id.edt_bike);
        edt_car=findViewById(R.id.edt_car);
        btn_eupdate=findViewById(R.id.btn_eupdate);

        btn_eupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strbike=edt_bike.getText().toString();
                strcar=edt_car.getText().toString();


                preferences=getSharedPreferences("user_login",MODE_PRIVATE);
                login_id=preferences.getString("userid","");
                login_type=preferences.getString("usertype","");

                String BASEURL=getResources().getString(R.string.host);
                String ADD_PARKING_DETAILS=BASEURL+"updateparkingdetailssecurity.php";

                StringRequest request=new StringRequest(Request.Method.POST, ADD_PARKING_DETAILS, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(EditParking.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),SecurityDashboard.class);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(EditParking.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                )
                {

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params=new HashMap<String, String>();
                        params.put("logid",login_id);
                        params.put("bike",strbike);
                        params.put("car",strcar);
                        return params;
                    }
                };


                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(request);


            }
        });

        String BASEURL=getResources().getString(R.string.host);
        String ADD_PARKING_DETAILS=BASEURL+"getparkingdetailssecurity.php?logid="+login_id;

        JsonArrayRequest request=new JsonArrayRequest(ADD_PARKING_DETAILS, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject json=response.getJSONObject(i);
                        String date=json.getString("date");
                        String park_bike=json.getString("total_bike");
                        String park_car=json.getString("total_car");

                        edt_edate.setText(date);
                        edt_bike.setText(park_bike);
                        edt_car.setText(park_car);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(EditParking.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}