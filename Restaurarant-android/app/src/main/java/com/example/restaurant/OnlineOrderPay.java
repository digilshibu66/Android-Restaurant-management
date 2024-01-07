package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class OnlineOrderPay extends AppCompatActivity {

    SharedPreferences preferences;
    String login_id,login_type;



    MyOnlineOrderAdapter orderAdapter;
    OnlineOrderModel modelOrder;
    public static ArrayList<OnlineOrderModel> showorderrArrayList=new ArrayList<>();

    ListView onorder_listview;
    Button btn_opay;
    TextView txt_ototal;

    Double price,quant,total;

    String rate,quantity,order_id,reserved_table_id;
    String[] test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_order_pay);

        onorder_listview=findViewById(R.id.onorder_listview);
        btn_opay=findViewById(R.id.btn_opay);
        txt_ototal=findViewById(R.id.txt_ototal);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        orderAdapter=new MyOnlineOrderAdapter(this,showorderrArrayList);


        btn_opay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<test.length;i++)
                {
                    Intent intent=new Intent(getApplicationContext(),onlinePayment.class);
                    intent.putExtra("order_id",test[i]);
                    intent.putExtra("tableid",reserved_table_id);
                    intent.putExtra("amount",total.toString());
                    startActivity(intent);
                }
            }
        });



        String BASEURL=getResources().getString(R.string.host);
        String GETORDER=BASEURL+"getordercustomeronline.php?loginid="+login_id;

        //Toast.makeText(this, GETORDER, Toast.LENGTH_SHORT).show();

        JsonArrayRequest request=new JsonArrayRequest(GETORDER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("res", String.valueOf(response));

                showorderrArrayList.clear();

                total=0.0;

                test=new String[response.length()];

                for (int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject json=response.getJSONObject(i);
                        order_id=json.getString("order_id");
                        String login_idd=json.getString("login_id");
                        String item_id=json.getString("item_id");
                        reserved_table_id="0";
                       /* String reserved_table_no=json.getString("reserved_table_no");*/
                        String item_name=json.getString("item_name");
                        rate=json.getString("rate");
                        quantity=json.getString("quantity");

                        /*Toast.makeText(Order.this, rate, Toast.LENGTH_SHORT).show();
                        Toast.makeText(Order.this, quantity, Toast.LENGTH_SHORT).show();*/

                        test[i]=order_id;

                        modelOrder=new OnlineOrderModel(order_id,login_idd,item_id,item_name,rate,quantity);
                        showorderrArrayList.add(modelOrder);
                        orderAdapter.notifyDataSetChanged();
                        onorder_listview.setAdapter(orderAdapter);


                        price=Double.parseDouble(rate.toString());
                        quant=Double.parseDouble(quantity.toString());
                        total=total+(price*quant);
                        //Toast.makeText(Order.this, total.toString(), Toast.LENGTH_SHORT).show();
                        txt_ototal.setText("Total :"+total.toString());



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