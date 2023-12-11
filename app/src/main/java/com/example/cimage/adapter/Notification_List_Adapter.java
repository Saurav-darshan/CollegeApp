package com.example.cimage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cimage.R;
import com.example.cimage.models.Notification_List_Model;

import java.util.List;

public class Notification_List_Adapter extends RecyclerView.Adapter<Notification_List_Adapter.ViewHolder> {
    private Context context;
    List<Notification_List_Model> itemList;

    public Notification_List_Adapter(Context context, List<Notification_List_Model> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Notification_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_listview_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Notification_List_Adapter.ViewHolder holder, int position) {
        final Notification_List_Model getData = itemList.get(position);

        holder.tv_notif_date.setText(""+getData.getNotif_date());
        holder.tv_notif_title.setText(getData.getNotif_title());
        holder.tv_notif_message.setText(getData.getNotif_message());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_notif_type, tv_notif_date, tv_notif_title, tv_notif_message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_notif_date = (TextView) itemView.findViewById(R.id.tv_notif_date);
            tv_notif_title = (TextView) itemView.findViewById(R.id.tv_notif_title);
            tv_notif_message = (TextView) itemView.findViewById(R.id.tv_notif_message);

            itemView.setTag(itemView);
        }
    }
}
