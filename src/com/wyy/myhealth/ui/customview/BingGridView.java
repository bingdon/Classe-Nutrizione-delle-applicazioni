package com.wyy.myhealth.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class BingGridView extends GridView {
	
	
	public BingGridView(Context context){
		super(context);
	}

	public BingGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
