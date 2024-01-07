package com.example.restaurant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class FarmRegistration extends AppCompatActivity {

    EditText edt_farm_name,edt_farm_contact,edt_farm_password;
    Button btn_farm_register;

    String str_farm_name,str_farm_contact,str_farm_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_registration);

        getSupportActionBar().hide();

        edt_farm_name=findViewById(R.id.edt_farm_name);
        edt_farm_contact=findViewById(R.id.edt_farm_contact);
        edt_farm_password=findViewById(R.id.edt_farm_password);

        btn_farm_register=findViewById(R.id.btn_farm_register);

        btn_farm_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_farm_name=edt_farm_name.getText().toString();
                str_farm_contact=edt_farm_contact.getText().toString();
                str_farm_password=edt_farm_password.getText().toString();

                if (str_farm_name.isEmpty())
                {
                    Toast.makeText(FarmRegistration.this, "Farm name is empty!", Toast.LENGTH_SHORT).show();
                }
                else if (str_farm_contact.isEmpty())
                {
                    Toast.makeText(FarmRegistration.this, "Farm contact is empty!", Toast.LENGTH_SHORT).show();
                }
                else if (str_farm_password.isEmpty())
                {
                    Toast.makeText(FarmRegistration.this, "Farm password is empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String BASEURL=getResources().getString(R.string.host);
                    String TRUSTREG_URL=BASEURL+"farm_register.php";


                    StringRequest request=new StringRequest(Request.Method.POST, TRUSTREG_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params=new HashMap<String, String>();
                            params.put("farmname",str_farm_name);
                            params.put("farmcontact",str_farm_contact);
                            params.put("farmpassword",str_farm_password);
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