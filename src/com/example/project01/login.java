package com.example.project01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class login extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
  
        Button alert = (Button) findViewById(R.id.LoginBtn);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            //	Intent intent = new Intent(login.this, member.class);
            	//startActivity(intent);
            }
        });
        Button memBtn =(Button)findViewById(R.id.memBtn);
        memBtn.setOnClickListener(new View.OnClickListener(){
        	@Override
			public void onClick(View v){
        		Intent intent = new Intent(login.this, member.class);
        		startActivityForResult(intent, 1);
        	}
        });
        Button ListBtn =(Button)findViewById(R.id.ListBtn);
        ListBtn.setOnClickListener(new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent(login.this, MemberList.class);
        	startActivity(intent);
        	//	startActivityForResult(intent, 2);
        	}
        });
        Button gameBtn=(Button)findViewById(R.id.gameBtn);
        gameBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			Intent intent =new Intent(login.this, Main.class);
			startActivity(intent);
			}
		});
        Button setBtn =(Button)findViewById(R.id.setBtn);
        setBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent =new Intent(login.this, Setting.class);
			startActivity(intent);
				
			}
		});
    
   
        Button logoutBtn=(Button)findViewById(R.id.LogOutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
            	LinearLayout loginProcLayout = (LinearLayout)findViewById(R.id.loginProc);
            	LinearLayout loginFormLayout = (LinearLayout)findViewById(R.id.loginForm);
               	loginFormLayout.setVisibility(View.VISIBLE);
            	loginProcLayout.setVisibility(View.GONE);	            	
			}
		});
		
    }
    private void showAlertDialog() {
    //	Toast.makeText(login.this, "로그인", Toast.LENGTH_SHORT);
       
    	
    	LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = (LinearLayout) vi.inflate(R.layout.logindialog, null);

        final EditText  idEdit = (EditText) loginLayout.findViewById(R.id.id);
        final EditText  pwEdit = (EditText) loginLayout.findViewById(R.id.password);

        new AlertDialog.Builder(this).setTitle("로그인").setView(loginLayout)
                .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	LinearLayout loginProcLayout = (LinearLayout)findViewById(R.id.loginProc);
                    	LinearLayout loginFormLayout = (LinearLayout)findViewById(R.id.loginForm);
             
                    	String idStr = idEdit.getText().toString();
                    	String pwStr = pwEdit.getText().toString();
                    	//idEdit.getText().toString())
                    	DBHandler dbhandler = DBHandler.open(login.this);
                    	Cursor cursor = dbhandler.select(idStr,pwStr );
                    	int cnt = cursor.getCount();
                		
                    	
                    	String pwStr2 =""; 
                    	if(cnt >0 ) pwStr2 = cursor.getString(cursor.getColumnIndex("pw"));                    	
                    	//String userId2 = cursor.getString(cursor.getColumnIndex("userid"));
                    	if(cnt<=0) {
                            Toast.makeText(
                            		login.this,
                                    "아이디오류", Toast.LENGTH_LONG)
                                    .show();
                    	/*if(cnt <=0) {
                            Toast.makeText(
                            		login.this,
                                    "아이디오류", Toast.LENGTH_LONG)
                                    .show();*/
                    	}else if(!pwStr.equals(pwStr2)){

                            Toast.makeText(
                            		login.this,
                                    "암호오류", Toast.LENGTH_LONG)
                                    .show();
                    	}else {
                           	loginFormLayout.setVisibility(View.GONE);
                        	loginProcLayout.setVisibility(View.VISIBLE);	
                      
                    }
                    }
                }).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
    	if(requestCode == 1){
    		if(resultCode == RESULT_OK){
    			LinearLayout btn = (LinearLayout)findViewById(R.id.loginForm);
				btn.setVisibility(View.GONE);
				LinearLayout btn2 = (LinearLayout)findViewById(R.id.loginProc);
				btn2.setVisibility(View.VISIBLE);
    		
				}
    		}	
    	}
 
  		}
  
