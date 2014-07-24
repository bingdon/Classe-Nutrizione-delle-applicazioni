package com.wyy.myhealth.ui.discover;

import com.wyy.myhealth.R;
import com.wyy.myhealth.ui.healthbar.HealthPassActivity;
import com.wyy.myhealth.ui.healthrecorder.HealthRecorderActivity;
import com.wyy.myhealth.ui.message.MessageTListActivity;
import com.wyy.myhealth.ui.shaiyishai.ShaiyishaiActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class DiscoverFragment extends Fragment {

	public static DiscoverFragment newInstance(int postion) {
		DiscoverFragment discoverFragment = new DiscoverFragment();
		discoverFragment.setArguments(new Bundle());
		return discoverFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.discover_lay, null);
		initView(rootView);
		return rootView;
	}

	private void initView(View v) {
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
				
				break;

			case R.id.tellfr_fr:

				break;

			case R.id.msgbox_fr:
				showMsgList();
				break;

			default:
				break;
			}

		}
	};

	
	private void showShaiyishai(){
		startActivity(new Intent(getActivity(), ShaiyishaiActivity.class));
	}
	
	
	private void showHealthPass(){
		startActivity(new Intent(getActivity(), HealthPassActivity.class));
	}
	
	private void showMsgList(){
		startActivity(new Intent(getActivity(), MessageTListActivity.class));
	}
	
	private void showHealthRecorder(){
		startActivity(new Intent(getActivity(), HealthRecorderActivity.class));
	}
	
}
