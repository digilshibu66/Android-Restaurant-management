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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {
    EditText cardnumber,ccv,amount,expirydate;
    Button pay;

    String order_id,table_id,total;

    String cardno,cvvno,expiryno,grtotal;

    SharedPreferences preferences;
    String login_id,login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().hide();

        cardnumber=findViewById(R.id.cardnumber);
        ccv=findViewById(R.id.ccv);
        amount=findViewById(R.id.amount);
        pay=findViewById(R.id.pay);
        expirydate=findViewById(R.id.expirydate);

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        Intent intent=getIntent();
        order_id=intent.getStringExtra("order_id");
        table_id=intent.getStringExtra("tableid");
        total=intent.getStringExtra("amount");

        //Toast.makeText(this, table_id, Toast.LENGTH_SHORT).show();

        amount.setText(total);
        /*Toast.makeText(this, total, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, order_id, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, order_id, Toast.LENGTH_SHORT).show();*/

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cardno=cardnumber.getText().toString();
                cvvno=ccv.getText().toString();
                expiryno=expirydate.getText().toString();
                grtotal=amount.getText().toString();

                if (cardno.isEmpty())
                {
                    cardnumber.setError("card number is empty!");
                }
                else if (cvvno.isEmpty())
                {
                    ccv.setError("cvv is empty!");
                }
                else if (expiryno.isEmpty())
                {
                    expirydate.setError("expiry date is empty!");
                }
                else if (grtotal.isEmpty())
                {
                    amount.setError("Amount is empty!");
                }
                else
                {
                    String BASEURL=getResources().getString(R.string.host);
                    String ADD_PAYMENT=BASEURL+"addpayment.php";

                    StringRequest request=new StringRequest(Request.Method.POST, ADD_PAYMENT, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(Payment.this,response, Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(intent1);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Payment.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    {

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params=new HashMap<String, String>();
                            params.put("login_id",login_id);
                            params.put("order_id",order_id);
                            params.put("table_id",table_id);
                            params.put("total",total);
                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(request);
                }

            }
        });
    }
}