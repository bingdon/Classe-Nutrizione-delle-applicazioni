package com.wyy.myhealth.ui.healthbar;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

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
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.healthpass, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.msglist:
			startActivity(new Intent(context, MsgListActivity.class));
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
