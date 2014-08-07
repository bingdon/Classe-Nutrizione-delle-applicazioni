package com.wyy.myhealth.ui.scan.utils;

import com.wyy.myhealth.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogShowFeture {

	public static void showFetureDialog(Context context, String message) {

		new AlertDialog.Builder(context).setTitle(android.R.id.summary)
				.setMessage(message)
				.setPositiveButton(R.string.ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				}).show();

	}
}
