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

public class AddMssgtrust extends AppCompatActivity {

    EditText edt_trust_message;
    Button btn_add_trust_mssg;
    String str_trust_message;

    SharedPreferences preferences;
    String login_id,login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mssgtrust);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        edt_trust_message=findViewById(R.id.edt_trust_message);
        btn_add_trust_mssg=findViewById(R.id.btn_add_trust_mssg);

        btn_add_trust_mssg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_trust_message=edt_trust_message.getText().toString();

                if (str_trust_message.isEmpty())
                {
                    Toast.makeText(AddMssgtrust.this, "Field is empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String BASEURL=getResources().getString(R.string.host);
                    String ADDTRMSG=BASEURL+"addtrustmssg.php";

                    StringRequest request=new StringRequest(Request.Method.POST, ADDTRMSG, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(AddMssgtrust.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),farm_dashboard.class);
                            startActivity(intent);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddMssgtrust.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params=new HashMap<String, String>();
                            params.put("login_id",login_id);
                            params.put("mssg",str_trust_message);
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