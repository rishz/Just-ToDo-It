package com.rishabhshukla.justtodoit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.rishabhshukla.justtodoit.DB.DBHelper;
import com.rishabhshukla.justtodoit.DB.Tables.Todos;
import com.rishabhshukla.justtodoit.Models.ToDo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    FloatingActionButton fab;
    RecyclerView rv;
    ToDoRecyclerAdapter todoAdapter;
    DBHelper dbHelper;

    ArrayList<ToDo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);
        fab = (FloatingActionButton) findViewById(R.id.btnAdd);
        rv = (RecyclerView) findViewById(R.id.rv);
        todos = new ArrayList<>();
        dbHelper = new DBHelper(this);

        todos= Todos.getAllTasks(dbHelper.getReadableDatabase());
        todoAdapter = new ToDoRecyclerAdapter(this,todos,dbHelper.getWritableDatabase());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(todoAdapter);
        refreshTodos();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Todos.addTask(et.getText().toString(),dbHelper.getWritableDatabase());
                refreshTodos();
            }
        });

    }

    void refreshTodos(){
        todos = Todos.getAllTasks(dbHelper.getReadableDatabase());
        todoAdapter.updateTodos(todos);
    }
}
