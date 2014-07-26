package com.wyy.myhealth.ui.setting;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.Toast;

import com.wyy.myhealth.R;
import com.wyy.myhealth.http.AsyncHttpResponseHandler;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.ui.baseactivity.BaseActivity;
import com.wyy.myhealth.ui.baseactivity.interfacs.ActivityInterface;

public class AboutVersion extends BaseActivity implements ActivityInterface{

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		getSupportActionBar().setTitle(R.string.versioninfo);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_version);
	}

	
	private String getLoVersion() {

		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			String version = packageInfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		HealthHttpClient.doHttpUpdateApp(handler);
	}
	
	
	private AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {

		@Override
		public void onSuccess(String content) {
			// TODO Auto-generated method stub
			super.onSuccess(content);
			parseUrl(content);
		}

		@Override
		public void onFailure(Throwable error, String content) {
			// TODO Auto-generated method stub
			super.onFailure(error, content);
			Toast.makeText(context, R.string.net_erro, Toast.LENGTH_LONG).show();
		}

	};
	
	
	private void parseUrl(String content) {

		try {
			JSONObject jsonObject = new JSONObject(content);
			String versionNum = jsonObject.getJSONArray("versions")
					.getJSONObject(0).getString("versionNum");

			if (!versionNum.equals(getLoVersion())) {
				Toast.makeText(context, R.string.hasnewversion, Toast.LENGTH_LONG).show();
//				newVersion.setText("×îÐÂ°æ±¾V" + versionNum);
			}else {
				Toast.makeText(context, R.string.is_alreadly_version, Toast.LENGTH_LONG).show();
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
