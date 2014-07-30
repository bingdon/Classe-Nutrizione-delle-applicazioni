package com.wyy.myhealth.ui.login;

import com.wyy.myhealth.MainActivity;
import com.wyy.myhealth.R;
import com.wyy.myhealth.app.WyyApplication;
import com.wyy.myhealth.bean.PersonalInfo;
import com.wyy.myhealth.contants.ConstantS;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.imag.utils.Bmprcy;
import com.wyy.myhealth.ui.personcenter.PersonCenterFragment;
import com.wyy.myhealth.utils.ImageUtil;
import com.wyy.myhealth.utils.SavePersonInfoUtlis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
		initFilter();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
	
	private void initUI(){
		loginButton=(Button)findViewById(R.id.login_btn);
		loginButton.setOnClickListener(listener);
		findViewById(R.id.weimalogin).setOnClickListener(listener);
	}
	
	
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.login_btn:
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
//				finish();
				break;

			case R.id.weimalogin:
				startActivity(new Intent(LoginActivity.this, MicroCodeLoginActivity.class));
				break;
				
			default:
				break;
			}
		}
	};
	
	private void initFilter(){
		IntentFilter filter=new IntentFilter();
		filter.addAction(ConstantS.ACTION_LOGIN_FINISH);
		registerReceiver(receiver, filter);
	}
	
	private BroadcastReceiver receiver=new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			LoginActivity.this.finish();
		}
	};
	
	
}
