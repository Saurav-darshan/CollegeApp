package com.example.cimage.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cimage.R;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> items;

    public CustomListAdapter(Context context, List<String> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.aminities_listview_layout, parent, false);
        }

        String currentItem = items.get(position);

        ImageView iconImageView = listItemView.findViewById(R.id.icon);
        TextView itemTextView = listItemView.findViewById(R.id.item_text);

        // Set icon and text
        iconImageView.setImageResource(R.drawable.baseline_arrow_circle_right_24);
        itemTextView.setText(currentItem);

        return listItemView;
    }
}
