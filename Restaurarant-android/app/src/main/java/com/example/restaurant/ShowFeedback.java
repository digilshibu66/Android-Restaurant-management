package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowFeedback extends AppCompatActivity {

    SharedPreferences preferences;
    String login_id,login_type;

    ListView feedback_listview;
    //ImageView add_feedback_view;

    FeedbackAdapter adapter;
    FeedbackModel model;
    public static ArrayList<FeedbackModel> showfeedbackArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_feedback);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        feedback_listview=findViewById(R.id.feedback_listview);
       // add_feedback_view=findViewById(R.id.add_feedback_view);

        adapter=new FeedbackAdapter(this,showfeedbackArrayList);

        String BASEURL=getResources().getString(R.string.host);
        String VIEWFEEDBACK=BASEURL+"getfeedback.php";

        JsonArrayRequest request=new JsonArrayRequest(VIEWFEEDBACK, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                showfeedbackArrayList.clear();

                for (int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject json=response.getJSONObject(i);
                        String feedback_id=json.getString("feedback_id");
                        String customer_login_id=json.getString("customer_login_id");
                        String feedback=json.getString("feedback");
                        String name=json.getString("name");
                        String reply=json.getString("reply");

                        model=new FeedbackModel(feedback_id,customer_login_id,feedback,name,reply);
                        showfeedbackArrayList.add(model);
                        adapter.notifyDataSetChanged();
                        feedback_listview.setAdapter(adapter);


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

        /*add_feedback_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Feedback.class);
                startActivity(intent);
            }
        });
*/

    }
}