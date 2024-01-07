package com.example.restaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyReservationAdapter extends ArrayAdapter<Model_Reservation> {


    Context context;
    List<Model_Reservation> arrayreservationList ;

    String tableid,statuss;
    TextView table_id;

    SharedPreferences preferences;
    String login_id,login_type;



    public MyReservationAdapter(Context context,List<Model_Reservation> arrayreservationList) {
        super(context, R.layout.my_custom_reservationlist, arrayreservationList);

        this.context=context;
        this.arrayreservationList=arrayreservationList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.my_custom_reservationlist,null,true);

         table_id=view.findViewById(R.id.table_id);
        TextView tablename=view.findViewById(R.id.tablename);
        TextView available=view.findViewById(R.id.available);
        TextView status=view.findViewById(R.id.status);
        Button reserve=view.findViewById(R.id.reserve);

       statuss=status.getText().toString();






        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preferences=context.getSharedPreferences("user_login",MODE_PRIVATE);
                login_id=preferences.getString("userid","");
                login_type=preferences.getString("usertype","");

                statuss=status.getText().toString();

                View viewParent = (View) view.getParent();
                TextView txtid = viewParent.findViewById(R.id.table_id);
                tableid=txtid.getText().toString();

               /* preferences=context.getSharedPreferences("user_table",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("tableid",tableid);
                editor.commit();*/

                String BASEURL=context.getResources().getString(R.string.host);
                String RESERVE_TABLE=BASEURL+"reserve_table.php?tbid="+tableid+"&logid="+login_id;

                StringRequest request=new StringRequest(Request.Method.GET, RESERVE_TABLE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getContext(),Menu.class);
                        context.startActivity(intent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                );

                RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                requestQueue.add(request);



            }
        });






        table_id.setText(arrayreservationList.get(position).getTable_id());
        tablename.setText("Table No: "+arrayreservationList.get(position).getTable_no());
        available.setText(arrayreservationList.get(position).getSeats());
        status.setText(arrayreservationList.get(position).getStatus());

        statuss=status.getText().toString();

        if (statuss.equals("reserved"))
        {
            reserve.setVisibility(View.GONE);
        }



        return view;
    }
}

