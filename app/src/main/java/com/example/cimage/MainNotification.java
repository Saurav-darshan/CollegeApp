package com.example.cimage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cimage.adapter.Notification_List_Adapter;
import com.example.cimage.models.Notification_List_Model;
import com.example.cimage.utils.Config;
import com.example.cimage.utils.MasterFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainNotification extends AppCompatActivity {
    private static final String TAG = MainNotification.class.getName();
    private List<Notification_List_Model> notification_list_models;
    private RecyclerView recyclerView;
    private RequestQueue mRequestQueue;
    StringRequest mStringQueue;
    private Context mContext;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);

        mRequestQueue = Volley.newRequestQueue(this);

        mContext = this;

        recyclerView = findViewById(R.id.notif_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        mStringQueue = new StringRequest(Request.Method.GET, Config.ShowAnnouncement, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("success");
                    if(jsonArray.length()==0){
                        MasterFunction.showDialog(mContext,"Notification","Notification Not found",0);
                    }

                    notification_list_models = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String notif_type = jsonObject.getString("notif_type");
                        String notif_date = jsonObject.getString("created_at");
                        String notif_title = jsonObject.getString("notif_title");
                        String notif_message = jsonObject.getString("notif_message");
                        notification_list_models.add(new Notification_List_Model(notif_type, notif_date, notif_title,notif_message));
                    }

                    Notification_List_Adapter notification_list_adapter = new Notification_List_Adapter(mContext,notification_list_models);
                    recyclerView.setAdapter(notification_list_adapter);

                } catch (JSONException ex) {
                    Log.d("JSONException", "onResponse: "+ex.getMessage().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error : " + error.toString());
            }
        });
        mRequestQueue.add(mStringQueue);
    }
}