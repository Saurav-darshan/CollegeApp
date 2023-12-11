package com.example.cimage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cimage.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edt_username,edt_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_login);

        edt_username = findViewById(R.id.user_id);
        edt_password = findViewById(R.id.pass_id);
        btn_login = findViewById(R.id.btn_sign_in);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt_username.getText().toString();
                String pwd= edt_password.getText().toString();

                if(username.isEmpty()){
                    edt_username.setError("Write login user name");
                }
                else if(pwd.isEmpty()){
                    edt_password.setError("Write login password");
                }
                else {

                    ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("Login, Authenticate please wait...");
                    progressDialog.show();

                    String url= Config.baseUrl +"cimage_patli/admin_login.php";

                    RequestQueue mRequestQueue = Volley.newRequestQueue(LoginActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int success = jsonObject.getInt("success");
                                        String message = jsonObject.getString("message");
                                        String userId = jsonObject.getString("userId");

                                        if(success==1){
                                            SharedPreferences sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor spe =sp.edit();
                                            spe.putBoolean("islogin",true);
                                            spe.putString("userId",userId);
                                            spe.apply();
                                            Intent intent = new Intent(LoginActivity.this, AdminDashActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else if(success==0){
                                            Toast.makeText(LoginActivity.this, "Invalid User Id/password", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(LoginActivity.this, "Error Occurred : "+response, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    catch (JSONException ex){

                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap hashMap = new HashMap();
                            hashMap.put("user_name",username);
                            hashMap.put("pwd",pwd);
                            return  hashMap;
                        }
                    };

                    mRequestQueue.add(stringRequest);
                }
            }
        });

    }
}