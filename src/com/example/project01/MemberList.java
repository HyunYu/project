package com.example.project01;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;

public  class MemberList extends ListActivity implements View.OnClickListener{
    protected static final EditText String = null;
    
    
     void finishActivity(){
     	finish();
     }
    @Override
    public void onResume(){
    	super.onResume();
       	DBHandler dbhandler = DBHandler.open(this);
   	 
    	Cursor cursor = dbhandler.selectAll();
    	startManagingCursor(cursor);
    	Log.d("tag",""+cursor.getCount());
    	
    	// db 데이터를 for문으로 빼오는 내용 배열로도 만들 수 있음
    	String cols[] = cursor.getColumnNames();
    	for(int i = 0 ; i < cols.length; i++){
    		Log.d("'tag", cols[i]+":"+ cursor.getString(i));
    	}
    	SimpleCursorAdapter cusorAdapter = new SimpleCursorAdapter(
    			this,
    			R.layout.listitem ,
    			cursor,
    			new String[]{"_id","userid","name","nickname"},
    			new int[]{R.id.listid, R.id.listuserid, R.id.listname, R.id.listnickname}
    			);
    	setListAdapter(cusorAdapter);
 //   	cursor.close();
    	dbhandler.close();   	
    }

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listform);
 
        Button okBtn = (Button)findViewById(R.id.okBtn);
     	okBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MemberList.this, login.class );
				setResult(RESULT_OK,intent);				
			    finishActivity();
			  
			  
			}
		});

	
	

/*
 	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			idEdit.setText("");
			pwEdit.setText("");
			nameEdit.setText("");
			nicknameEdit.setText("");
		}
	});
   
  
	

	
    
    	
			f(v.getId() == R.id.okBtn){
					
				long cnt = dbhandler.selectAll(idEdit.getText().toString(),nameEdit.getText().toString(), nicknameEdit.getText().toString());
				
				Toast.makeText(
						list.this,
						"ID:" +idEdit.getText().toString()+ 
						"\npw:"+pwEdit.getText().toString()+
						"\nname:" +nameEdit.getText().toString()+
						"\nnickname:" +nicknameEdit.getText().toString(),Toast.LENGTH_LONG)
						       .show();
				
				}
				Intent intent = new Intent(list.this, login.class );
		  		setResult(RESULT_OK,intent);				
				finishActivity();
		  				
	}
	
    });
 
    }
		


 
                
      
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
@Override
protected  void onActivityResult(int requestCode, int resultCode, Intent intent){
	if(requestCode ==2){
		if(requestCode == RESULT_OK){
			Button btn =(Button)findViewById(R.id.ListBtn);
			btn.setVisibility(View.GONE);
			LinearLayout btn2 =(LinearLayout)findViewById(R.id.loginProc);
			btn2.setVisibility(View.VISIBLE);
		
		}
	}
}*/
	


}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}





	