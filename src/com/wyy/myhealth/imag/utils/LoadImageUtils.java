package com.wyy.myhealth.imag.utils;

import com.wyy.myhealth.app.WyyApplication;

import android.widget.ImageView;

public class LoadImageUtils {

	public static void loadImage4ImageV(ImageView imageView,String url) {
		WyyApplication.imageLoader.displayImage(url, imageView, WyyApplication.options);
	}
	
}
