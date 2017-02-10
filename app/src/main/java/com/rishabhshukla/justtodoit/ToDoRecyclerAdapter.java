package com.rishabhshukla.justtodoit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rishabhshukla.justtodoit.Models.ToDo;

import java.util.ArrayList;

/**
 * Created by rishabhshukla on 10/02/17.
 */

public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.ToDoViewHolder>{

    private SQLiteDatabase db;
    private Context context;
    private ArrayList<ToDo> toDoArrayList;

    public ToDoRecyclerAdapter(Context ctx, ArrayList<ToDo> todos, SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        this.context = ctx;
        this.toDoArrayList = todos;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View todoItemView = li.inflate(R.layout.recylerlayout, parent, false);


        return new ToDoViewHolder(todoItemView);
    }

    @Override
    public void onBindViewHolder(final ToDoViewHolder holder, final int position) {
        holder.tvTask.setText(toDoArrayList.get(position).getTaskName());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    holder.tvTask.setPaintFlags(holder.tvTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    holder.tvTask.setPaintFlags(holder.tvTask.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }


            }
        });


        //        holder.tvTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Todos.removeTask(toDoArrayList.get(position).getId(),db);
//                notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(toDoArrayList==null){
            return 0;
        }
        return toDoArrayList.size();
    }

    public void updateTodos(ArrayList<ToDo> todos) {
        this.toDoArrayList = todos;
        notifyDataSetChanged();
    }

    public  interface  onDeleteListener{
        public void onDelete(int position);
    }

    class ToDoViewHolder extends  RecyclerView.ViewHolder{
        TextView tvTask;
        CheckBox checkBox;

        public ToDoViewHolder(View itemView) {
            super(itemView);

            tvTask = (TextView) itemView.findViewById(R.id.tvDisp);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

        }
    }
}