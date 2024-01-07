package com.example.restaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class MenuAdapter extends RecyclerView.Adapter<menuHolder> {

    Context context;
    List<Model_Menu> arraylistmenu;
    SharedPreferences preferences;
    String login_id,login_type,tableid;

    public MenuAdapter(Context context, List<Model_Menu> arraylistmenu) {
        this.context = context;
        this.arraylistmenu = arraylistmenu;
    }

    @NonNull
    @Override
    public menuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_custom_menulist,parent,false);

        return new menuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull menuHolder holder, int position) {

        preferences=context.getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");



        holder.item_id.setText(arraylistmenu.get(position).getItem_id());
        holder.dishname.setText(arraylistmenu.get(position).getItem_name());
        holder.category.setText(arraylistmenu.get(position).getItem_type());
        holder.description.setText(arraylistmenu.get(position).getItem_description());
        holder.price.setText(arraylistmenu.get(position).getItem_rate());

        


    }

    @Override
    public int getItemCount() {
        return arraylistmenu.size();
    }
}

class menuHolder extends RecyclerView.ViewHolder
{
     TextView item_id,dishname,category,description,price;
     EditText quantity;
     Button order;

    SharedPreferences preferences;
    String login_id,login_type;

    String tableid;

    String qua,itemid;

    public menuHolder(@NonNull View itemView) {
        super(itemView);



        preferences=itemView.getContext().getSharedPreferences("user_login",MODE_PRIVATE);
        login_id=preferences.getString("userid","");
        login_type=preferences.getString("usertype","");




        item_id=itemView.findViewById(R.id.item_id);
        dishname=itemView.findViewById(R.id.dishname);
        category=itemView.findViewById(R.id.category);
        description=itemView.findViewById(R.id.description);
        price=itemView.findViewById(R.id.price);
        quantity=itemView.findViewById(R.id.quantity);
        order=itemView.findViewById(R.id.order);

      order.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              qua=quantity.getText().toString();
              itemid=item_id.getText().toString();
              Log.e("q",qua);

              if (qua.isEmpty())
              {
                  Toast.makeText(itemView.getContext(),"quantity is empty!", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  String BASEURL=itemView.getContext().getResources().getString(R.string.host);
                  String ADD_ORDER=BASEURL+"addorder.php";

                  StringRequest request=new StringRequest(Request.Method.POST, ADD_ORDER, new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {

                          Toast.makeText(itemView.getContext(), response, Toast.LENGTH_SHORT).show();
                          Intent intent=new Intent(itemView.getContext(),Order.class);
                          itemView.getContext().startActivity(intent);



                      }
                  }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {

                          Toast.makeText(itemView.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                      }
                  })
                  {

                      @Nullable
                      @Override
                      protected Map<String, String> getParams() throws AuthFailureError {

                          Map<String,String> params=new HashMap<String, String>();
                          params.put("quantity",qua);
                          params.put("itemid",itemid);
                          params.put("loginid",login_id);
                          return params;
                      }
                  };

                  RequestQueue requestQueue= Volley.newRequestQueue(itemView.getContext());
                  requestQueue.add(request);
              }
          }
      });

    }
}
