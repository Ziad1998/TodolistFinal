package com.example.todolistfinal.ui.adapter.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistfinal.R;
import com.example.todolistfinal.data.todolist.model;

import java.util.ArrayList;
import java.util.List;

public class adapter extends  RecyclerView.Adapter<adapter.ContactHolder>  {
    private List<model> modelList =new ArrayList<>();
com.example.todolistfinal.ui.adapter.todolist.deleteNode deleteNode;
Context context;
    public void setResult(List<model> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }
    public adapter(Context context, com.example.todolistfinal.ui.adapter.todolist.deleteNode deleteNode) {
        this.context = context;
        this.deleteNode=deleteNode;

    }
    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ContactHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        model c = modelList.get(position);
        if(c!=null){
            holder.task.setText(c.getTask());
            holder.description.setText(c.getDescription());
            holder.day.setText(c.getDay());
            holder.month.setText(c.getMonth());
            holder.year.setText(c.getYear());
        }
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
deleteNode.delete(c.getTaskid());
          }
      });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    static class ContactHolder extends RecyclerView.ViewHolder{
        TextView task,description,day,month,year;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            task=itemView.findViewById(R.id.taskInfo);
            description=itemView.findViewById(R.id.descInfo);
            day=itemView.findViewById(R.id.day);
            month=itemView.findViewById(R.id.monthly);
            year=itemView.findViewById(R.id.year);
        }
    }

}
