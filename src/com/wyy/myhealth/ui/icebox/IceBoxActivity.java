package com.wyy.myhealth.ui.icebox;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wyy.myhealth.R;
import com.wyy.myhealth.ui.baseactivity.BaseActivity;
import com.wyy.myhealth.ui.baseactivity.interfacs.ActivityInterface;

public class IceBoxActivity extends BaseActivity implements ActivityInterface{

	
	private ImageView door_left;
	
	private ImageView door_right;
	
	private LinearLayout doorlayout;
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
	public void initView() {
		// TODO Auto-generated method stub
		door_left=(ImageView)findViewById(R.id.door_l);
		door_right=(ImageView)findViewById(R.id.door_r);
		doorlayout=(LinearLayout)findViewById(R.id.ice_box_door);
		animationHandler.sendEmptyMessageDelayed(0, 1000);
		initData();
	}


	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void startOpenAnimation(){
		TranslateAnimation translateAnimation = new TranslateAnimation(0, -door_left.getWidth(), 0, 0);
		translateAnimation.setDuration(5000);
		door_left.startAnimation(translateAnimation);
		
		TranslateAnimation translateAnimation1 = new TranslateAnimation(0, door_right.getWidth(), 0, 0);
		translateAnimation1.setDuration(5000);
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
	
	
	private Handler animationHandler=new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			startOpenAnimation();
			return false;
		}
	});
}
