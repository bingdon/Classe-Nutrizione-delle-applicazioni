package com.wyy.myhealth.ui.healthbar;

import android.support.v7.app.ActionBar;

import com.wyy.myhealth.R;
import com.wyy.myhealth.ui.baseactivity.AbstractlistActivity;


public class HealthPassActivity extends AbstractlistActivity{

	@Override
	protected void onInitFragment() {
		// TODO Auto-generated method stub
		super.onInitFragment();
		getSupportFragmentManager().beginTransaction()
		.add(R.id.wrapper, new HealPassFragment()).commit();
	}
	
	
	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		ActionBar actionBar=getSupportActionBar();
		actionBar.setTitle(R.string.healthpass);
	}
	
}
