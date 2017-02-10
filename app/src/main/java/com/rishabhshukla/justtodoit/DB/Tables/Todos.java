package com.rishabhshukla.justtodoit.DB.Tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rishabhshukla.justtodoit.Models.ToDo;

import java.util.ArrayList;

import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.COMMA;
import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.LBR;
import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.RBR;
import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.SEMICOL;
import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.TYPE_AI;
import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.TYPE_BOOLEAN;
import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.TYPE_INT;
import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.TYPE_PK;
import static com.rishabhshukla.justtodoit.DB.DBHelper.Consts.TYPE_TEXT;

/**
 * Created by rishabhshukla on 10/02/17.
 */

public class Todos {

    public static final String TABLE_NAME = "todos";
    private static final String TAG = "TODOS";

    public interface  Columns{
        String ID = "todo_id";
        String TASK  = "todo_task";
        String DONE = "todo_done";
    }
    public static final String CMD_TABLE_CREATE =   "CREATE TABLE "+TABLE_NAME+
            LBR+Columns.ID+TYPE_INT+TYPE_PK+TYPE_AI+COMMA+Columns.TASK+TYPE_TEXT+COMMA+
            Columns.DONE+TYPE_BOOLEAN+RBR+SEMICOL;

    public static boolean addTask(String task , SQLiteDatabase db){

        if(db.isReadOnly()){
            return false;
        }
        ContentValues taskObj = new ContentValues();
        taskObj.put(Columns.TASK,task);
        db.insert(TABLE_NAME,null,taskObj); // nullcolHack requires a Col in parameter so it
                                            // sets the value of null taskObj as Null in itself
        db.close();
        return true;
    }
    public static ArrayList<ToDo> getAllTasks (SQLiteDatabase db){
        String[] PROJECTION = {
                Columns.ID, Columns.TASK, Columns.DONE
        };

        Cursor cursor = db.query(TABLE_NAME,PROJECTION,null,null,null,null,null);

        ArrayList<ToDo> todos = new ArrayList<>();
        cursor.moveToFirst();

        int idIndex=  cursor.getColumnIndex(Columns.ID);
        int taskIndex = cursor.getColumnIndex(Columns.TASK);
        int boolIndex= cursor.getColumnIndex(Columns.DONE);
        while(cursor.moveToNext()){
      //      boolean value = cursor.getInt(boolIndex) > 0;
            todos.add(new ToDo(cursor.getInt(idIndex),cursor.getString(taskIndex),cursor.getInt(boolIndex) > 0));
        }
        cursor.close();
        return todos;
    }

    public static boolean updateTaskAsDone(int taskId, SQLiteDatabase db, boolean bool){
        if(db.isReadOnly()){
            return false;
        }
        ContentValues todoObj = new ContentValues();
        todoObj.put(Columns.DONE,bool);
        String whereClause= Columns.ID + " = ?"; // ? is replaced by 3rd parameter of db.delete

        db.update(TABLE_NAME,todoObj,whereClause,new String[]{String.valueOf(taskId)});
        return true;
    }
}
