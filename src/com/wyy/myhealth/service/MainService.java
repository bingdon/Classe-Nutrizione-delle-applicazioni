package com.wyy.myhealth.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.wyy.myhealth.app.WyyApplication;
import com.wyy.myhealth.bean.HealthRecoderBean;
import com.wyy.myhealth.config.Config;
import com.wyy.myhealth.http.AsyncHttpResponseHandler;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.http.utils.JsonUtils;
import com.wyy.myhealth.utils.BingLog;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MainService extends Service {

	private static final String TAG=MainService.class.getSimpleName();
	
	private static List<HealthRecoderBean> thHealthRecoderBeans = new ArrayList<>();

	private static List<HealthRecoderBean> nextHealthRecoderBeans = new ArrayList<>();
	
	private static List<String> sports=new ArrayList<>();

	public static List<HealthRecoderBean> getNextHealthRecoderBeans() {
		return nextHealthRecoderBeans;
	}

	
	public static List<HealthRecoderBean> getThHealthRecoderBeans() {
		return thHealthRecoderBeans;
	}

	public static List<String> getSports() {
		return sports;
	}
	

	private final Binder mBinder = new Wibingder();

	public class Wibingder extends Binder {

		public MainService getBingder() {
			return MainService.this;
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		getHealthRecored();
		getFoots();
	}

	private void getHealthRecored() {
		HealthHttpClient.getHealthRecorder(WyyApplication.getInfo().getId(),
				"0", handler);
	}

	private AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			super.onFinish();
		}

		@Override
		public void onSuccess(String content) {
			// TODO Auto-generated method stub
			super.onSuccess(content);
			BingLog.i(TAG, "·µ»Ø:"+content);
			parseJson(content);
		}

		@Override
		public void onFailure(Throwable error, String content) {
			// TODO Auto-generated method stub
			super.onFailure(error, content);
		}

	};

	private void parseJson(String content) {
		try {
			JSONObject jsonObject = new JSONObject(content);
			int length0 = jsonObject.getJSONArray("nutritions").length();

			for (int i = 0; i < length0; i++) {
				HealthRecoderBean healthRecoderBean = JsonUtils
						.getHealthRecoder(jsonObject.getJSONArray("nutritions")
								.getJSONObject(i));
				
				if (null!=healthRecoderBean) {
					thHealthRecoderBeans.add(healthRecoderBean);
				}
				
			}

			
			
			int length1=jsonObject.getJSONArray("nutritionsNext").length();
			
			for (int i = 0; i < length1; i++) {
				
				HealthRecoderBean healthRecoderBean = JsonUtils
						.getHealthRecoder(jsonObject.getJSONArray("nutritionsNext")
								.getJSONObject(i));
				
				if (null!=healthRecoderBean) {
					nextHealthRecoderBeans.add(healthRecoderBean);
				}
				
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			if (Config.DEVELOPER_MODE) {
				e.printStackTrace();
			}
			
		}
	}

	
	private static void getFoots() {
		HealthHttpClient.doHttpGetMyFoots(WyyApplication.getInfo().getId(),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(String content) {
						// TODO Auto-generated method stub
						super.onSuccess(content);
						parseFoots(content);

					}

				});
	}
	
	
	private static void parseFoots(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			int length = jsonObject.getJSONArray("foots").length();
			for (int i = 0; i < length; i++) {
				String level = jsonObject.getJSONArray("foots")
						.getJSONObject(i).getString("level");
				sports.add(level);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
