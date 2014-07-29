package com.wyy.myhealth.utils;

import com.wyy.myhealth.R;

import android.content.Context;
import android.content.Intent;

public class ShareUtils {

	public static void share2Fre(Context context) {
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_content));
		shareIntent.setType("text/plain");
		context.startActivity(Intent.createChooser(shareIntent, context
				.getString(R.string.tell_other)));
	}

	
}
