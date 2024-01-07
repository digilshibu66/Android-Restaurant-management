package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.ListAdapter;
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
import java.util.List;

public class Order extends AppCompatActivity {

    ListView order_listview;
    SharedPreferences preferences;
    String login_id,login_type;
    TextView txt_total;
    Button btn_pay;

    MyOrderAdapter orderAdapter;
    Model_Order modelOrder;
    public static ArrayList<Model_Order> showorderArrayList=new ArrayList<>();

    Double price,quant,total;

    String rate,quantity,order_id,reserved_table_id;
    String[] test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        orderAdapter=new MyOrderAdapter(this,showorderArrayList);

        order_listview = (ListView) findViewById(R.id.order_listview);
        txt_total=findViewById(R.id.txt_total);
        btn_pay=findViewById(R.id.btn_pay);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<test.length;i++) 
                {
                    Intent intent=new Intent(getApplicationContext(),Payment.class);
                    intent.putExtra("order_id",test[i]);
                    intent.putExtra("tableid",reserved_table_id);
                    intent.putExtra("amount",total.toString());
                    startActivity(intent);
                }
            }
        });

        String BASEURL=getResources().getString(R.string.host);
        String GETORDER=BASEURL+"getordercustomer.php?loginid="+login_id;



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
                         String login_idd=json.getString("login_id");
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
                        order_listview.setAdapter(orderAdapter);


                        price=Double.parseDouble(rate.toString());
                        quant=Double.parseDouble(quantity.toString());
                        total=total+(price*quant);
                        //Toast.makeText(Order.this, total.toString(), Toast.LENGTH_SHORT).show();
                        txt_total.setText("Total :"+total.toString());



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
}