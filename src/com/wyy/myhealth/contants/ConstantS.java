package com.wyy.myhealth.contants;

import com.wyy.myhealth.R;

public interface ConstantS {

	/*************ACTION**********/
	
	public static final String NEW_LOGIN_ACTION = "COM.BING.NEW_LOGIN";

	public static final String NEW_FOOD_COMMENT = "COM.BING.NEW_FOOD_COMMENT";

	public static final String NOTICE_MAP_MSG = "尊敬的用户您好,本地区为初次上线，美食服务数据正在快速增加,请保持多次"
			+ "使用后会有更好地服务体验，健康饮食生活从此刻开始！";

	public static final String ADD_FOODS_COMMENT = "add_foods_comment";

	public static final String FIRST_LOGIN = "first_login";

	public static final String BAIDU_ONBIND = "COM.BING.BAIDU.ONBIND";
	/**
	 * 上传步伐
	 */
	public static final String POST_FOOT_ACTION = "COM.BING.POST.FOOTS";
	
	public static final String PAGE_INDEX_CHANG="ACTION.WYY.PAGE.CHANGE";
	
	public static final String ACTION_BASE_INFO_CHANGE="ACTION.BASEINFO.CHANGE";
	
	public static final String ACTION_SEARCH_FOOD="ACTION.WYY.SEARCH.FOOD";

	/**************NOTICE_ID*************/
	public static final int NEW_LOGIN_ACTION_ID = 0;

	public static final int NEW_FOOD_COMMENT_ID = 1;

	public static final int PUBLISH_MOOD_ID = 2;

	public static final int PUBLISH_SHAI_ID = 3;

	public static final int PUBLISH_FOOD_ID = 4;
	
	public static final int PUBLISH_COMMENT=5;
	/**
	 * 指数图片数组
	 */
	public static final int[] LEVEL_POINT = { R.drawable.star1,R.drawable.star1,
			R.drawable.star2, R.drawable.star3, R.drawable.star4,
			R.drawable.star5 };
	/***********json格式*********/
	public static final String RESULT="result";
	
	public static final String ID="id";
	
	public static final String WOMAN="0";
	
	public static final String MAN="1";
	
}
