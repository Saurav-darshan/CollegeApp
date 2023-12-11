package com.example.cimage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.example.cimage.utils.Config;
import com.example.cimage.utils.MasterFunction;

import java.util.HashMap;

public class AddAnnouncementActivity extends AppCompatActivity {
    Button publishButton;
    EditText announcement, ann_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_announcement_layout);
        publishButton = findViewById(R.id.btn_publish);
        announcement = findViewById(R.id.announcement_txt);
        ann_title = findViewById(R.id.announcement_title);


        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = ann_title.getText().toString();
                String announcementText = announcement.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("notif_title", title);
                hashMap.put("user_id", "0000");
                hashMap.put("notif_message", announcementText);
                hashMap.put("notif_type", "");
                sendAnnouncementToServer(hashMap);
            }
        });
    }


    private void sendAnnouncementToServer(HashMap hashMap) {
        MasterFunction.request(AddAnnouncementActivity.this, Config.AddAnnouncement, hashMap, true,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MasterFunction.showDialog(AddAnnouncementActivity.this,"","SUCCESS",1);
                    }
                }, false);


    }
}
