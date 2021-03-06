package com.wyy.myhealth.ui.fooddetails;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wyy.myhealth.MainActivity;
import com.wyy.myhealth.R;
import com.wyy.myhealth.app.PreferencesFoodsInfo;
import com.wyy.myhealth.bean.Comment;
import com.wyy.myhealth.bean.NearFoodBean;
import com.wyy.myhealth.bean.PersonalInfo;
import com.wyy.myhealth.contants.ConstantS;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.support.collect.CollectUtils;
import com.wyy.myhealth.ui.baseactivity.BaseActivity;
import com.wyy.myhealth.ui.mapfood.CommercialMapActivity;
import com.wyy.myhealth.ui.mapfood.MapFoodsActivity;
import com.wyy.myhealth.ui.yaoyingyang.YaoyingyangFragment;
import com.wyy.myhealth.utils.DistanceUtils;
import com.wyy.myhealth.utils.FoodsUtil;

public class FoodDetailsActivity extends BaseActivity {

	private static final String TAG = FoodDetailsActivity.class.getSimpleName();
	private ProgressBar loadingBar;
	private NearFoodBean foods;
	private ArrayList<Comment> comments;

	/**
	 * ��Ʒ����
	 */
	private ArrayList<Comment> menuComlist = new ArrayList<Comment>();

	private PersonalInfo info;

	private int starsindex = 0;
	private int goodsindex = 0;

	private ImageView foodpic;

	private TextView foodtag;

	private ImageView tasteLevel;

	private ImageView ishanghu;

	private TextView distance;

	private ImageView userHead;

	private TextView userName;

	private TextView userTag;

	private TextView userSummary;

	private ImageLoader imageLoader = ImageLoader.getInstance();

	private DisplayImageOptions options;

	private ScrollView scrollView;

	private FrameLayout commentInfos;

	private FrameLayout healthzs;

	private String mydiatance = "";

	private String foodid = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.activity_food_details);
		initView();
		foodid = PreferencesFoodsInfo.getfoodId(this);
		foodsDetail(foodid);
	}

	private void initView() {
		mydiatance = getIntent().getStringExtra("distance");
		scrollView = (ScrollView) findViewById(R.id.details_scroll);
		foodpic = (ImageView) findViewById(R.id.foodpic);
		foodtag = (TextView) findViewById(R.id.food_tag);
		tasteLevel = (ImageView) findViewById(R.id.food_taste_level);
		ishanghu = (ImageView) findViewById(R.id.isshanghu);
		distance = (TextView) findViewById(R.id.food_distance);
		userHead = (ImageView) findViewById(R.id.userheadimg);
		userName = (TextView) findViewById(R.id.username);
		userTag = (TextView) findViewById(R.id.usertag);
		userSummary = (TextView) findViewById(R.id.user_summary_txt);

		commentInfos = (FrameLayout) findViewById(R.id.comment_fra);
		healthzs = (FrameLayout) findViewById(R.id.healthzs_fra);

		commentInfos.setOnClickListener(listener);
		healthzs.setOnClickListener(listener);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.pic_loading_)
				.showImageForEmptyUri(R.drawable.pic_empty)
				.showImageOnFail(R.drawable.pic_failure).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true).build();
	}

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.title_activity_food_details);
		loadingBar = new ProgressBar(this);
		actionBar.setCustomView(loadingBar, new ActionBar.LayoutParams(
				Gravity.RIGHT));
		actionBar.setDisplayShowCustomEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.food_details, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.comment:
			loopCommentActivity();
			break;

		case R.id.collect:
			CollectUtils.collectFood(foodid, context);
			break;

		case R.id.canel:

			break;

		case R.id.call:

			break;

		case R.id.action_share:

			break;
			
		case R.id.loopmap:
			
			loopCommercialMap();
			
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.comment_fra:
				loopCommentInfo();
				break;

			case R.id.healthzs_fra:

				break;
				

			default:
				break;
			}

		}
	};

	private void foodsDetail(String foodid) {

		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				parseFoods(response);
				// initData();
				// InitViewPager();
				// initList();

			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				super.onFailure(arg0, arg1, arg2, arg3);

			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				loadingBar.setVisibility(View.GONE);
			}

		};
		FoodsUtil.doHttpDetails(foodid, res);
	}

	private void parseFoods(String response) {

		try {

			
			JSONObject result = new JSONObject(response);

			JSONObject obj = result.getJSONObject("foods");

			try {
				Gson gson = new Gson();
				foods = gson.fromJson(obj.toString(), NearFoodBean.class);
				foods.setDistance(""
						+ DistanceUtils.getDistance(MainActivity.Wlongitude,
								MainActivity.Wlatitude,
								foods.getCommercialLon(),
								foods.getCommercialLat()));
			} catch (Exception e) {
				// TODO: handle exception
				Log.e(TAG, "��������");
			}

			comments = new ArrayList<Comment>();
			JSONArray json = result.getJSONArray("overviews");// �Ķ�
			JSONArray commentsArray = result.getJSONArray("comments");

			int length = json.length();
			System.out.println("length==" + length);
			for (int i = 0; i < length; i++) {
				Comment mcomment = new Comment();
				obj = json.getJSONObject(i);

				try {
					Gson gson = new Gson();
					mcomment = gson.fromJson(obj.toString(), Comment.class);
					foods.setEnergy(mcomment.getEnergy());
					foods.setReasonable(mcomment.getReasonable());
					comments.add(mcomment);
				} catch (Exception e) {
					// TODO: handle exception
					Log.e(TAG, "��������");
				}
				// mcomment.setVitamin(obj.getString("vitamin"));
				// mcomment.setProtein(obj.getString("protein"));
				// mcomment.setMineral(obj.getString("mineral"));
				// mcomment.setSugar(obj.getString("sugar"));
				// mcomment.setFat(obj.getString("fat"));
				// mcomment.setEnergy(obj.getString("energy"));
				// // mcomment.setContent(obj.getString("content"));
				// //
				// mcomment.setContent(commentsArray.getJSONObject(i).getString("content"));
				// // comment.setUserheadimage(obj.getString("userheadimage"));
				// // mcomment.setUsername(obj.getString("username"));
				// mcomment.setReasonable(obj.getString("reasonable"));

				// Log.i("comments", "-------------" + comments);
			}

			int commlength = commentsArray.length();
			/*********** �������� ************/
			for (int i = 0; i < commlength; i++) {
				Log.i(TAG, "����:" + commentsArray.getJSONObject(i));
				Comment mComment = new Comment();
				try {
					Gson gson = new Gson();
					mComment = gson.fromJson(commentsArray.getJSONObject(i)
							.toString(), Comment.class);
					mComment.setUserheadimage(HealthHttpClient.IMAGE_URL
							+ commentsArray.getJSONObject(i).getString(
									"headimage"));
					menuComlist.add(mComment);
				} catch (Exception e) {
					// TODO: handle exception
					Log.e(TAG, "��������");
				}

				// mComment.setContent(commentsArray.getJSONObject(i).getString(
				// "content"));
				// mComment.setReasonable(commentsArray.getJSONObject(i)
				// .getString("reasonable"));
				// mComment.setUsername(commentsArray.getJSONObject(i).getString(
				// "username"));
				// mComment.setUserheadimage(HealthHttpClient.IMAGE_URL
				// + commentsArray.getJSONObject(i).getString("headimage"));
				// Log.i("����", "�û���:"+mComment.getUsername());
				// Log.i("����", "ͼƬURL:"+mComment.getUserheadimage());

			}

			info = new PersonalInfo();
			JSONObject obj1 = result.getJSONObject("user");
			info.setHeadimage(obj1.getString("headimage"));
			info.setUsername(obj1.getString("username"));
			info.setBodyindex(obj1.getString("bodyindex"));
			try {
				Gson gson = new Gson();
				info = gson.fromJson(obj1.toString(), PersonalInfo.class);
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				starsindex = Integer.valueOf(result.getInt("collectCount"));
				goodsindex = Integer.valueOf(result.getInt("laudCount"));
			} catch (Exception e) {
				// TODO: handle exception
				Log.e(TAG, "��������");
			}

			setView();

		} catch (JSONException e) {
			e.printStackTrace();
			Log.e(TAG, "��������");
		}

	}

	private void setView() {
		imageLoader.displayImage(
				HealthHttpClient.IMAGE_URL + foods.getFoodpic(), foodpic,
				options);
		foodtag.setText("" + foods.getTags());
		try {
			tasteLevel.setImageResource(ConstantS.LEVEL_POINT[Integer
					.valueOf(foods.getTastelevel())]);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (TextUtils.isEmpty(mydiatance)) {
			distance.setText("" + foods.getDistance() + "km");
		} else {
			distance.setText(mydiatance + "km");
		}

		imageLoader.displayImage(
				HealthHttpClient.IMAGE_URL + info.getHeadimage(), userHead,
				options);
		userName.setText("" + info.getUsername());
		userSummary.setText("" + info.getSummary());
		userTag.setText("" + info.getBodyindex());
		scrollView.setVisibility(View.VISIBLE);

	}

	private void loopCommentInfo() {
		try {
			Intent intent = new Intent();
			intent.setClass(context, FoodCommentInfoActivity.class);
			intent.putExtra("comment", (Serializable) menuComlist);
			startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.e(TAG, "д�����");
		}

	}

	private void loopCommentActivity() {
		try {
			Intent intent = new Intent();
			intent.setClass(context, FoodCommentActivity.class);
			intent.putExtra(ConstantS.ID, foodid);
			startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.e(TAG, "д�����");
		}
	}

	
	private void loopCommercialMap(){
		try {
			
			YaoyingyangFragment.isdingwei = true;
			Intent intent = new Intent();
			intent.setClass(context, CommercialMapActivity.class);
			intent.putExtra("foods", (Serializable) foods);
			startActivity(intent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.e(TAG, "д�����");
		}
	}
	
}
