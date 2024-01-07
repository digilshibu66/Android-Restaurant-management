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

public class Feedback extends AppCompatActivity {
    EditText edt_feedback;
    Button btn_submit_feedback;

    String str_feedback;


    SharedPreferences preferences;
    String login_id,login_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        edt_feedback=findViewById(R.id.edt_feedback);
        btn_submit_feedback=findViewById(R.id.btn_submit_feedback);

        btn_submit_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_feedback=edt_feedback.getText().toString();

                if (str_feedback.isEmpty())
                {
                    Toast.makeText(Feedback.this, "feedback field is empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String BASEURL=getResources().getString(R.string.host);
                    String ADDFEEDBACK=BASEURL+"addfeedback.php";

                    StringRequest request=new StringRequest(Request.Method.POST, ADDFEEDBACK, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Feedback.this, response, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),ShowFeedback.class);
                            startActivity(intent);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(Feedback.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    {

                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> params=new HashMap<String, String>();
                            params.put("loginid",login_id);
                            params.put("feedback",str_feedback);
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