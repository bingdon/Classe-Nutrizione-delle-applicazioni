package com.wyy.myhealth.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class PhoneInfoUtils {

	public static String getPhoneNum(Context context) {

		String telnum = "";
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		telnum = telephonyManager.getLine1Number();
		return telnum;

	}

	public static String getPhoneIme(Context context) {

		String ime = "";
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		ime = telephonyManager.getDeviceId();
		return ime;

	}

}
