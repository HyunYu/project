package com.example.project01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

public class DBHandler {
    private DBHelper helper;
    private SQLiteDatabase db;

    private DBHandler(Context ctx) {
    	try{
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase();
        Log.e("tag","ok");
    	}catch(Exception e){
    		e.printStackTrace();
    		Log.e("tag","e"+e.getMessage());
    	}
    }

    public static DBHandler open(Context ctx) throws SQLException {
        DBHandler handler = new DBHandler(ctx);

        return handler;
    }

    public void close() {
        helper.close();
    }

    public long insert(String idEdit, String pwEdit, String nameEdit, String nicknameEdit) {
      
    ContentValues values = new ContentValues();
    Log.d("tag", nameEdit);
        values.put("userid",idEdit);
        values.put("pw", pwEdit);
        values.put("name", nameEdit);
        values.put("nickname",nicknameEdit);
        Log.d("tag", nameEdit);

        return db.insert("member",null,values);
       
    }


    public Cursor select(String idEdit ,String pwEdit) throws SQLException {
    	//distinct 
    /*	Cursor cursor = db.query(true, "member", //ù��°true �ߺ�����
                 new String[] {"userid","idEdit"},
                 "userid"+ "=" + idEdit,
                 null,null,null, null, null);*/
    
    	Cursor cursor = db.query(true, "member", //ù��°true �ߺ�����
                new String[] {"pw"},
                "userid='"+idEdit+"'",
                null,null,null, null, null);
   //where?������, group, having(group �߰�����),order by, limit
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

	public long insert(EditText id, EditText pw, EditText name,
			EditText nickname) {
		// TODO Auto-generated method stub
		return 0;
	}

public Cursor selectAll() throws SQLException{
	Cursor cursor = db.query(true,"member", 
			new String[] {"_id","userid","name","nickname"},
					null,null,null,null,null,null);
	if(cursor !=null){
		cursor.moveToFirst();
	}
	return cursor;
}

}

