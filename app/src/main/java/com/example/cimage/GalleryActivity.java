package com.example.cimage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.cimage.adapter.CustomListAdapter;
import java.util.Arrays;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallery);
    }

}
