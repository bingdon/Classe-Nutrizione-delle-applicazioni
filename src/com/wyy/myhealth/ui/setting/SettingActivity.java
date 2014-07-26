package com.wyy.myhealth.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.wyy.myhealth.R;
import com.wyy.myhealth.imag.utils.LoadImageUtils;
import com.wyy.myhealth.ui.baseactivity.BaseActivity;
import com.wyy.myhealth.ui.baseactivity.interfacs.ActivityInterface;

public class SettingActivity extends BaseActivity implements ActivityInterface {

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		getSupportActionBar().setTitle(R.string.action_settings);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		context=this;
		findViewById(R.id.feed_back_).setOnClickListener(listener);
		findViewById(R.id.app_version_).setOnClickListener(listener);
		findViewById(R.id.clear_coach_).setOnClickListener(listener);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.feed_back_:
				showFeedback();
				break;

			case R.id.app_version_:
				showVersion();
				break;

			case R.id.clear_coach_:
				LoadImageUtils.clear_Coach(context);
				break;

			default:
				break;
			}
		}
	};

	
	
	private void showFeedback(){
		startActivity(new Intent(context, FeedbackActivity.class));
	}
	
	private void showVersion(){
		startActivity(new Intent(context, AboutVersion.class));
	}
	
}
