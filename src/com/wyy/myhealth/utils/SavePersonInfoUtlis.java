package com.wyy.myhealth.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import com.wyy.myhealth.bean.PersonalInfo;

/**
 * ���������Ϣ
 * 
 * @author lyl
 * 
 */
public class SavePersonInfoUtlis {
	/**
	 * ������Ϣ
	 * 
	 * @param info
	 * @param context
	 */
	public static void setPersonInfo(PersonalInfo info, Activity context) {
		SharedPreferences preferences = context.getSharedPreferences("Login",
				Context.MODE_PRIVATE);

		Editor editor = preferences.edit();
		// ��������
		editor.putBoolean("isLogin", true);
		editor.putString("id", info.getId());
		editor.putString("idcode", info.getIdcode());
		editor.putString("foodpic", info.getHeadimage());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream infoStream = new ObjectOutputStream(baos);
			infoStream.writeObject(info);
			String infobase64 = Base64.encodeToString(baos.toByteArray(),
					Base64.DEFAULT);
			editor.putString("personinfo", infobase64);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �ύ�޸�
		editor.commit();
	}

}
