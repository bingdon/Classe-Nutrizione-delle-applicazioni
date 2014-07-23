package com.wyy.myhealth.http.utils;

import org.json.JSONException;
import org.json.JSONObject;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wyy.myhealth.bean.Foods;
import com.wyy.myhealth.bean.HealthRecoderBean;
import com.wyy.myhealth.bean.PersonalInfo;
import com.wyy.myhealth.config.Config;
import com.wyy.myhealth.utils.BingLog;

public class JsonUtils {

	private static final String TAG=JsonUtils.class.getSimpleName();
	/**
	 * ���ؽ��
	 * 
	 * @param jsonObject
	 *            json����
	 * @param key
	 *            ��ֵ
	 * @return
	 */
	public static String getKey(JSONObject jsonObject, String key) {
		if (jsonObject.has(key)) {
			try {
				return jsonObject.getString(key);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}

		return "";

	}
	
	
	/**
	 * ��������
	 * 
	 * @param object
	 * @return
	 */
	public static PersonalInfo getInfo(JSONObject object) {
		PersonalInfo info = null;
		try {
			Gson gson = new Gson();
			info = gson.fromJson(object.toString(), PersonalInfo.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * ��������
	 * 
	 * @param object
	 * @return
	 */
	public static Foods getfoods(JSONObject object) {
		Foods foods = null;
		try {
			Gson gson = new Gson();
			foods = gson.fromJson(object.toString(), Foods.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foods;
	}

	/**
	 * �ж������Ƿ���ȷ
	 * 
	 * @param object
	 * @return
	 */
	public static Boolean isSuccess(JSONObject object) {
		try {
			if (object.has("result")) {
				int result = object.getInt("result");
				if (result == 1) {
					return true;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	
	public static HealthRecoderBean getHealthRecoder(JSONObject object){
		HealthRecoderBean healthRecoderBean=new HealthRecoderBean();
		try {
			Gson gson = new Gson();
			healthRecoderBean=gson.fromJson(object.toString(), HealthRecoderBean.class);
		} catch (Exception e) {
			// TODO: handle exception
			if (Config.DEVELOPER_MODE) {
				e.printStackTrace();
				BingLog.e(TAG, "��������");
			}
		}
		
		return healthRecoderBean;
	}
	
}