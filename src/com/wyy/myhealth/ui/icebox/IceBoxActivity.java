package com.wyy.myhealth.ui.icebox;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wyy.myhealth.R;
import com.wyy.myhealth.app.WyyApplication;
import com.wyy.myhealth.bean.IceBoxFoodBean;
import com.wyy.myhealth.contants.ConstantS;
import com.wyy.myhealth.http.JsonHttpResponseHandler;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.http.utils.JsonUtils;
import com.wyy.myhealth.ui.baseactivity.BaseActivity;
import com.wyy.myhealth.ui.baseactivity.interfacs.ActivityInterface;
import com.wyy.myhealth.ui.customview.BingListView;
import com.wyy.myhealth.utils.BingLog;

public class IceBoxActivity extends BaseActivity implements ActivityInterface,
		OnRefreshListener {

	private static final String TAG = IceBoxActivity.class.getSimpleName();

	private ImageView door_left;

	private ImageView door_right;

	private LinearLayout doorlayout;

	private BingListView iceListv;

	private SwipeRefreshLayout mSwipeRefreshLayout;

	private List<List<IceBoxFoodBean>> iceBoxFoodBeansList = new ArrayList<>();

	private List<IceBoxFoodBean> fruitsList = new ArrayList<>();

	private List<IceBoxFoodBean> vegetablesList = new ArrayList<>();

	private List<IceBoxFoodBean> meatList = new ArrayList<>();

	private List<IceBoxFoodBean> staplefoodsList = new ArrayList<>();

	private List<IceBoxFoodBean> otherList = new ArrayList<>();
	
	private String json="";
	
	private IceBoxMainAdapter  iceBoxMainAdapter;

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		getSupportActionBar().setTitle(R.string.my_ice_box);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ice_box);
		initView();
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		saveCurrent_Result(json);
	}
	
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		door_left = (ImageView) findViewById(R.id.door_l);
		door_right = (ImageView) findViewById(R.id.door_r);
		doorlayout = (LinearLayout) findViewById(R.id.ice_box_door);
		animationHandler.sendEmptyMessageDelayed(0, 1000);
		iceListv = (BingListView) findViewById(R.id.ice_box_list_v);
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.list_swipe);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		initData();
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
		iceBoxMainAdapter=new IceBoxMainAdapter(iceBoxFoodBeansList, context);
		iceListv.setAdapter(iceBoxMainAdapter);
		json=getLast_Result();
		if (TextUtils.isEmpty(json)) {
			reshIceBoxFood();
		}else {
			paresJson(json);
			reshIceBoxFood();
		}
	}

	private void startOpenAnimation() {
		TranslateAnimation translateAnimation = new TranslateAnimation(0,
				-door_left.getWidth(), 0, 0);
		translateAnimation.setDuration(3000);
		door_left.startAnimation(translateAnimation);

		TranslateAnimation translateAnimation1 = new TranslateAnimation(0,
				door_right.getWidth(), 0, 0);
		translateAnimation1.setDuration(3000);
		door_right.startAnimation(translateAnimation1);

		translateAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				doorlayout.setVisibility(View.GONE);
			}
		});

	}

	private Handler animationHandler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			startOpenAnimation();
			return false;
		}
	});

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mSwipeRefreshLayout.setRefreshing(false);
	}

	private void reshIceBoxFood() {
		HealthHttpClient.getIceBoxFood(/*WyyApplication.getInfo().getId()*/"6015", "0", null, responseHandler);
	}

	
	
	private JsonHttpResponseHandler responseHandler=new JsonHttpResponseHandler(){


		public void onSuccess(String content) {
			super.onSuccess(content);
			BingLog.i(TAG, "冰箱数据:"+content);
			if (json==content) {
				Toast.makeText(context, R.string.nonewmsg, Toast.LENGTH_LONG).show();
			}else {
				paresJson(content);
			}
			
		};
		
		@Override
		public void onFailure(Throwable e, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(e, errorResponse);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			super.onFinish();
		}
		
	};
	
	
	private void paresJson(String content){
		try {
			
			JSONObject object=new JSONObject(content);
			
			if (JsonUtils.isSuccess(object)) {
				JSONArray foods=object.getJSONArray("foods");
				int length=foods.length();
				for (int i = 0; i < length; i++) {
					IceBoxFoodBean iceBoxFoodBean=JsonUtils.getIceBoxFoodBean(foods.getJSONObject(i));
					if (iceBoxFoodBean.getType().equals("1")) {
						fruitsList.add(iceBoxFoodBean);
						vegetablesList.add(iceBoxFoodBean);
						meatList.add(iceBoxFoodBean);
						staplefoodsList.add(iceBoxFoodBean);
						otherList.add(iceBoxFoodBean);
						
					}
				}
				
				iceBoxFoodBeansList.add(fruitsList);
				iceBoxFoodBeansList.add(vegetablesList);
				iceBoxFoodBeansList.add(meatList);
				iceBoxFoodBeansList.add(staplefoodsList);
				iceBoxFoodBeansList.add(otherList);
				iceBoxMainAdapter.notifyDataSetChanged();
				json=content;
			}else {
				Toast.makeText(context, R.string.nodata, Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(context, R.string.parseerror, Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * 保存此次数据
	 * 
	 * @param result
	 */
	private void saveCurrent_Result(String result) {
		if (TextUtils.isEmpty(result)) {
			return;
		}
		SharedPreferences preferences = getSharedPreferences(TAG,
				Context.MODE_PRIVATE);

		Editor editor = preferences.edit();

		editor.putString(ConstantS.RESULT, result);
		editor.commit();

	}

	private String getLast_Result() {
		String result = getSharedPreferences(TAG, Context.MODE_PRIVATE)
				.getString(ConstantS.RESULT, "");
		return result;
	}

}
