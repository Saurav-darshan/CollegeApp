package com.example.cimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cimage.utils.MasterFunction;

public class FeePortal extends AppCompatActivity {
    Button btn_back,btn_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fee_activity);

        btn_back = findViewById(R.id.back);
        btn_proceed = findViewById(R.id.process);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeePortal.this, MainActivity.class));
            }
        });
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MasterFunction.showDialog(FeePortal.this,"FEE PORTAL","Payment Gateway Coming Soon",1);

            }
        });

    }
}




