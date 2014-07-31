package com.wyy.myhealth.http.utils;

import android.content.Context;
import android.util.Log;

import com.wyy.myhealth.baidu.utlis.Utils;
import com.wyy.myhealth.bean.PersonalInfo;
import com.wyy.myhealth.http.AsyncHttpClient;
import com.wyy.myhealth.http.AsyncHttpResponseHandler;
import com.wyy.myhealth.http.RequestParams;

/**
 
 */
public class HealthHttpClient {

	public static final String URL = "http://115.28.164.99:7001/S_health/";

	/**
	 * 閿熸枻鎷烽敓鐭鎷峰潃
	 */
	public static final String BASE_URL = "http://115.28.164.99:7001/S_health/api/";

	public static final String BASE_URL_NO = "http://115.28.164.99:7001/S_health/api";

	/**
	 * 鍥剧墖鍦板潃
	 */
	public static final String IMAGE_URL = "http://115.28.164.99:7001/S_health/upload/";

	/**
	 * 涓汉涓績
	 */

	public static final String PERSONAL_URL = "http://115.28.164.99:7001/S_health/api/ucenter/";
	/**
	 * 小图片加载
	 */
	public static final String MINIIMAGE = "http://115.28.164.99:7001/S_health/api/miniImage70x70?id=";
	/**
	 * 应用下载地址
	 */
	public static final String APP_URL = "http://115.28.164.99:7001/S_health/version/";

	public static final String ICE_BOX = BASE_URL + "userIcebox";

	private volatile static HealthHttpClient instance = null;

	protected static AsyncHttpClient client = new AsyncHttpClient();

	public HealthHttpClient() {
	}

	public void cancelAll(Context paramContext) {
		if (HealthHttpClient.client != null)
			HealthHttpClient.client.cancelRequests(paramContext, true);
	}

	public static HealthHttpClient getInstance() {
		if (instance == null) {
			synchronized (HealthHttpClient.class) {
				if (instance == null) {
					instance = new HealthHttpClient();
				}
			}

		}
		return instance;
	}

	// 鍒濇鐧诲綍
	public static void doHttpFristLogin(AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();

		params.put("channelid", "" + Utils.channelId);
		params.put("requestid", "" + Utils.requestId);
		params.put("appid", "" + Utils.appid);
		params.put("appuserid", "" + Utils.userid);
		Log.i("channelId", "channelId:" + Utils.channelId);

		client.post(BASE_URL + "firstLogin", params, handler);

	}

	// 鐧诲�?
	public static void doHttpLogin(String idcode,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("idcode", idcode);
		client.post(BASE_URL + "loginByIDentity", params, handler);
	}

	// 鑾峰彇涓汉淇℃�?
	public static void doHttpPersonalSetting(String userId,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("module", "user");
		params.put("action", "userinfo");
		params.put("userid", userId);
		client.post(BASE_URL, params, handler);
	}

	// 淇敼淇℃伅 name birthday gender
	public static void doHttpFinishPersonInfoForName(PersonalInfo info,
			String username, String age, String gender, String headimage,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", info.getId());
		params.put("height", info.getHeight());
		params.put("weight", info.getWeight());
		params.put("job", info.getJob());
		params.put("gender", gender);
		params.put("age", age);
		params.put("bodyindex", info.getBodyindex());
		params.put("username", username);
		params.put("sportindex", info.getSportindex());
		params.put("tags", info.getTags());
		params.put("headimage", headimage);
		client.post(PERSONAL_URL + "fullUserInfo", params, handler);
	}

	// 淇敼淇℃伅 Job
	public static void doHttpFinishPersonInfoForJob(PersonalInfo info,
			String job, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", info.getId());
		params.put("height", info.getHeight());
		params.put("weight", info.getWeight());
		params.put("job", job);
		params.put("gender", info.getGender());
		params.put("birthday", info.getBirthday());
		params.put("name", info.getUsername());
		params.put("sportindex", info.getSportindex());
		params.put("tags", info.getTags());
		params.put("headimg", info.getHeadimage());
		client.post(PERSONAL_URL + "fullUserInfo", params, handler);
	}

	// 淇敼淇℃伅 Bodyindex
	public static void doHttpFinishPersonInfoForTag(PersonalInfo info,
			String bodyindex, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", info.getId());
		params.put("height", info.getHeight());
		params.put("weight", info.getWeight());
		params.put("job", info.getJob());
		params.put("gender", info.getGender());
		params.put("birthday", info.getBirthday());
		params.put("name", info.getUsername());
		params.put("sportindex", info.getSportindex());
		params.put("tags", info.getTags());
		params.put("bodyindex", bodyindex);
		params.put("headimg", info.getHeadimage());
		client.post(PERSONAL_URL + "fullUserInfo", params, handler);
	}

	// 淇敼淇℃伅 height weight
	public static void doHttpFinishPersonInfoForHeight(PersonalInfo info,
			String height, String weight, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", info.getId());
		params.put("height", height);
		params.put("weight", weight);
		params.put("job", info.getJob());
		params.put("gender", info.getGender());
		params.put("birthday", info.getBirthday());
		params.put("name", info.getUsername());
		params.put("sportindex", info.getSportindex());
		params.put("tags", info.getTags());
		params.put("headimg", info.getHeadimage());
		client.post(PERSONAL_URL + "fullUserInfo", params, handler);
	}

	// 淇敼澶村儚
	public static void doHttpFinishPersonInfoForHeadimage(PersonalInfo info,
			String headimage, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", info.getId());
		params.put("height", info.getHeight());
		params.put("weight", info.getWeight());
		params.put("job", info.getJob());
		params.put("gender", info.getGender());
		params.put("birthday", info.getBirthday());
		params.put("name", info.getUsername());
		params.put("sportindex", info.getSportindex());
		params.put("tags", info.getTags());
		params.put("headimage", headimage);
		client.post(PERSONAL_URL + "fullUserInfo", params, handler);
	}

	// 淇敼澶村儚
	public static void doHttpFinishPersonInfoForSummary(PersonalInfo info,
			String summary, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", info.getId());
		params.put("height", info.getHeight());
		params.put("weight", info.getWeight());
		params.put("job", info.getJob());
		params.put("gender", info.getGender());
		params.put("birthday", info.getBirthday());
		params.put("name", info.getUsername());
		params.put("sportindex", info.getSportindex());
		params.put("tags", info.getTags());
		params.put("headimage", info.getHeadimage());
		params.put("summary", summary);
		client.post(PERSONAL_URL + "fullUserInfo", params, handler);
	}

	/**
	 * Share food method 分享食物
	 * 
	 * @param userid
	 *            用户ID
	 * @param foodpicStr
	 *            食物描述
	 * @param tags
	 *            标签
	 * @param summary
	 *            理由
	 * @param tastelevel
	 *            可口度
	 * @param commercialName
	 *            商家名称
	 * @param commercialPhone
	 *            商家电话
	 * @param commercialAddress
	 *            商家地址
	 * @param commercialLat
	 *            地理纬度
	 * @param commercialLon
	 *            地理经度
	 * @param handler
	 *            返回处理
	 */
	public static void doHttpPostFoods(String userid, String foodpicStr,
			String tags, String summary, String tastelevel,
			String commercialName, String commercialPhone,
			String commercialAddress, String commercialLat,
			String commercialLon, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("foodpicStr", foodpicStr);
		params.put("tags", tags);
		params.put("summary", summary);
		params.put("tastelevel", tastelevel);
		params.put("commercialName", commercialName);
		params.put("commercialPhone", commercialPhone);
		params.put("commercialAddress", commercialAddress);
		params.put("commercialLat", commercialLat);
		params.put("commercialLon", commercialLon);
		client.post(BASE_URL + "postFoods", params, handler);
	}

	public static void doHttpUserFoodsAndMoods(String userId,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userId);
		client.post("http://115.28.164.99:7001/S_health/api/userFoodsAndMoods",
				params, handler);
	}

	// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽閿熸枻鎷烽敓鏂ゆ嫹
	public static void doHttpPostComment(String foodid, String content,
			String reasonable, String userid, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("foodid", foodid);
		params.put("content", content);
		params.put("reasonable", reasonable);
		params.put("userid", userid);
		Log.i("PASSURL", "URL:" + BASE_URL + "postFoodComment");
		client.post(BASE_URL + "postFoodComment", params, handler);
	}

	// 閿熸枻鎷烽敓鏂ゆ嫹閿燂拷锟�
	public static void doHttpFeedBack(String userId, String content,
			String contact, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("content", content);
		params.put("contact", contact);
		params.put("userid", userId);
		client.post(BASE_URL + "feedback", params, handler);
	}

	// 涓婁紶蹇冩儏
	public static void doHttpPostMood(String userid, String context,
			String moodindex, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("context", context);
		params.put("moodindex", moodindex);
		client.post(BASE_URL + "postMood", params, handler);
	}

	/**
	 * 曬一曬 接口
	 * 
	 * @param userid
	 *            用戶ID
	 * @param context
	 *            內容
	 * @param handler
	 *            返回處理
	 */
	public static void shaiPostAired(String userid, String context,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("context", context);
		client.post(BASE_URL + "postAired", params, handler);
	}

	/**
	 * 帶有心情指數的 曬一曬
	 * 
	 * @param userid
	 *            用戶ID
	 * @param context
	 *            理由
	 * @param moodindex
	 *            心情指數
	 * @param handler
	 *            返回處理
	 */
	public static void shaiPostAired(String userid, String context,
			String moodindex, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("context", context);
		params.put("moodindex", moodindex);
		client.post(BASE_URL + "postAired", params, handler);
	}

	/**
	 * Get all foods list bing. 獲取所有事物
	 * 
	 * @param txt
	 *            關鍵字
	 * @param lat
	 *            緯度
	 * @param lon
	 *            經度
	 * @param first
	 *            開始位置
	 * @param limit
	 *            加載數量
	 * @param handler
	 *            返回處理
	 */
	public static void doHttpGetFoodsList(String txt, String lat, String lon,
			String first, String limit, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("txt", txt);
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("first", first);
		params.put("limit", limit);
		client.post(BASE_URL + "searchFoods", params, handler);
	}

	/**
	 * Get search for key words; 根據關鍵字搜尋食物
	 * 
	 * @param txt
	 *            關鍵字
	 * @param first
	 *            開始位置
	 * @param limit
	 *            家在數量
	 * @param handler
	 *            返回處理
	 */
	public static void doHttpGetFoodsList(String txt, String first,
			String limit, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("txt", txt);
		params.put("first", first);
		params.put("limit", limit);
		client.post(BASE_URL + "searchFoods", params, handler);
	}

	/**
	 * Get special number list. 獲取特定數量食物
	 * 
	 * @param first
	 *            開始位置
	 * @param limit
	 *            加載數量
	 * @param handler
	 *            消息處理
	 */
	public static void doHttpGetFoodsList(String first, String limit,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("first", first);
		params.put("limit", limit);
		client.post(BASE_URL + "searchFoods", params, handler);
	}

	/**
	 * Get foods list for location. 根據地理位置獲取食物列表
	 * 
	 * @param lat
	 *            緯度
	 * @param lon
	 *            經度
	 * @param first
	 *            開始位置
	 * @param limit
	 *            加載數量
	 * @param handler
	 *            返回處理
	 */
	public static void doHttpGetFoodsList(String lat, String lon, String first,
			String limit, AsyncHttpResponseHandler handler, String userid) {
		RequestParams params = new RequestParams();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("first", first);
		params.put("limit", limit);
		params.put("userid", userid);
		client.post(BASE_URL + "searchFoods", params, handler);
	}

	/**
	 * Collect food post 收藏食物
	 * 
	 * @param userid
	 *            用戶ID
	 * @param foodsid
	 *            食物ID
	 * @param handler
	 *            消息處理
	 */
	public static void doHttpPostCollect(String userid, String foodsid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("foodsid", foodsid);
		client.post(BASE_URL + "postCollect", params, handler);
	}

	/**
	 * delete collect food 刪除收藏食物
	 * 
	 * @param cllect_id
	 *            收藏ID
	 * @param handler
	 *            返回處理
	 */
	public static void doHttpdelCollect(String cllect_id,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("id", cllect_id);
		client.post(BASE_URL + "delCollect", params, handler);
	}

	/**
	 * delete my mood list 刪除心情
	 * 
	 * @param mood_id
	 *            心情ID
	 * @param handler
	 *            異步處理
	 */
	public static void doHttpdelMood(String mood_id,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("id", mood_id);
		client.post(BASE_URL + "delMood", params, handler);
	}

	/**
	 * delete my food list 刪除發佈食物
	 * 
	 * @param food_id
	 *            發佈食物ID
	 * @param handler
	 *            消息處理
	 */
	public static void doHttpdelFoods(String food_id,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("id", food_id);
		client.post(BASE_URL + "delFoods", params, handler);
	}

	/**
	 * Post good comment 为食物点赞
	 * 
	 * @param userid
	 *            用户ID
	 * @param foodsid
	 *            食物ID
	 * @param handler
	 *            返回处理
	 */
	public static void doHttppostFoodsLand(String userid, String foodsid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("foodsid", foodsid);
		client.post(BASE_URL + "postFoodsLand", params, handler);
	}

	/**
	 * post message to server 发送消息到服务器
	 * 
	 * @param userid
	 *            用户ID
	 * @param content
	 *            消息内容
	 * @param handler
	 *            返回处理
	 */
	public static void doHttpPostMessage(String userid, String content,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("content", content);
		client.post(BASE_URL + "postMessages", params, handler);

	}

	/**
	 * 发送步数
	 * 
	 * @param userid
	 *            用户ID
	 * @param number
	 *            行走步数
	 * @param handler
	 *            处理Handler
	 */
	public static void doHttpPostFoot(String userid, String number,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("number", number);
		client.post(BASE_URL + "postFoot", params, handler);
	}

	/**
	 * 获取我的运动步数
	 * 
	 * @param userid
	 *            用户id
	 * @param handler
	 *            返回处理
	 */
	public static void doHttpGetMyFoots(String userid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		client.post(BASE_URL + "userFoots", params, handler);
	}

	/**
	 * 应用更新
	 * 
	 * @param handler
	 */
	public static void doHttpUpdateApp(AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		client.post(BASE_URL + "listVersion", params, handler);
	}

	/**
	 * 获取心情接口
	 * 
	 * @param userid
	 * @param handler
	 */
	public static void dohttpGetMood(String userid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		client.post(BASE_URL + "userMood", params, handler);
	}

	/**
	 * 晒一晒内容
	 * 
	 * @param handler
	 */
	public static void doHttpGetShayiSai(AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		client.post(BASE_URL + "userAiredFoodsAndMoods", params, handler);
	}

	/**
	 * 分页获取晒一晒内容
	 * 
	 * @param handler
	 */
	public static void doHttpGetShayiSai(String first, String limit,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("first", first);
		params.put("limit", limit);
		client.post(BASE_URL + "userAiredFoodsAndMoods", params, handler);
	}

	/**
	 * 上传心情带图片
	 * 
	 * @param moodex
	 *            心情ID
	 * @param moodpicStr
	 *            心情图片
	 * @param handler
	 *            返回处理
	 */
	public static void doHttpPostMoodPic(String moodex, String moodpicStr,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("moodid", moodex);
		params.put("moodpicStr", moodpicStr);
		client.post(BASE_URL + "postMoodImg", params, handler);
	}

	/**
	 * 将心情发送到晒一晒
	 * 
	 * @param moodid
	 *            心情ID
	 * @param handler
	 *            返回处理
	 */
	public static void MoodShaiYIShai(String moodid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("moodid", moodid);
		client.post(BASE_URL + "postMoodAired", params, handler);
	}

	/**
	 * 食物晒一晒
	 * 
	 * @param foodsid
	 *            食物ID
	 * @param handler
	 *            返回处理
	 */
	public static void FoodShaiYiShai(String foodsid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("foodid", foodsid);
		client.post(BASE_URL + "postFoodAired", params, handler);
	}

	/**
	 * 给心情点赞
	 * 
	 * @param userid
	 *            用户ID
	 * @param moodid
	 *            心情ID
	 * @param handler
	 *            返回处理
	 */
	public static void postMoodLand(String userid, String moodid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("moodid", moodid);
		params.put("userid", userid);
		client.post(BASE_URL + "postMoodLand", params, handler);

	}

	/**
	 * 评论心情
	 * 
	 * @param userid
	 *            用户ID
	 * @param moodid
	 *            心情ID
	 * @param content
	 *            评论内容
	 * @param handler
	 *            返回处理
	 */
	public static void postMoodComment(String userid, String moodid,
			String content, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("moodid", moodid);
		params.put("userid", userid);
		params.put("content", content);
		client.post(BASE_URL + "postMoodComment", params, handler);
	}

	/**
	 * 收藏心情
	 * 
	 * @param userid
	 *            用户ID
	 * @param moodid
	 *            心情ID
	 * @param content
	 *            评论内容
	 * @param handler
	 *            返回处理
	 */
	public static void postMoodCollect(String userid, String moodid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("moodid", moodid);
		params.put("userid", userid);
		client.post(BASE_URL + "postMoodCollect", params, handler);
	}

	/**
	 * 新健康周
	 * 
	 * @param userid
	 *            用户ID
	 * @param first
	 *            开始
	 * @param limit
	 *            数量
	 * @param handler
	 *            返回处理
	 */
	public static void userFoodsAndMoods2(String userid, String first,
			String limit, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("first", first);
		params.put("limit", limit);
		client.post(BASE_URL + "userFoodsAndMoods2", params, handler);
	}

	/**
	 * 我的收藏
	 * 
	 * @param userid
	 *            用户ID
	 * @param first
	 *            开始
	 * @param limit
	 *            数量
	 * @param handler
	 *            返回处理
	 */
	public static void userCollects(String userid, String first, String limit,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("first", first);
		params.put("limit", limit);
		client.post(BASE_URL + "userCollects", params, handler);
		// client.post(BASE_URL + "userCollects", handler);
	}

	/**
	 * 文字识别接口
	 * 
	 * @param word
	 *            关键字
	 * @param handler
	 *            返回处理
	 */
	public static void cmpFoodWords(String word,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("word", word);
		client.post(BASE_URL + "cmpFoodWords", params, handler);

	}

	/**
	 * 上传图片对比
	 * 
	 * @param foodpicStr
	 *            图片
	 * @param userid
	 *            用户ID
	 * @param handler
	 *            返回处理
	 */
	public static void cmpFoodPic(String foodpicStr, String userid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("foodpicStr", foodpicStr);
		params.put("userid", userid);
		client.post(BASE_URL + "cmpFoodpic", params, handler);

	}

	/**
	 * 心情详情
	 * 
	 * @param moodid
	 * @param handler
	 */
	public static void getMoodInfo(String moodid,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("moodid", moodid);
		client.post(BASE_URL + "moodInfo", params, handler);
	}

	public static void getHealthRecorder(String userid, String first,
			AsyncHttpResponseHandler handler) {

		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("first", first);
		client.post(BASE_URL + "userNutritions", params, handler);

	}

	/**
	 * 获取冰箱内容
	 * @param userid
	 * @param first
	 * @param limit
	 * @param handler
	 */
	public static void getIceBoxFood(String userid, String first, String limit,
			AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("userid", userid);
		params.put("first", first);
		params.put("limit", limit);
		client.post(ICE_BOX, params, handler);
	}

}