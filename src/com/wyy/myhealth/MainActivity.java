package com.wyy.myhealth;

import com.astuetz.PagerSlidingTabStrip;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.wyy.myhealth.contants.ConstantS;
import com.wyy.myhealth.pager.utils.SuperAwesomeCardFragment;
import com.wyy.myhealth.ui.discover.DiscoverFragment;
import com.wyy.myhealth.ui.personcenter.PersonCenterFragment;
import com.wyy.myhealth.ui.scan.ScanFragment;
import com.wyy.myhealth.ui.yaoyingyang.YaoyingyangFragment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity implements OnQueryTextListener, OnPageChangeListener {

	private static final String TAG=MainActivity.class.getSimpleName();
	private static final String POSTION="postion";
	private static int curpostion=0;
	private PagerSlidingTabStrip tabs;
	private ViewPager mainPager;
	private MyPagerAdapter adapter;
	
	/**
	 * 定位
	 */
	private LocationClient mLocationClient;
	
	/**
	 * 经度
	 */
	public static double Wlongitude = 0;
	/**
	 * 纬度
	 */
	public static double Wlatitude = 0;
	
	private ScanFragment scanFragment;
	
	private YaoyingyangFragment yaoyingyangFragment;
	
	private DiscoverFragment discoverFragment;
	
	private PersonCenterFragment personCenterFragment;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			
			initActionBar();
			
			tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
			tabs.setShouldExpand(true);
			mainPager = (ViewPager) findViewById(R.id.pager);
			adapter = new MyPagerAdapter(getSupportFragmentManager());

			mainPager.setAdapter(adapter);

			final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
					.getDisplayMetrics());
			mainPager.setPageMargin(pageMargin);

			tabs.setViewPager(mainPager);
			tabs.setOnPageChangeListener(this);
//			tabs.setTextColor(getResources().getColor(R.color.blue));
		}
		
		
		initLocation();
		
	}

	
	private void initActionBar(){
		SearchView searchView=new SearchView(this);
		getSupportActionBar().setCustomView(searchView, new ActionBar.LayoutParams(
				Gravity.RIGHT));
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_g_bg));
		searchView.setOnQueryTextListener(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopLocation();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		curpostion=mainPager.getCurrentItem();
		outState.putInt(POSTION, curpostion);
	}
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState!=null) {
			curpostion=savedInstanceState.getInt(POSTION, 0);
			mainPager.setCurrentItem(curpostion);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_saoyy) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "扫营养", "yao营养", "发现", "我的" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				
				return ScanFragment.newInstance(position);
				
			case 1:
				return YaoyingyangFragment.newInstance(position);
				
			case 2:
				
				return DiscoverFragment.newInstance(position);
			
			case 3:
				
				return PersonCenterFragment.newInstance(position);
				
			default:
				break;
			}
			return SuperAwesomeCardFragment.newInstance(position);
		}

	}
	
	/**
	 * 停止定位
	 */
	private void stopLocation(){
		if (mLocationClient != null) {
			mLocationClient.stop();
			mLocationClient = null;
		}
	}
	
	/**
	 * 初始化百度定位
	 */
	private void initLocation() {
		mLocationClient = new LocationClient(MainActivity.this);
		mLocationClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceivePoi(BDLocation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onReceiveLocation(BDLocation arg0) {
				// TODO Auto-generated method stub
				Wlongitude = arg0.getLongitude();
				Wlatitude = arg0.getLatitude();

//				 Log.i(TAG, "经度:"+Wlongitude+"纬度:"+Wlatitude);
			}
		});

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocationClient.setLocOption(option);

		mLocationClient.start();

		mLocationClient.requestLocation();

	}


	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		sendFoodkey(""+arg0);
		return true;
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		if (arg0==0) {
			sendPageIndex(arg0);
		}
		
	}

	
	private void sendPageIndex(int index){
		Intent intent=new Intent();
		intent.setAction(ConstantS.PAGE_INDEX_CHANG);
		intent.putExtra("index", index);
		sendBroadcast(intent);
	}
	
	
	private void sendFoodkey(String key){
		Intent intent=new Intent();
		intent.setAction(ConstantS.ACTION_SEARCH_FOOD);
		intent.putExtra("key", key);
		sendBroadcast(intent);
		mainPager.setCurrentItem(1);
	}
	

	
}
