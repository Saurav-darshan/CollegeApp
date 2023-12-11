package com.example.cimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.cimage.R;
import com.example.cimage.adapter.AttedanceRecylerViewAdapter;
import com.example.cimage.adapter.AttendanceListAdapter;
import com.example.cimage.models.StudentListModel;
import com.example.cimage.utils.Config;
import com.example.cimage.utils.MasterFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AttendanceActivity extends AppCompatActivity {


    private Spinner spnr_course_name, spnr_subject;
    private Context mContext;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    AttedanceRecylerViewAdapter recyclerViewadapter;

    List<StudentListModel> studentListModels;
    String courseId = "", subjectId = "";
    private Button btn_submit;
    EditText etdate;

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        mContext = this;

        studentListModels = new ArrayList<>();

        spnr_course_name = findViewById(R.id.spnr_course_name);
        spnr_subject = findViewById(R.id.spnr_subject);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        etdate = findViewById(R.id.editTextDate);

        btn_submit = findViewById(R.id.btn_submit);

        myCalendar = Calendar.getInstance();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = etdate.getText().toString();

                if(date.isEmpty()){
                    etdate.setError("Select date");
                }
                else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("course_id", courseId);
                    hashMap.put("subjectId",subjectId);
                    hashMap.put("date",MasterFunction.parseDateToddMMyyyy(date,"yyyyMMdd","dd/MM/yy"));

                    fetchStudentList(hashMap);
                }



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

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };

        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateDate() {
        String myFormat = "dd/MM/yy"; //put your date format in which you need to display
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        etdate.setText(sdf.format(myCalendar.getTime()));
    }

    void fetchStudentList(HashMap hashMap) {
        String url = Config.baseUrl + "cimage_patli/fetch_attendance_list.php";
        MasterFunction.request(mContext, url, hashMap, true, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                studentListModels.clear();

                try {

                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("success");

                    if(jsonArray.length()==0){
                        MasterFunction.showDialog(mContext,"Attendance Report","Attendance Not found",0);
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String st_id = jsonObject.getString("st_id");
                        String st_name = jsonObject.getString("st_name");
                        String at_status = jsonObject.getString("at_status");
                        StudentListModel listModel = new StudentListModel();
                        listModel.setName(st_name);
                        listModel.setStId(st_id);
                        listModel.setStatus(at_status);
                        studentListModels.add(listModel);
                    }

                    AttendanceListAdapter  attendanceListAdapter = new AttendanceListAdapter(studentListModels,mContext);
                    recyclerView.setAdapter(attendanceListAdapter);

                } catch (JSONException ex) {
                    Log.d("JSONException", "onResponse: "+ex.getMessage().toString());
                }
            }
        }, false);
    }
}