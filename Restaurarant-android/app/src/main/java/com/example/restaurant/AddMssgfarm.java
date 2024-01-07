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

public class AddMssgfarm extends AppCompatActivity {

    EditText edt_farm_message;
    Button btn_add_farm_mssg;

    String str_farm_message;

    SharedPreferences preferences;
    String login_id,login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mssgfarm);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        edt_farm_message=findViewById(R.id.edt_farm_message);
        btn_add_farm_mssg=findViewById(R.id.btn_add_farm_mssg);

        btn_add_farm_mssg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_farm_message=edt_farm_message.getText().toString();

                if (str_farm_message.isEmpty())
                {
                    Toast.makeText(AddMssgfarm.this, "Field is empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String BASEURL=getResources().getString(R.string.host);
                    String ADDFARMSSG=BASEURL+"addfarmmssg.php";

                    StringRequest request=new StringRequest(Request.Method.POST, ADDFARMSSG, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(AddMssgfarm.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),farm_dashboard.class);
                            startActivity(intent);



                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(AddMssgfarm.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params=new HashMap<String, String>();
                            params.put("login_id",login_id);
                            params.put("mssg",str_farm_message);
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