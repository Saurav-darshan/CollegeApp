package com.example.cimage.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cimage.R;
import com.example.cimage.models.StudentListModel;
import com.example.cimage.utils.ItemClickListner;

import java.util.List;

public class ShowRecylerViewAdapter extends RecyclerView.Adapter<ShowRecylerViewAdapter.ViewHolder> {

    Context context;
    List<StudentListModel> getDataAdapter;

    ItemClickListner it;

    public ShowRecylerViewAdapter(List<StudentListModel> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_student_list_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, int position) {

        final StudentListModel getDataAdapter1 =  getDataAdapter.get(position);

        Viewholder.txt_userid.setText(getDataAdapter1.getStId());
        Viewholder.txt_name.setText(getDataAdapter1.getName());





    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    public void setClickListner(ItemClickListner itt )
    {
        this.it=itt;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_name,status,txt_userid;


        public ViewHolder(View itemView) {
            super(itemView);
            txt_name=(TextView)itemView.findViewById(R.id.txt_name);
            txt_userid=(TextView)itemView.findViewById(R.id.txt_userid);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(it!=null)
            {
                it.onClick(view,getAdapterPosition());
            }
        }

    }


}
