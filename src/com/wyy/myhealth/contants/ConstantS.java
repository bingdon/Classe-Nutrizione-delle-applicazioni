package com.wyy.myhealth.contants;


import com.wyy.myhealth.R;

public interface ConstantS {

	/*************ACTION**********/
	
	public static final String NEW_LOGIN_ACTION = "COM.BING.NEW_LOGIN";

	public static final String NEW_FOOD_COMMENT = "COM.BING.NEW_FOOD_COMMENT";

	public static final String NOTICE_MAP_MSG = "�𾴵��û�����,������Ϊ�������ߣ���ʳ�����������ڿ�������,�뱣�ֶ��"
			+ "ʹ�ú���и��õط������飬������ʳ����Ӵ˿̿�ʼ��";

	public static final String ADD_FOODS_COMMENT = "add_foods_comment";

	public static final String FIRST_LOGIN = "first_login";

	public static final String BAIDU_ONBIND = "COM.BING.BAIDU.ONBIND";
	/**
	 * �ϴ�����
	 */
	public static final String POST_FOOT_ACTION = "COM.BING.POST.FOOTS";
	
	public static final String PAGE_INDEX_CHANG="ACTION.WYY.PAGE.CHANGE";
	
	public static final String ACTION_BASE_INFO_CHANGE="ACTION.BASEINFO.CHANGE";
	
	public static final String ACTION_SEARCH_FOOD="ACTION.WYY.SEARCH.FOOD";
	
	public static final String ACTION_HIDE_UI_CHANGE = "ACTION.WYY.UI.CHANGE";
	
	public static final String ACTION_RESH_USER_DATA = "ACTION.WYY.RESH.USER.DATA";
	
	public static final String 	ACTION_LOGIN_FINISH="ACTION.WYY.LOGIN.FINISH";

	/**************NOTICE_ID*************/
	public static final int NEW_LOGIN_ACTION_ID = 0;

	public static final int NEW_FOOD_COMMENT_ID = 1;

	public static final int PUBLISH_MOOD_ID = 2;

	public static final int PUBLISH_SHAI_ID = 3;

	public static final int PUBLISH_FOOD_ID = 4;
	
	public static final int PUBLISH_COMMENT=5;
	/**
	 * ָ��ͼƬ����
	 */
	public static final int[] LEVEL_POINT = { R.drawable.star1,R.drawable.star1,
			R.drawable.star2, R.drawable.star3, R.drawable.star4,
			R.drawable.star5 };
	/***********json��ʽ*********/
	public static final String RESULT="result";
	
	public static final String ID="id";
	
	public static final String WOMAN="0";
	
	public static final String MAN="1";
	
	/**************��Ϣ����*************/
	public static final int MSG2M=0;
	
	public static final int MSG2W=1;

	/*************Preferences*************/
	
	public static final String USER_DATA="Login";
	
	public static final String USER_PREFERENCES="Preferences";
	
	public static final String USER_BACK_UP="back_up";
	
	/************��¼����************/
	
	public static final int YINSHI=100;

	public static final int YUNDONG=101;
	
	public static final int ZHIFANG=102;
	
	public static final int TANGLEI=103;
	
	public static final int DANGBAIZHI=104;
	
	public static final int WEISHENGSU=105;
	
	public static final int KUANGWUZHI=106;
	
	public static final String RECEODER="receoder_type";
	
}
