package com.wyy.myhealth.ui.yaoyingyang;

import com.wyy.myhealth.R;
import com.wyy.myhealth.ui.baseactivity.AbstractlistActivity;

public class TodayFoodRecActivity extends AbstractlistActivity {

	@Override
	protected void onInitFragment() {
		// TODO Auto-generated method stub
		super.onInitFragment();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.wrapper, new TodayFoodRecFragment()).commit();
	}

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		getSupportActionBar().setTitle(R.string.today_food);
	}

}
