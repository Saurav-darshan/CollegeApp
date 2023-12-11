package com.example.cimage;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.example.cimage.utils.Config;
import com.example.cimage.utils.MasterFunction;

import java.util.HashMap;

public class AddTeacherActivity extends AppCompatActivity {
    private EditText edt_user_name, edt_password;
    private Button btn_add_user;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_teacher_layout);

        mContext = this;

        edt_user_name = findViewById(R.id.edt_user_name);
        edt_password = findViewById(R.id.edt_password);
        btn_add_user = findViewById(R.id.btn_add_user);

        btn_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = edt_user_name.getText().toString();
                String password = edt_password.getText().toString();
                if(user_name.isEmpty()){
                    edt_user_name.setError("Please Enter User Name");
                }else if (password.isEmpty()){
                    edt_password.setError("Please Enter Password");
                }else{
                    HashMap hashMap = new HashMap();
                    hashMap.put("user_name",user_name);
                    hashMap.put("password",password);

                    MasterFunction.request(mContext, Config.AddTeacher, hashMap, true, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            MasterFunction.showDialog(AddTeacherActivity.this,"","Teacher added successfully",1);

                        }
                    },false);

                }
            }
        });

    }
}