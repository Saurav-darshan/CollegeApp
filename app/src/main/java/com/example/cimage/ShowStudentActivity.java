package com.example.cimage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.example.cimage.adapter.ShowRecylerViewAdapter;
import com.example.cimage.models.StudentListModel;
import com.example.cimage.utils.Config;
import com.example.cimage.utils.MasterFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowStudentActivity extends AppCompatActivity {

    private Spinner spnr_course_name, spnr_subject;
    private Context mContext;
    Button back;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    ShowRecylerViewAdapter recyclerViewadapter;

    List<StudentListModel> studentListModels;
    String courseId = "", subjectId = "",LoginUserId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_student);
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, AdminDashActivity.class));

            }
        });

        mContext = this;

        SharedPreferences sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        LoginUserId= sp.getString("userId","");

        studentListModels = new ArrayList<>();

        spnr_course_name = findViewById(R.id.spnr_course_name);

        recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(ShowStudentActivity.this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        spnr_course_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_id = view.findViewById(R.id.tv_id);
                TextView tv_name = view.findViewById(R.id.tv_name);
                courseId = tv_id.getText().toString();

                HashMap hashMap = new HashMap();
                hashMap.put("course_id", courseId);

                fetchStudentList(hashMap);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        MasterFunction.fetchCourseList(mContext,spnr_course_name);
    }

    void fetchStudentList(HashMap hashMap) {
        String url = Config.baseUrl + "cimage_patli/student_list.php";
        MasterFunction.request(mContext, url, hashMap, false, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /// Toast.makeText(mContext, ""+response, Toast.LENGTH_SHORT).show();

                studentListModels.clear();
                try {

                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("success");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String st_id = jsonObject.getString("st_id");
                        String st_name = jsonObject.getString("st_name");
                        StudentListModel listModel = new StudentListModel();
                        listModel.setName(st_name);
                        listModel.setCourseId(courseId);
                        listModel.setSubjectId(subjectId);
                        listModel.setStId(st_id);

                        studentListModels.add(listModel);
                    }

                    ShowRecylerViewAdapter showRecylerViewAdapter = new ShowRecylerViewAdapter(studentListModels, mContext);

                    recyclerView.setAdapter(showRecylerViewAdapter);
                } catch (JSONException ex) {

                }
            }
        }, false);
    }


}