package com.example.cimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminDashActivity extends AppCompatActivity {
    CardView student,teacher,result,announcement,attendance,subject,schedule,show_student;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dasboard);


        student= findViewById(R.id.add_student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this, AddStudentActivity.class));


            }

        });

        btn= findViewById(R.id.btn_dash_logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this,LoginActivity.class));
            }
        });

        teacher= findViewById(R.id.add_teacher);
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this, AddTeacherActivity.class));


            }

        });
        result= findViewById(R.id.add_result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this, AddResultActivity.class));


            }

        });
        announcement= findViewById(R.id.add_announcement);
        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this, AddAnnouncementActivity.class));


            }

        });
        attendance= findViewById(R.id.add_attendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this, AddAttendanceActivity.class));


            }

        });
        subject= findViewById(R.id.add_subject);
        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this, AddSubject.class));


            }

        });
        schedule= findViewById(R.id.add_time_table);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this, AddScheduleActivity.class));


            }

        });
        show_student= findViewById(R.id.show_student);
        show_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashActivity.this, ShowStudentActivity.class));


            }

        });





    }


}
