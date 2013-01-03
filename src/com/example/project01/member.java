package com.example.project01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public  class member extends Activity implements View.OnClickListener{
    protected static final EditText String = null;
	EditText _id;
    EditText idEdit;
    EditText pwEdit;
    EditText nameEdit;
    EditText nicknameEdit;
    EditText selectIdEdit;
    
    void finishActivity(){
    	finish();
    }
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member);
   
     _id =(EditText)findViewById(R.id.id);
  	 idEdit = (EditText)findViewById(R.id.id);
  	 pwEdit =(EditText)findViewById(R.id.pw);
  	 nameEdit =(EditText)findViewById(R.id.name);
  	 nicknameEdit =(EditText)findViewById(R.id.nickname);
  	 
  	 Button cancel = (Button)findViewById(R.id.cancel);
  	 cancel.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			_id.setText("");
			idEdit.setText("");
			pwEdit.setText("");
			nameEdit.setText("");
			nicknameEdit.setText("");
		}
	});
  	 
  	 Button ok =(Button)findViewById(R.id.ok);
  	 ok.setOnClickListener(new View.OnClickListener()  {
  	 

 /* 		 @Override
  	 public void onClick(View v){
    		Intent intent = new Intent(member.this, login.class);
    		startActivity(intent);
    	}
    });
	
	}*/
	@Override
 //   public void onClick(View v) {
			 public void onClick(View v) {
			/* Toast.makeText(member.this, id.getText(), Toast.LENGTH_SHORT).show();
		 Toast.makeText(member.this, pw.getText(), Toast.LENGTH_SHORT).show();
		 Toast.makeText(member.this, name.getText(), Toast.LENGTH_SHORT).show();
		 Toast.makeText(member.this, nickname.getText(), Toast.LENGTH_SHORT).show();
		*/
		
    	
    	DBHandler dbhandler = DBHandler.open(member.this);
    	
				/* if(v.getId()== R.id.id) {
					 String id = idEdit.getText().toString();
					 
				 }else if(v.getId()==R.id.pw){
					String pw =pwEdit.getText().toString();
					
				}else if (v.getId()==R.id.name){
					String name = nameEdit.getText().toString();
					
				}else if (v.getId()==R.id.nickname){
					String nickname = nicknameEdit.getText().toString();
				}*/
	
				if(v.getId() == R.id.ok){
					
				long cnt = dbhandler.insert(idEdit.getText().toString(),pwEdit.getText().toString(), nameEdit.getText().toString(), nicknameEdit.getText().toString());
				
				Toast.makeText(
						member.this,
					    "ID:" +idEdit.getText().toString()+ 
						"\npw:"+pwEdit.getText().toString()+
						"\nname:" +nameEdit.getText().toString()+
						"\nnickname:" +nicknameEdit.getText().toString(),Toast.LENGTH_LONG)
						       .show();
				
				}
				Intent intent = new Intent(member.this, login.class );
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}