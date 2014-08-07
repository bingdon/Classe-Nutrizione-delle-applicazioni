package com.wyy.myhealth.ui.shaiyishai;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyy.myhealth.R;
import com.wyy.myhealth.app.PreferencesFoodsInfo;
import com.wyy.myhealth.app.WyyApplication;
import com.wyy.myhealth.bean.ListDataBead;
import com.wyy.myhealth.db.utils.ShaiDatebaseUtils;
import com.wyy.myhealth.http.AsyncHttpResponseHandler;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.support.collect.CollectUtils;
import com.wyy.myhealth.ui.absfragment.ListBaseFragment;
import com.wyy.myhealth.ui.absfragment.adapter.ShaiYiSaiAdapter.ShaiItemOnclickListener;
import com.wyy.myhealth.ui.customview.BingListView.IXListViewListener;
import com.wyy.myhealth.ui.fooddetails.FoodDetailsActivity;
import com.wyy.myhealth.ui.photoPager.PhotoPagerActivity;

public class ShaiyishaiFragment extends ListBaseFragment implements
		ShaiItemOnclickListener, OnRefreshListener, IXListViewListener,
		OnItemClickListener {

	// 数据库ID
	private int _id = 0;
	// 评论View
	private View sendView;
	// 评论编辑
	private EditText sendEditText;
	// 评论按钮
	private Button sendButton;
	// 美食ID
	private String shaiFoodsid = "";
	// 心情ID
	private String shaimoodsid = "";

	public View getSendView() {
		return sendView;
	}

	@Override
	protected void initView(View v) {
		// TODO Auto-generated method stub
		super.initView(v);
		sendView = v.findViewById(R.id.send_v);
		mRefreshLayout.setOnRefreshListener(this);
		mListView.setXListViewListener(this);
		initSendView(sendView);
		mAdapter.setOnClickListener(this);
		mListView.setOnItemClickListener(this);
	}

	@Override
	protected void onGetLastData() {
		// TODO Auto-generated method stub
		super.onGetLastData();
		getLastStatus();
	}

	@SuppressWarnings("unchecked")
	private void getLastStatus() {
		ShaiDatebaseUtils shaiDatebaseUtils = new ShaiDatebaseUtils(
				getActivity());
		lastDataBeads = (List<ListDataBead>) shaiDatebaseUtils.queryData();
		if (null != lastDataBeads) {
			if (lastDataBeads.size() > 0) {
				json = lastDataBeads.get(0).getJsondata();
				postion = lastDataBeads.get(0).getPostion();
				_id = lastDataBeads.get(0).getPostion();
			}

		}
		shaiDatebaseUtils.close();

		if (TextUtils.isEmpty(json)) {
			reshShayiSai("0", limit);
		} else {
			pareseJson(json);
			reshShayiSai("0", limit);

		}

	}

	@Override
	protected void saveJsontoDb(String json, int postion) {
		// TODO Auto-generated method stub
		super.saveJsontoDb(json, postion);

		if (TextUtils.isEmpty(json)) {
			return;
		}
		ShaiDatebaseUtils shaiDatebaseUtils = new ShaiDatebaseUtils(
				getActivity());
		long m;
		if (lastDataBeads.size() == 0) {
			m = shaiDatebaseUtils.insert(json, postion);
			Log.i(TAG, "插入:" + m);
		} else {
			m = shaiDatebaseUtils.update(json, postion, 1);
			if (m == 0) {
				shaiDatebaseUtils.update(json, postion, _id);
			}
			Log.i(TAG, "更新:" + m);
		}

		shaiDatebaseUtils.close();

	}

	@Override
	protected void reshShayiSai(String first, String limit) {
		// TODO Auto-generated method stub
		super.reshShayiSai(first, limit);
		HealthHttpClient.doHttpGetShayiSai(first, limit, reshHandler);
	}

	@Override
	protected void getLoadMore(String first, String limit) {
		// TODO Auto-generated method stub
		super.getLoadMore(first, limit);
		HealthHttpClient.doHttpGetShayiSai(first, limit, parseHandler);
	}

	@Override
	public void onUserPicClick(int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCommentClick(int position) {
		// TODO Auto-generated method stub

		Toast.makeText(getActivity(), "数字z:"+position, Toast.LENGTH_LONG).show();
		
		if (thList.get(position).containsKey("foodsid")) {
			shaiFoodsid = thList.get(position).get("foodsid").toString();
			shaimoodsid = "";

		} else if (thList.get(position).containsKey("moodsid")) {
			Log.i(TAG, "" + thList.get(position).get("moodsid"));
			shaimoodsid = thList.get(position).get("moodsid").toString();
			shaiFoodsid = "";

		}

		sendView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onZanClick(int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollectClick(int position) {
		// TODO Auto-generated method stub
		
		if (thList.get(position).containsKey("foodsid")) {
			String foodsid = thList.get(position).get("foodsid").toString();
			CollectUtils.collectFood(foodsid, getActivity());

		} else if (thList.get(position).containsKey("moodsid")) {
			String moodsid = thList.get(position).get("moodsid").toString();
			CollectUtils.postMoodCollect(moodsid, getActivity());
		}
	}

	@Override
	public void onPicClick(int listPostino, int picPostion) {
		// TODO Auto-generated method stub
		if (thList.get(listPostino).containsKey("grid_pic")) {
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) thList.get(listPostino).get(
					"grid_pic");
			Intent intent = new Intent();
			intent.putStringArrayListExtra("imgurls", (ArrayList<String>) list);
			intent.putExtra("postion", picPostion);
			intent.setClass(getActivity(), PhotoPagerActivity.class);
			startActivity(intent);
		}
	}

	private void initSendView(View v) {
		sendButton = (Button) v.findViewById(R.id.send_msg_btn);
		sendEditText = (EditText) v.findViewById(R.id.send_msg_editText);
		sendButton.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.send_msg_btn:
				if (!TextUtils.isEmpty(shaiFoodsid)) {
					sendComment(shaiFoodsid);
				} else if (!TextUtils.isEmpty(shaimoodsid)) {
					sendMoodComment(shaimoodsid);
				}

				break;

			default:
				break;
			}
		}
	};

	/**
	 * 发送评论
	 * 
	 * @param foodsid
	 *            食物ID
	 */
	private void sendComment(String foodsid) {
		if (TextUtils.isEmpty(sendEditText.getText().toString())) {
			Toast.makeText(getActivity(), R.string.nullcontentnotice,
					Toast.LENGTH_LONG).show();
			return;
		}
		HealthHttpClient.doHttpPostComment(foodsid, sendEditText.getText()
				.toString(), "5", WyyApplication.getInfo().getId(),
				commentHandler);
	}

	/**
	 * 发送评论
	 * 
	 * @param foodsid
	 *            食物ID
	 */
	private void sendMoodComment(String moodsid) {
		if (TextUtils.isEmpty(sendEditText.getText().toString())) {
			Toast.makeText(getActivity(), R.string.nullcontentnotice,
					Toast.LENGTH_LONG).show();
			return;
		}
		HealthHttpClient.postMoodComment(WyyApplication.getInfo().getId(),
				moodsid, sendEditText.getText().toString(), commentHandler);
	}

	private AsyncHttpResponseHandler commentHandler = new AsyncHttpResponseHandler() {

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			sendButton.setEnabled(false);
			sendButton.setBackgroundColor(getResources().getColor(
					R.color.home_txt));
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			super.onFinish();
			sendButton.setEnabled(true);
			sendButton.setText(R.string.send);
			sendButton.setBackgroundColor(getResources().getColor(
					R.color.transparent));
		}

		@Override
		public void onSuccess(String content) {
			// TODO Auto-generated method stub
			super.onSuccess(content);
			Log.i(TAG, "返回:" + content);
			Toast.makeText(getActivity(), R.string.comment_success_,
					Toast.LENGTH_LONG).show();
			sendEditText.setText("");
			sendView.setVisibility(View.GONE);
			shaiFoodsid = "";
			shaimoodsid = "";
		}

		@Override
		public void onFailure(Throwable error, String content) {
			// TODO Auto-generated method stub
			super.onFailure(error, content);
			Toast.makeText(getActivity(), R.string.comment_faliure,
					Toast.LENGTH_LONG).show();
		}

	};

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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (thList.get(position).containsKey("foodsid")) {
			PreferencesFoodsInfo.setfoodId(getActivity(), thList.get(position)
					.get("foodsid") + "");
			startActivity(new Intent(getActivity(), FoodDetailsActivity.class));
		}

	}

}
