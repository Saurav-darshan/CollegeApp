package com.example.cimage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cimage.utils.MasterFunction;

public class AddResultActivity extends AppCompatActivity {
    Button publishButton;
    Spinner university,course,semester;

    EditText exam_name,result_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_result_layout);

        publishButton = findViewById(R.id.btn_publish);
        result_url =findViewById(R.id.result_url);
        exam_name= findViewById(R.id.exam_name);
        university = findViewById(R.id.spnr_universiity_name);
        course = findViewById(R.id.spnr_course_name);
        semester = findViewById(R.id.spnr_semester);
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exam_name.setText("");
                result_url.setText((""));
                university.setSelection(0);
                course.setSelection(0);
                semester.setSelection(0);

                showToast("Published");
            }

        });

    }
    private  void showToast(String message) {
        MasterFunction.showDialog(AddResultActivity.this,"","Result Published",1);

    }


}
