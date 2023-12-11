package com.example.cimage.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cimage.R;
import com.example.cimage.models.CourseNameModel;

import java.util.List;


public class CourseNameAdapter extends BaseAdapter {
    Context context;
    List<CourseNameModel> requestLists;

    public CourseNameAdapter(Context context, List<CourseNameModel> requestLists) {
        this.context = context;
        this.requestLists = requestLists;
    }

    @Override
    public int getCount() {
        return requestLists.size();
    }

    @Override
    public CourseNameModel getItem(int i) {
        return requestLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        TextView tv_name, tv_id;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.spinner_two_item, null);
            holder = new ViewHolder();


            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_id = (TextView) view.findViewById(R.id.tv_id);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        CourseNameModel obj = requestLists.get(position);


        holder.tv_name.setText(obj.courseName);
        holder.tv_id.setText(obj.courseId);

        return view;
    }
}