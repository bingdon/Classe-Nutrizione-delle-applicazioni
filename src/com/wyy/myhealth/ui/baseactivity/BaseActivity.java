package com.wyy.myhealth.ui.baseactivity;

import java.lang.reflect.Method;

import com.wyy.myhealth.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class BaseActivity extends ActionBarActivity {

	protected Activity context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		context=this;
		
		onInitActionBar();
		onInitUI();
		onInitFragment();
	}

	protected void onInitFragment() {

	}
	
	protected  void onInitUI(){
		
	}

	protected void onInitActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.actionbar_g_bg));
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub

		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);

				} catch (Exception e) {
				}
			}
		}

		return super.onMenuOpened(featureId, menu);
	}

	

}
