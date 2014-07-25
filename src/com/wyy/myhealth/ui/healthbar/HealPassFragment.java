package com.wyy.myhealth.ui.healthbar;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
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
import com.wyy.myhealth.file.utils.FileUtils;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.imag.utils.PhotoUtils;
import com.wyy.myhealth.ui.absfragment.HealthPassBase;
import com.wyy.myhealth.ui.customview.BingListView.IXListViewListener;
import com.wyy.myhealth.utils.BingLog;

public class HealPassFragment extends HealthPassBase implements
		OnRefreshListener, IXListViewListener {

	private ImageLoader imageLoader = ImageLoader.getInstance();

	private DisplayImageOptions options;

	private Bitmap headbg;

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

		username.setText("" + WyyApplication.getInfo().getUsername());

		headbg = PhotoUtils.getListHeadBg();
		if (headbg != null) {
			bgImageView.setImageBitmap(headbg);
		}

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
				startActivity(new Intent(getActivity(),
						PublishMoodActivity.class));
				break;

			case R.id.user_bg:
				changeHeadBg();
				break;

			default:
				break;
			}
		}
	};

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		if (requestCode == 0) {
			try {

				final String str;
				Uri localUri = data.getData();
				String[] arrayOfString = new String[1];
				arrayOfString[0] = "_data";
				Cursor localCursor = getActivity().getContentResolver().query(
						localUri, arrayOfString, null, null, null);
				if (localCursor == null)
					return;
				localCursor.moveToFirst();
				str = localCursor.getString(localCursor
						.getColumnIndex(arrayOfString[0]));
				localCursor.close();
				headbg = PhotoUtils.getScaledBitmap(str, 600);

				// //把得到的图片绑定在控件上显示
				bgImageView.setImageBitmap(headbg);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (requestCode == 1) {
			try {

				Bundle extras = data.getExtras();
				headbg = (Bitmap) extras.get("data");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				headbg.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				bgImageView.setImageBitmap(headbg);// 把拍摄的照片转成圆角显示在预览控件上
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 把得到的图片绑定在控件上显示

		}
		
		saveCurrent_ResultBitmap(headbg);
		
		
	};

	// 图片上传选择途径
	private void changeHeadBg() {
		final CharSequence[] items = { getString(R.string.photo),
				getString(R.string.takepic) };
		AlertDialog dlg = new AlertDialog.Builder(getActivity())
				.setTitle(getString(R.string.changebg))
				.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						// 这里item是根据选择的方式，
						// 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
						if (item == 1) {
							Intent getImageByCamera = new Intent(
									"android.media.action.IMAGE_CAPTURE");
							startActivityForResult(getImageByCamera, 1);
						} else {
							Intent getImage = new Intent(
									Intent.ACTION_GET_CONTENT);
							getImage.addCategory(Intent.CATEGORY_OPENABLE);
							getImage.setType("image/jpeg");
							startActivityForResult(getImage, 0);
						}
					}
				}).create();
		dlg.show();
	}

	/**
	 * 保存此次背景
	 * 
	 * @param result
	 */
	private void saveCurrent_ResultBitmap(Bitmap bitmap) {
		BingLog.i(TAG, "开始保存");
		File file = new File(FileUtils.HEALTH_IMAG, WyyApplication.getInfo()
				.getUsername() + "hps" + ".jpg");
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			bitmap.compress(CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BingLog.i(TAG, "保存成功");
	}
}
