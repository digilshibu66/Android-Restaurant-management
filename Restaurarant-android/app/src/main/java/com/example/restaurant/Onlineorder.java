package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Onlineorder extends AppCompatActivity {


    RecyclerView onlinemenu_listview;
    SharedPreferences preferences;
    String login_id,login_type;

    MenuOnlineAdapter menuAdapter;
    Model_Menu modelMenu;
    List<Model_Menu> arraylist;

    LinearLayoutManager linearLayoutManager;



    String tableid;
    String item_id,item_name,item_type,description,rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineorder);

        getSupportActionBar().hide();

        preferences=getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");


        onlinemenu_listview = findViewById(R.id.onlinemenu_listview);




        linearLayoutManager=new LinearLayoutManager(this);
        onlinemenu_listview.setLayoutManager(linearLayoutManager);
        arraylist=new ArrayList<>();
        menuAdapter=new MenuOnlineAdapter(this,arraylist);
        onlinemenu_listview.setAdapter(menuAdapter);


        String BASEURL=getResources().getString(R.string.host);
        String MENU=BASEURL+"gettodaymenu.php";


        JsonArrayRequest request=new JsonArrayRequest(MENU, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("res", String.valueOf(response));

                arraylist.clear();

                for (int i=0;i<response.length();i++)
                {

                    try {

                        JSONObject json = response.getJSONObject(i);
                        item_id=json.getString("item_id");
                        item_name=json.getString("item_name");
                        item_type=json.getString("item_type");
                        description=json.getString("description");
                        rate=json.getString("rate");

                        //Toast.makeText(Menu.this, rate, Toast.LENGTH_SHORT).show();

                        modelMenu=new Model_Menu(item_id,item_name,item_type,description,rate);
                        arraylist.add(modelMenu);
                        menuAdapter.notifyDataSetChanged();




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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