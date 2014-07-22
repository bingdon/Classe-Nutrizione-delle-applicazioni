package com.wyy.myhealth.ui.login;

import com.wyy.myhealth.MainActivity;
import com.wyy.myhealth.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends ActionBarActivity {

	private Button loginButton;
	
	private EditText accounEditText;
	
	private EditText passwordEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_g_bg));
		initUI();
	}
	
	private void initUI(){
		loginButton=(Button)findViewById(R.id.login_btn);
		loginButton.setOnClickListener(listener);
	}
	
	
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.login_btn:
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
				finish();
				break;

			default:
				break;
			}
		}
	};
	
}
