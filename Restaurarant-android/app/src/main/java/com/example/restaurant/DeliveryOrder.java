package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DeliveryOrder extends AppCompatActivity implements DeliveryOrderAdapter.onClickLayout {

    SharedPreferences preferences;
    String login_id, login_type;

    ListView deliveryorderr_listview;

    DeliveryOrderAdapter orderAdapter;
        Model_Order modelOrder;
        public static ArrayList<Model_Order> showorderArrayList=new ArrayList<>();

    Double price,quant,total;

    String rate,quantity,order_id,reserved_table_id;
    String[] test;
    String login_idd;


    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude,str_latitude,str_longitude;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_order);

        getSupportActionBar().hide();

        preferences = getSharedPreferences("user_login", MODE_PRIVATE);
        login_id = preferences.getString("userid", "");
         login_type=preferences.getString("usertype","");

         deliveryorderr_listview=findViewById(R.id.deliveryorder_listview);


            orderAdapter=new DeliveryOrderAdapter(this,showorderArrayList);
            orderAdapter.clicked(this::clicked);
          String BASEURL=getResources().getString(R.string.host);
                 String GETORDER=BASEURL+"getorderonlinedeliver.php";

            JsonArrayRequest request=new JsonArrayRequest(GETORDER, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    showorderArrayList.clear();

                    total=0.0;

                    test=new String[response.length()];

                    for (int i=0;i<response.length();i++)
                    {
                        try {

                            JSONObject json=response.getJSONObject(i);
                            order_id=json.getString("order_id");
                             login_idd=json.getString("login_id");
                            String item_id=json.getString("item_id");
                            reserved_table_id=json.getString("reserved_table_id");
                            String reserved_table_no=json.getString("reserved_table_no");
                            String item_name=json.getString("item_name");
                            rate=json.getString("rate");
                            quantity=json.getString("quantity");

                        /*Toast.makeText(Order.this, rate, Toast.LENGTH_SHORT).show();
                        Toast.makeText(Order.this, quantity, Toast.LENGTH_SHORT).show();*/

                            test[i]=order_id;

                            modelOrder=new Model_Order(order_id,login_idd,item_id,reserved_table_id,reserved_table_no,item_name,rate,quantity);
                            showorderArrayList.add(modelOrder);
                            orderAdapter.notifyDataSetChanged();
                            deliveryorderr_listview.setAdapter(orderAdapter);

                            deliveryorderr_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Toast.makeText(DeliveryOrder.this, login_idd, Toast.LENGTH_SHORT).show();

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                                    }
                                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                                        OnGPS();
                                    } else {
                                        GetLocation();
                                    }

                                    String BASEURL=getResources().getString(R.string.host);
                                    String GET_LOC_DELIVER=BASEURL+"getlocationtodeliveryboy.php?customer_login_id="+login_idd;

                                   JsonArrayRequest arrayRequest=new JsonArrayRequest(GET_LOC_DELIVER, new Response.Listener<JSONArray>() {
                                       @Override
                                       public void onResponse(JSONArray response) {

                                           for (int i=0;i<response.length();i++)
                                           {
                                               try {

                                                   JSONObject json=response.getJSONObject(i);
                                                   str_latitude=json.getString("latitude");
                                                   str_longitude=json.getString("longitude");


                                                   Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                                           Uri.parse("http://maps.google.com/maps?saddr="+latitude+","+longitude+"&daddr="+str_latitude+","+str_longitude));
                                                   startActivity(intent);

                                                  // Toast.makeText(DeliveryOrder.this, "http://maps.google.com/maps?saddr="+latitude+","+longitude+"&daddr="+str_latitude+","+str_longitude, Toast.LENGTH_SHORT).show();


                                               } catch (Exception e) {
                                                   e.printStackTrace();
                                               }
                                           }

                                       }
                                   }, new Response.ErrorListener() {
                                       @Override
                                       public void onErrorResponse(VolleyError error) {

                                       }
                                   });

                                   RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                                   requestQueue.add(arrayRequest);
                                }
                            });


                            price=Double.parseDouble(rate.toString());
                            quant=Double.parseDouble(quantity.toString());
                            total=total+(price*quant);
                            //Toast.makeText(Order.this, total.toString(), Toast.LENGTH_SHORT).show();
                            //txt_ototal.setText("Total :"+total.toString());



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);





    }

    private void GetLocation()
    {
        if (ActivityCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                //Toast.makeText(this, "Latitude: " + latitude + "\n" + "Longitude: " + longitude, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void OnGPS()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void clicked(View view) {

            //String logid=((TextView)view.findViewById(R.id.dorderlogin_id)).getText().toString();

        Toast.makeText(DeliveryOrder.this, login_idd, Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            GetLocation();
        }

        String BASEURL=getResources().getString(R.string.host);
        String GET_LOC_DELIVER=BASEURL+"getlocationtodeliveryboy.php?customer_login_id="+login_idd;

        JsonArrayRequest arrayRequest=new JsonArrayRequest(GET_LOC_DELIVER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject json=response.getJSONObject(i);
                        str_latitude=json.getString("latitude");
                        str_longitude=json.getString("longitude");


                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?saddr="+latitude+","+longitude+"&daddr="+str_latitude+","+str_longitude));
                        startActivity(intent);

                        // Toast.makeText(DeliveryOrder.this, "http://maps.google.com/maps?saddr="+latitude+","+longitude+"&daddr="+str_latitude+","+str_longitude, Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(arrayRequest);
    }
}