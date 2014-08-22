package com.wyy.myhealth.ui.discover;

import com.wyy.myhealth.R;
import com.wyy.myhealth.bean.MoodaFoodBean;
import com.wyy.myhealth.contants.ConstantS;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.imag.utils.LoadImageUtils;
import com.wyy.myhealth.ui.healthbar.HealthPassActivity;
import com.wyy.myhealth.ui.healthrecorder.HealthRecorderActivity;
import com.wyy.myhealth.ui.icebox.IceBoxActivity;
import com.wyy.myhealth.ui.message.MessageTListActivity;
import com.wyy.myhealth.ui.shaiyishai.ShaiyishaiActivity;
import com.wyy.myhealth.utils.ShareUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DiscoverFragment extends Fragment {

	private ImageView shaiNoticeView;
	
	private ImageView healthNoticeView;

	public static DiscoverFragment newInstance(int postion) {
		DiscoverFragment discoverFragment = new DiscoverFragment();
		discoverFragment.setArguments(new Bundle());
		return discoverFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initFilter();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getActivity().unregisterReceiver(disReceiver);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.discover_lay, container,
				false);
		initView(rootView);
		return rootView;
	}

	private void initView(View v) {
		shaiNoticeView = (ImageView) v.findViewById(R.id.shaiyishai_notice);
		healthNoticeView = (ImageView) v.findViewById(R.id.health_notice);
		v.findViewById(R.id.shaiyishai_fr).setOnClickListener(listener);
		v.findViewById(R.id.healthpass_fr).setOnClickListener(listener);
		v.findViewById(R.id.healthrecord_fr).setOnClickListener(listener);
		v.findViewById(R.id.bingxiang_fr).setOnClickListener(listener);
		v.findViewById(R.id.tellfr_fr).setOnClickListener(listener);
		v.findViewById(R.id.msgbox_fr).setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			switch (v.getId()) {
			case R.id.shaiyishai_fr:
				showShaiyishai();
				break;

			case R.id.healthpass_fr:
				showHealthPass();
				break;

			case R.id.healthrecord_fr:
				showHealthRecorder();
				break;

			case R.id.bingxiang_fr:
				showIceBoxActivity();
				break;

			case R.id.tellfr_fr:
				ShareUtils.share2Fre(getActivity());
				break;

			case R.id.msgbox_fr:
				showMsgList();
				break;

			default:
				break;
			}

		}
	};

	private void showShaiyishai() {
		shaiNoticeView.setVisibility(View.GONE);
		sendCanelNotice();
		startActivity(new Intent(getActivity(), ShaiyishaiActivity.class));
	}

	private void showHealthPass() {
		healthNoticeView.setVisibility(View.GONE);
		sendCanelNotice();
		startActivity(new Intent(getActivity(), HealthPassActivity.class));
	}

	private void showMsgList() {
		startActivity(new Intent(getActivity(), MessageTListActivity.class));
	}

	private void showHealthRecorder() {
		startActivity(new Intent(getActivity(), HealthRecorderActivity.class));
	}

	private void showIceBoxActivity() {
		startActivity(new Intent(getActivity(), IceBoxActivity.class));
	}

	private void initFilter() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConstantS.ACTION_SEND_SHAI);
		filter.addAction(ConstantS.NEW_FOOD_COMMENT);
		getActivity().registerReceiver(disReceiver, filter);
	}

	private BroadcastReceiver disReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(ConstantS.ACTION_SEND_SHAI)) {
				handlerShai(intent);
			}else if (action.equals(ConstantS.NEW_FOOD_COMMENT)) {
				handlerHps();
			}

		}
	};

	private void handlerShai(Intent intent) {
		MoodaFoodBean moodaFoodBean = (MoodaFoodBean) intent
				.getSerializableExtra("shai");
		if (moodaFoodBean != null) {
			shaiNoticeView.setVisibility(View.VISIBLE);
			LoadImageUtils.loadImage4ImageV(shaiNoticeView,
					HealthHttpClient.IMAGE_URL
							+ moodaFoodBean.getUser().getHeadimage());
		}
	}

	private void handlerHps(){
		healthNoticeView.setVisibility(View.VISIBLE);
	}
	
	private void sendCanelNotice(){
		Intent intent=new Intent(ConstantS.ACTION_SEND_CANEL_NOTICE);
		getActivity().sendBroadcast(intent);
	}
	
}
