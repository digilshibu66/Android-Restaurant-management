package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
EditText username,password;
TextView regiter_link,security,chef,waiter,farm_reg_link,trust_reg_link;
Button login;
String str_username,str_pasword;

SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        regiter_link=findViewById(R.id.register_link);
        farm_reg_link=findViewById(R.id.farm_reg_link);
        trust_reg_link=findViewById(R.id.trust_reg_link);
        login=findViewById(R.id.login);
       /* security=findViewById(R.id.security);
        chef=findViewById(R.id.chef);
        waiter=findViewById(R.id.waiter);*/

        farm_reg_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),FarmRegistration.class);
                startActivity(intent);

            }
        });

        trust_reg_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),TrustRegistration.class);
                startActivity(intent);
            }
        });

        regiter_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_username=username.getText().toString();
                str_pasword=password.getText().toString();

                Map<String,String> params=new HashMap<String, String>();
                params.put("username",str_username);
                params.put("password",str_pasword);

                JSONObject parameter=new JSONObject(params);

                if (str_username.isEmpty())
                {
                    username.setError("Username is required!");
                }
                else if (str_pasword.isEmpty())
                {
                    password.setError("Password is required!");
                }
                else
                {
                    String BASEURL=getResources().getString(R.string.host);
                    String LOGINURL=BASEURL+"login.php";


                    JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, LOGINURL, parameter, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.e("res", String.valueOf(response));

                               try {

                                   int success=response.getInt("success");

                                   if (success==1)
                                   {
                                      String userid=response.getJSONObject("data").getString("login_id");
                                      String usertype=response.getJSONObject("data").getString("type");

                                      SharedPreferences.Editor editor=preferences.edit();
                                      editor.putString("userid",userid);
                                      editor.putString("usertype",usertype);
                                      editor.commit();


                                      if (usertype.equals("1"))
                                      {
                                          Intent intent=new Intent(getApplicationContext(),Waiter_Dashboard.class);
                                          startActivity(intent);
                                      }
                                      else if (usertype.equals("2"))
                                      {
                                          Intent intent=new Intent(getApplicationContext(),Chef_Dashboard.class);
                                          startActivity(intent);
                                      }
                                      else if (usertype.equals("3"))
                                      {
                                          Intent intent=new Intent(getApplicationContext(),Deliveryboy_Dashboard.class);
                                          startActivity(intent);
                                      }
                                      else if (usertype.equals("4"))
                                      {
                                          Intent intent=new Intent(getApplicationContext(), SecurityDashboard.class);
                                          startActivity(intent);
                                      }

                                      else if (usertype.equals("5"))
                                      {
                                          Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                                          startActivity(intent);
                                      }
                                      else if (usertype.equals("6"))
                                      {
                                          Intent intent=new Intent(getApplicationContext(),trust_dashboard.class);
                                          startActivity(intent);
                                      }
                                      else if (usertype.equals("7"))
                                      {
                                          Intent intent=new Intent(getApplicationContext(),farm_dashboard.class);
                                          startActivity(intent);
                                      }
                                      else
                                      {
                                          Toast.makeText(Login.this, "User not found!", Toast.LENGTH_SHORT).show();
                                      }
                                   }
                                   else
                                   {
                                       Toast.makeText(Login.this, "Login unsuccesfull!", Toast.LENGTH_SHORT).show();
                                   }

                               } catch (Exception e) {
                                   e.printStackTrace();
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
        });

        /*security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Security_Dashboard.class);
                startActivity(i);
            }
        });

        chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Chef_Dashboard.class);
                startActivity(i);
            }
        });

        waiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Waiter_Dashboard.class);
                startActivity(i);
            }
        });*/
    }
}