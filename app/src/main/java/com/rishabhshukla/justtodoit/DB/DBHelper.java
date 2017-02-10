package com.rishabhshukla.justtodoit.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rishabhshukla.justtodoit.DB.Tables.Todos;

/**
 * Created by rishabhshukla on 10/02/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Todos.db";
    public static final int DB_VER =1 ;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    public interface Consts{
        String LBR= " ( ";
        String RBR= " ) ";
        String COMMA = " , ";
        String SEMICOL = " ; ";

        String TYPE_TEXT = " TEXT ";
        String TYPE_INT=" INTEGER ";
        String TYPE_PK = " PRIMARY KEY ";
        String TYPE_AI = " AUTOINCREMENT ";
        String TYPE_BOOLEAN = " BOOLEAN ";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Todos.CMD_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
