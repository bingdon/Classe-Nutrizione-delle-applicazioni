package com.wyy.myhealth.ui.shaiyishai;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wyy.myhealth.R;
import com.wyy.myhealth.ui.baseactivity.AbstractlistActivity;
import com.wyy.myhealth.ui.collect.CollectActivity;

public class ShaiyishaiActivity extends AbstractlistActivity {

	private ShaiyishaiFragment shaiyishaiFragment;
	
	@Override
	protected void onInitFragment() {
		// TODO Auto-generated method stub
		super.onInitFragment();
		shaiyishaiFragment=new ShaiyishaiFragment();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.wrapper, shaiyishaiFragment).commit();

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.shai_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		ActionBar actionBar=getSupportActionBar();
		actionBar.setTitle(R.string.shayishai);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.publis_shai:
			startActivity(new Intent(context, PublishActivity.class));
			break;

		case R.id.mycollect:
			startActivity(new Intent(context, CollectActivity.class));
			break;
			
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (shaiyishaiFragment.getSendView().isShown()) {
				shaiyishaiFragment.getSendView().setVisibility(View.GONE);
				return true;
			}
		
		}

		return super.onKeyDown(keyCode, event);
	}
	
	
}
