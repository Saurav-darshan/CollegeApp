package com.example.cimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cimage.utils.MasterFunction;

public class AddScheduleActivity extends AppCompatActivity {

    Button publishButton;
    Context mContext;
    String courseId = "", subjectId = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);

        mContext = this;

        publishButton = findViewById(R.id.btn_sch);

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showToast();
            }

        });


    }


    private  void showToast() {
        MasterFunction.showDialog(AddScheduleActivity.this,"SCHEDULE ","Comming Soon",1);

    }

}
