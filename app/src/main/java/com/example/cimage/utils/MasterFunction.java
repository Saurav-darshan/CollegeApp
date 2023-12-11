package com.example.cimage.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cimage.adapter.CourseNameAdapter;
import com.example.cimage.adapter.SubjectListAdapter;
import com.example.cimage.models.CourseNameModel;
import com.example.cimagepatliputra.models.SubjectModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterFunction {
    public static void request(Context context, String url, final Map<String, String> params, boolean isprogress, final Response.Listener<String> listener, boolean is_cache) {
        ProgressDialog prog = null;
        if (isprogress) {
            prog = ProgressDialog.show(context, "", "Please wait...", false, false);
        }
        final ProgressDialog finalProg = prog;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(finalProg!=null) {
                    if(finalProg.isShowing()){
                        finalProg.dismiss();
                    }
                }
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(finalProg!=null) {
                    if(finalProg.isShowing()){
                        finalProg.dismiss();
                    }
                }
                Log.e("VolleyError", error.toString());
                String err = error.toString();
                if (err.contains("Failed to connect")) {
                    listener.onResponse("No Internet Connection detected please check internet connection");
                } else {
                    listener.onResponse(error.toString().replace("com.android.volley.",""));
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        stringRequest.setShouldCache(is_cache);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public static void request_Get(Context context, String url, boolean isprogress, final Response.Listener<String> listener, boolean is_cache) {
        ProgressDialog prog = null;
        Log.e("url", url);
        if (isprogress) {
            prog = ProgressDialog.show(context, "", "Please wait...", false, false);
        }
        final ProgressDialog finalProg = prog;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(finalProg!=null) {
                    if(finalProg.isShowing()){
                        finalProg.dismiss();
                    }

                }
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(finalProg!=null) {
                    if(finalProg.isShowing()){
                        finalProg.dismiss();
                    }
                }
                Log.e("VolleyError", error.toString());
                String err = error.toString();
                if (err.contains("Failed to connect")) {
                    listener.onResponse("No Internet Connection detected please check internet connection");
                } else {
                    listener.onResponse(error.toString().replace("com.android.volley.",""));
                }

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        stringRequest.setShouldCache(is_cache);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }


    public static void bindCourseList(String response, Context context, Spinner spnr){

        try {
            List<CourseNameModel> courseNameModels = new ArrayList<>();
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("success");
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String course_id = jsonObject.getString("course_id");
                String course_name = jsonObject.getString("course_name");
                courseNameModels.add(new CourseNameModel(course_id, course_name));
            }
            CourseNameAdapter courseNameAdapter = new CourseNameAdapter(context, courseNameModels);
            spnr.setAdapter(courseNameAdapter);

        } catch (JSONException ex) {

        }
    }

    public static void showDialog(Context context,String title,String msg,int status){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(status==1){
                    ((Activity)context).finish();
                }
            }
        });

        AlertDialog dialog =builder.create();
        dialog.show();
    }

    public static   void fetchCourseList(Context mContext,Spinner spnr_course_name) {
        String url = Config.baseUrl + "cimage_patli/course_list.php";

        MasterFunction.request_Get(mContext, url, false, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                MasterFunction.bindCourseList(response, mContext, spnr_course_name);

            }
        }, false);

    }

    public static void fetchSubjectList(Context mContext,  HashMap hashMap,Spinner spnr_subject) {
        String url = Config.baseUrl + "cimage_patli/subject_list.php";

        MasterFunction.request(mContext, url, hashMap, false, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    List<SubjectModel> subjectModels = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("success");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String subject_id = object.getString("subject_id");
                        String subject_name = object.getString("subject_name");

                        subjectModels.add(new SubjectModel(subject_id, subject_name));
                    }
                    SubjectListAdapter subjectListAdapter = new SubjectListAdapter(mContext, subjectModels);
                    spnr_subject.setAdapter(subjectListAdapter);
                } catch (JSONException ex) {

                }

            }
        }, false);

    }

    public static String parseDateToddMMyyyy(String time,String outputPattern,String inputPattern ) {

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static  void showToast(Context ctx,String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }


}
