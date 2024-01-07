package com.example.restaurant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddParking extends AppCompatActivity {

    TextView logout;
    SharedPreferences preferences;
    String login_id,login_type;

    EditText edt_date,edt_total_bike,edt_update_bike,edt_total_car,edt_update_car;
    Button btn_update;

    String sdate,stotalbike,supdatedbike,stotalcar,supdatecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_parking);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");


        //logout=findViewById(R.id.logout);
        edt_date=findViewById(R.id.edt_date);
        //edt_total_bike=findViewById(R.id.edt_total_bike);
        edt_update_bike=findViewById(R.id.edt_update_bike);
        //edt_total_car=findViewById(R.id.edt_total_car);
        edt_update_car=findViewById(R.id.edt_update_car);
        btn_update=findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sdate=edt_date.getText().toString().trim();
                //stotalbike=edt_total_bike.getText().toString().trim();
                supdatedbike=edt_update_bike.getText().toString().trim();
                //stotalcar=edt_total_car.getText().toString().trim();
                supdatecar=edt_update_car.getText().toString().trim();

                if (sdate.isEmpty())
                {
                    edt_date.setError("date is empty!");
                }
                /*else if (stotalbike.isEmpty())
                {
                    edt_total_bike.setError("Total bike is empty!");
                }*/
                else if (supdatedbike.isEmpty())
                {
                    edt_update_bike.setError("Updated bike is empty!");
                }
                /*else if (stotalcar.isEmpty())
                {
                    edt_total_car.setError("Total car is empty!");
                }*/
                else if (supdatecar.isEmpty())
                {
                    edt_update_car.setError("Updated car is empty!");
                }
                else
                {
                    preferences=getSharedPreferences("user_login",MODE_PRIVATE);
                    login_id=preferences.getString("userid","");
                    login_type=preferences.getString("usertype","");


                    String BASEURL=getResources().getString(R.string.host);
                    String ADD_PARKING_DETAILS=BASEURL+"addparkingdetails.php";

                    StringRequest request=new StringRequest(Request.Method.POST, ADD_PARKING_DETAILS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(AddParking.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),SecurityDashboard.class);
                            startActivity(intent);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(AddParking.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                    )
                    {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params=new HashMap<String, String>();
                            params.put("date",sdate);
                            params.put("login_id",login_id);
                            params.put("updated_bike",supdatedbike);
                            params.put("updated_car",supdatecar);
                            params.put("login_id",login_id);
                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(request);
                }

            }
        });

       /* logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();

                Intent i=new Intent(AddParking.this,Login.class);
                startActivity(i);
            }
        });*/
    }
}