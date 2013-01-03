package com.example.project01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.preference.PreferenceActivity;


public  class Setting extends Activity implements View.OnClickListener{
	    protected static final EditText String = null;
	    void finishActivity(){
	     	finish();
	     }	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.setting);

	        Button okBtn = (Button)findViewById(R.id.okBtn);
	     	okBtn.setOnClickListener(new View.OnClickListener() {
				

	
	     	@Override
			public void onClick(View v) {
				Intent intent = new Intent(Setting.this, login.class );
				setResult(RESULT_OK,intent);				
			    finishActivity();
			  
			  
			}
		});

	
}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
}