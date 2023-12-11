package com.example.cimage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cimage.utils.MasterFunction;

public class RegisterFormActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen_layout);
        btn = findViewById(R.id.btn_registration);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MasterFunction.showDialog(RegisterFormActivity.this,"","SUCCESS",1);
            }
        });
    }
}