package com.wyy.myhealth.ui.healthbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wyy.myhealth.R;
import com.wyy.myhealth.app.WyyApplication;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.ui.absfragment.HealthPassBase;
import com.wyy.myhealth.ui.customview.BingListView.IXListViewListener;

public class HealPassFragment extends HealthPassBase implements
		OnRefreshListener, IXListViewListener {

	private ImageLoader imageLoader = ImageLoader.getInstance();

	private DisplayImageOptions options;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.pic_preview)
				.showImageForEmptyUri(R.drawable.pic_preview)
				.showImageOnFail(R.drawable.pic_preview).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true).build();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	protected void onGetLastData() {
		// TODO Auto-generated method stub
		super.onGetLastData();
		json = getLast_Result();
		Log.i(TAG, "返回健康:" + json);
		if (TextUtils.isEmpty(json)) {
			reshShayiSai("0", limit);
		} else {
			pareseJson(json);
			reshShayiSai("0", limit);

		}
	}

	@Override
	protected void initView(View v) {
		// TODO Auto-generated method stub
		super.initView(v);
		mRefreshLayout.setOnRefreshListener(this);
		mListView.setXListViewListener(this);

		publishV.setOnClickListener(listener);
		bgImageView.setOnClickListener(listener);

		imageLoader.displayImage(HealthHttpClient.IMAGE_URL
				+ WyyApplication.getInfo().getHeadimage(), userhead, options);
		
		username.setText(""+WyyApplication.getInfo().getUsername());

	}

	@Override
	protected void reshShayiSai(String first, String limit) {
		// TODO Auto-generated method stub
		super.reshShayiSai(first, limit);
		if (null == WyyApplication.getInfo().getId()) {
			return;
		}
		HealthHttpClient.userFoodsAndMoods2(WyyApplication.getInfo().getId(),
				first, limit, reshHandler);
	}

	@Override
	protected void getLoadMore(String first, String limit) {
		// TODO Auto-generated method stub
		super.getLoadMore(first, limit);
		HealthHttpClient.userFoodsAndMoods2(WyyApplication.getInfo().getId(),
				first, limit, parseHandler);
	}

	@Override
	protected void saveJsontoDb(String json, int postion) {
		// TODO Auto-generated method stub
		super.saveJsontoDb(json, postion);
		saveCurrent_Result(json);
	}

	private String getLast_Result() {
		String result = getActivity().getSharedPreferences(TAG,
				Context.MODE_PRIVATE).getString("result", "");
		return result;
	}

	/**
	 * 保存此次数据
	 * 
	 * @param result
	 */
	private void saveCurrent_Result(String result) {
		SharedPreferences preferences = getActivity().getSharedPreferences(TAG,
				Context.MODE_PRIVATE);

		Editor editor = preferences.edit();

		editor.putString("result", result);
		editor.commit();

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		if (!loadflag) {
			reshShayiSai("0", limit);
		}
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		if (!loadflag) {
			getLoadMore("" + first, limit);
		}
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.take_pic:
				startActivity(new Intent(getActivity(), PublishMoodActivity.class));
				break;

			case R.id.user_bg:

				break;

			default:
				break;
			}
		}
	};

}
