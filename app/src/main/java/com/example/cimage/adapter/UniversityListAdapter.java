package com.example.cimage.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cimage.R;
import com.example.cimage.models.NewsEventModel;
import com.example.cimage.models.UniversityModel;

import java.util.List;


public class UniversityListAdapter extends BaseAdapter {
    Context context;
    List<UniversityModel> itemList;

    public UniversityListAdapter(Context context, List<UniversityModel> requestList) {
        this.context = context;
        this.itemList = requestList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public UniversityModel getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        TextView tv_id,  tv_name;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.spinner_two_item, null);

            holder = new ViewHolder();
            holder.tv_id = (TextView) view.findViewById(R.id.tv_id);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        UniversityModel obj = itemList.get(position);
        holder.tv_id.setText(obj.universityId);
        holder.tv_name.setText(obj.universityName);

        return view;
    }

}


