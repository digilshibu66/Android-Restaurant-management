package com.example.restaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class farm_dashboard extends AppCompatActivity {

    ListView farm_mssg_listview;
    ImageView add_farm_mssg;

    SharedPreferences preferences;
    String login_id,login_type;

    TextView txt_logout;

    FarmMessageAdapter messageAdapter;
    FarmMessageModel messageModel;
    public static ArrayList<FarmMessageModel> showfarmArrayList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_dashboard);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        messageAdapter=new FarmMessageAdapter(this,showfarmArrayList);

        getFarmMessage();


        farm_mssg_listview=findViewById(R.id.farm_mssg_listview);
        add_farm_mssg=findViewById(R.id.add_farm_mssg);

        txt_logout=findViewById(R.id.logout);

        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
               editor.commit();

                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();

            }
        });



        add_farm_mssg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),AddMssgfarm.class);
                startActivity(intent);
            }
        });

    }

    private void getFarmMessage()
    {

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");

        String BASEURL=getResources().getString(R.string.host);
        String GETFARMSSG=BASEURL+"getfarmmessage.php?loginid="+login_id;

        JsonArrayRequest request=new JsonArrayRequest(GETFARMSSG, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                showfarmArrayList.clear();

                for (int i=0;i<response.length();i++)
                {

                    try {

                        JSONObject json=response.getJSONObject(i);
                        String farm_request_id=json.getString("farm_request_id");
                        String login_idd=json.getString("login_id");
                        String message=json.getString("message");
                        String reply=json.getString("reply");

                        messageModel=new FarmMessageModel(farm_request_id,login_idd,message,reply);
                        showfarmArrayList.add(messageModel);
                        messageAdapter.notifyDataSetChanged();
                        farm_mssg_listview.setAdapter(messageAdapter);

                    } catch (JSONException e) {
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