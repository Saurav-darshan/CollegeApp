package com.example.cimage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.cimage.adapter.CustomListAdapter;
import java.util.Arrays;
import java.util.List;

public class listViewActivity extends AppCompatActivity {

    ListView listView;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_grid);

        // Initialize and set up the ListView
        list = Arrays.asList("World Class Infrastructure", "Hi-Tech Digital Library",
                "Ranked No.1 College in Bihar by Times of India","Best B-School of India East by ASSOCHAM",
                "Modern Facility Computer Labs","Triple mode education (Classroom+Zoom+Youtube)","Job Oriented Trainings with IIT Collaboration",
                "Most Emerging Institute for Management Education Award",
                        "Top BCA College in Patna, Bihar");
        listView = findViewById(R.id.list_view_grid);

        CustomListAdapter customListAdapter = new CustomListAdapter(this, list);
        listView.setAdapter(customListAdapter);
    }
}
