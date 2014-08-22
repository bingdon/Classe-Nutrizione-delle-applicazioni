package com.wyy.myhealth.contants;

import com.wyy.myhealth.R;

public interface ConstantS {

	/************* ACTION **********/

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

	public static final String PAGE_INDEX_CHANG = "ACTION.WYY.PAGE.CHANGE";

	public static final String ACTION_BASE_INFO_CHANGE = "ACTION.BASEINFO.CHANGE";

	public static final String ACTION_SEARCH_FOOD = "ACTION.WYY.SEARCH.FOOD";

	public static final String ACTION_HIDE_UI_CHANGE = "ACTION.WYY.UI.CHANGE";

	public static final String ACTION_RESH_USER_DATA = "ACTION.WYY.RESH.USER.DATA";

	public static final String ACTION_LOGIN_FINISH = "ACTION.WYY.LOGIN.FINISH";

	public static final String ACTION_MAIN_FINSH = "ACTION.WYY.MAIN.FINSH";

	public static final String ACTION_SEND_DELETE_ICEFOOD = "ACTION.WYY.SEND_DELETE_ICEFOOD";
	
	public static final String ACTION_SEND_SHAI = "ACTION.WYY.SEND.SHAI";
	
	public static final String ACTION_SEND_CANEL_NOTICE = "ACTION.WYY.SEND.CANEL.NOTICE";

	/************** NOTICE_ID *************/
	public static final int NEW_LOGIN_ACTION_ID = 0;

	public static final int NEW_FOOD_COMMENT_ID = 1;

	public static final int PUBLISH_MOOD_ID = 2;

	public static final int PUBLISH_SHAI_ID = 3;

	public static final int PUBLISH_FOOD_ID = 4;

	public static final int PUBLISH_COMMENT = 5;
	/**
	 * 指数图片数组
	 */
	public static final int[] LEVEL_POINT = { R.drawable.star1,
			R.drawable.star1, R.drawable.star2, R.drawable.star3,
			R.drawable.star4, R.drawable.star5 };

	public static final int[] levels = { R.drawable.shai_star0,
			R.drawable.shai_star1, R.drawable.shai_star2,
			R.drawable.shai_star3, R.drawable.shai_star4, R.drawable.shai_star5 };

	/*********** json格式 *********/
	public static final String RESULT = "result";

	public static final String ID = "id";

	public static final String WOMAN = "0";

	public static final String MAN = "1";

	/************** 消息类型 *************/
	public static final int MSG2M = 0;

	public static final int MSG2W = 1;

	/************* Preferences *************/

	public static final String USER_DATA = "Login";

	public static final String USER_PREFERENCES = "Preferences";

	public static final String USER_BACK_UP = "back_up";

	/************ 记录类型 ************/

	public static final int YINSHI = 100;

	public static final int YUNDONG = 101;

	public static final int ZHIFANG = 102;

	public static final int TANGLEI = 103;

	public static final int DANGBAIZHI = 104;

	public static final int WEISHENGSU = 105;

	public static final int KUANGWUZHI = 106;

	public static final String RECEODER = "receoder_type";
	
	/**************TYPE SHAI*****************/
	
	public static final int TYPE_FOOD = 1;

	public static final int TYPE_MOOD = 2;
	
	/****************TIME*******************/
	
	public static final long DELAY_TIME=2;
	
	public static final long PERIOD_TIME=5*60;
	
	/****************RESH*******************/
	
	public static final String FIRST="0";
	
	public static final String LIMIT="1";
	
	

}
