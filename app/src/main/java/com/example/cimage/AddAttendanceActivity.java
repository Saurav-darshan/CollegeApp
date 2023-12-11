package com.example.cimage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cimage.R;
import com.example.cimage.adapter.AttedanceRecylerViewAdapter;
import com.example.cimage.adapter.CourseNameAdapter;
import com.example.cimage.adapter.SubjectListAdapter;
import com.example.cimage.models.CourseNameModel;
import com.example.cimage.models.StudentListModel;

import com.example.cimage.utils.Config;
import com.example.cimage.utils.MasterFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddAttendanceActivity extends AppCompatActivity {

    private Spinner spnr_course_name, spnr_subject;
    private Context mContext;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    AttedanceRecylerViewAdapter recyclerViewadapter;

    List<StudentListModel> studentListModels;
    String courseId = "", subjectId = "",LoginUserId="";
    private Button btn_submit,btn_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attendance_layout);

        mContext = this;

        SharedPreferences sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
        LoginUserId= sp.getString("userId","");

        studentListModels = new ArrayList<>();

        spnr_course_name = findViewById(R.id.spnr_course_name);
        spnr_subject = findViewById(R.id.spnr_subject);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(AddAttendanceActivity.this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        btn_submit = findViewById(R.id.btn_submit);
        btn_show = findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, AttendanceActivity.class));


            }

        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                final ArrayList<String> status = new ArrayList<String>();
                final ArrayList<String> userId = new ArrayList<String>();
                final ArrayList<String> name = new ArrayList<String>();

                int presentstudent = 0, absentstudent = 0;
                for (StudentListModel number : studentListModels) {
                    status.add(number.getStatus());
                    userId.add(number.getStId());
                    name.add(number.getName());

                    if (number.getStatus() == "absent") {
                        absentstudent = absentstudent + 1;
                    } else if ((number.getStatus() == "present")) {
                        presentstudent = presentstudent + 1;
                    }
                }

                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                final View view = layoutInflater.inflate(R.layout.attendence_dialog, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setView(view);
                AlertDialog dialog;
                dialog = builder.show();
                TextView txttotalstudent = (TextView) view.findViewById(R.id.txttotalstudent);
                txttotalstudent.setText("Total Student : " + studentListModels.size());

                TextView txtpresentstudent = (TextView) view.findViewById(R.id.txtpresentstudent);
                txtpresentstudent.setText("Present Student : " + presentstudent);

                TextView txtabsentstudent = (TextView) view.findViewById(R.id.txtabsentstudent);
                txtabsentstudent.setText("Absent Student : " + absentstudent);

                Button btnsubmit = (Button) view.findViewById(R.id.btnsubmit);

                HashMap<String,String> params=new HashMap<String, String>();

                params.put("userId", String.valueOf(userId));
                params.put("name", String.valueOf(name));
                params.put("status", String.valueOf(status));
                params.put("course", courseId);
                params.put("subject", subjectId);
                params.put("createdBy", LoginUserId);

                btnsubmit.setOnClickListener(v -> {
                    dialog.dismiss();
                    insertAttendance(mContext, params);
                });

            }
        });


        spnr_course_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_id = view.findViewById(R.id.tv_id);
                TextView tv_name = view.findViewById(R.id.tv_name);
                courseId = tv_id.getText().toString();

                HashMap hashMap = new HashMap();
                hashMap.put("course_id", courseId);

                fetchStudentList(hashMap);

                MasterFunction.fetchSubjectList(mContext,hashMap,spnr_subject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnr_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_id = view.findViewById(R.id.tv_id);
                TextView tv_name = view.findViewById(R.id.tv_name);
                subjectId = tv_id.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        MasterFunction.fetchCourseList(mContext,spnr_course_name);
    }

    void insertAttendance(Context context, HashMap hashMap) {
        String url = Config.baseUrl+"cimage_patli/insert_attendance.php";
        MasterFunction.request(context,   url
                ,hashMap,true, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponse", " : "+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            int success = jsonObject.getInt("success");

                            if(success==0){
                                MasterFunction.showDialog(mContext,"Failed",message,0);
                            }
                            else if(success==1){
                                MasterFunction.showDialog(mContext,"Success",message,1);
                            }
                            else{
                                MasterFunction.showDialog(mContext,"Error occurred",message,0);
                            }
                        }
                        catch (JSONException ex){

                        }

                    }
                }, false);
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
                        String university_id = jsonObject.getString("university_id");
                        StudentListModel listModel = new StudentListModel();
                        listModel.setName(st_name);
                        listModel.setCourseId(courseId);
                        listModel.setSubjectId(subjectId);
                        listModel.setStId(st_id);
                        listModel.setStatus("absent");
                        studentListModels.add(listModel);
                    }

                    AttedanceRecylerViewAdapter attedanceRecylerViewAdapter = new AttedanceRecylerViewAdapter(studentListModels, mContext);

                    recyclerView.setAdapter(attedanceRecylerViewAdapter);
                } catch (JSONException ex) {

                }
            }
        }, false);
    }


}