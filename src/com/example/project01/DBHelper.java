package com.example.project01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "project.db", null, 1);
        //cursorFactory
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     /*   String table = "CREATE TABLE ses (" 
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "id TEXT NOT NULL);";*/
       
    	String table ="CREATE TABLE member("
    			        +"_id integer primary key autoincrement ,"
    					+"userid TEXT unique ,"
						+"pw TEXT  NOT NULL,"
						+"name TEXT  NOT NULL,"
						+"nickname TEXT  NOT NULL);";     

        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS member");
        onCreate(db);
    }
}
