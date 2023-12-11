package com.example.cimage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cimage.utils.MasterFunction;
import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
Context mContext;
    Button button;
    ListView listView;
    List<String> list;
    private EditText edt_fullname, edt_mobile_number, edt_email_id;
    private Spinner spnr_course_name;
    private Button btn_send_enq,btn_reg,btn_fee;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_reg = findViewById(R.id.btn_apply);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterFormActivity.class));


            }

        });
        btn_fee = findViewById(R.id.btn_fee);
        btn_fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FeePortal.class));


            }

        });
        // Initialize views and setup toolbar and navigation bar
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext= this;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    startActivity(new Intent(mContext, MainActivity.class));

                } else if (id == R.id.nav_gallery) {
                    startActivity(new Intent(mContext, MainGalleryActivity.class));

                } else if (id == R.id.nav_login) {
                    startActivity(new Intent(mContext, LoginActivity.class));

                } else if (id == R.id.nav_about) {
                    startActivity(new Intent(mContext, AboutActivity.class));
                }
                 else if (id == R.id.nav_news) {
                startActivity(new Intent(mContext, MainEventActivity.class));
                }
                else if (id == R.id.nav_notification) {
                    startActivity(new Intent(mContext, MainNotification.class));
                }

                return true;
            }
        });



        // Setup button click listener
        button = findViewById(R.id.btn_send_enq);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, listViewActivity.class);
                startActivity(intent);
            }
        });

        // Initialize and set up the ListView
        // Initialize and set up the ListView
        list = Arrays.asList("World Class Infrastructure", "Hi-Tech Digital Library",
                "Ranked No.1 College in Bihar by Times of India", "Best B-School of India East by ASSOCHAM",
                "Modern Facility Computer Labs", "Triple mode education (Classroom+Zoom+Youtube)", "Job Oriented Trainings with IIT Collaboration",
                "Most Emerging Institute for Management Education Award",
                "Top BCA College in Patna, Bihar");
        listView = findViewById(R.id.list_view_grid);


        class CustomListAdapter extends BaseAdapter {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = getLayoutInflater().inflate(R.layout.aminities_listview_layout, null);
                TextView text = view.findViewById(R.id.item_text);
                text.setText(list.get(position));
                return view;
            }
        }

        CustomListAdapter customListAdapter = new CustomListAdapter();
        listView.setAdapter(customListAdapter);



        edt_fullname = findViewById(R.id.edt_fullname);
        edt_mobile_number = findViewById(R.id.edt_mobile_number);
        edt_email_id = findViewById(R.id.edt_email_id);
        spnr_course_name = findViewById(R.id.spnr_course_name);
        btn_send_enq = findViewById(R.id.btn_send_enq);




        btn_send_enq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name = edt_fullname.getText().toString();
                String mobile_number = edt_mobile_number.getText().toString();
                String email_id = edt_email_id.getText().toString();

                if (full_name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Write your full name", Toast.LENGTH_SHORT).show();
                } else if (mobile_number.isEmpty()) {
                    Toast.makeText(MainActivity.this, "write your mobile number", Toast.LENGTH_SHORT).show();
                } else if (email_id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "write your email id", Toast.LENGTH_SHORT).show();
                } else {



                    RequestQueue mRequestQueue = null;

                    ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
                    pDialog.setMessage("Loading...PLease wait");
                    pDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://thedeveloperpoint.com/cimage_patli/insert_enquiry.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    pDialog.dismiss();
                                    MasterFunction.showDialog(MainActivity.this,"","SUCCESS",0);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Error"+error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map params = new HashMap();
                            params.put("full_name", full_name);
                            params.put("mobile_number", mobile_number);
                            params.put("email_id", email_id);
                            params.put("course", spnr_course_name.getSelectedItem().toString());
                            return params;
                        }
                    };
                    mRequestQueue = Volley.newRequestQueue(MainActivity.this);
                    mRequestQueue.add(stringRequest);

                }
            }
        });






    }








}