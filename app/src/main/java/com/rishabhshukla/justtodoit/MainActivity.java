package com.rishabhshukla.justtodoit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;
    FloatingActionButton fab;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);
        fab = (FloatingActionButton) findViewById(R.id.btnAdd);
        rv = (RecyclerView) findViewById(R.id.rv);

    }
}
