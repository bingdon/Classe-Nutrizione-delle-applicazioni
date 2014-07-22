package com.wyy.myhealth.ui.absfragment.utils;

import java.util.List;
import java.util.Map;

import com.wyy.myhealth.bean.NearFoodBean;

import android.util.Log;


public class ListAddUtils {

	@SuppressWarnings("unused")
	private static final String TAG="ListAddUtils";
	/**
	 * �ϲ�list
	 * 
	 * @param tempshaiList
	 * @param shaiList
	 */
	public static void compleAMearge(List<Map<String, Object>> tempshaiList, List<Map<String, Object>> shaiList) {
		
		
		
		int maxindex = 0;
		int locationdex=0;
		for (int i = 0; i < tempshaiList.size(); i++) {
			for (int j = 0; j < shaiList.size(); j++) {
				if (maxindex==0) {
					Log.i(TAG, "��ͬ"+"��ʱ��:"+tempshaiList.get(i).get("time")+":��ʱ��:"+shaiList.get(j).get("time"));
				}
				
				if (tempshaiList.get(i).get("time").equals(shaiList.get(j).get("time"))) {
					maxindex = i;
					locationdex=j;
//					break;
					i=tempshaiList.size();
					j=shaiList.size();
					Log.i(TAG, "��ͬ��:"+maxindex);
				}
				
				
			}
		}

//		Log.i(TAG, "��ͬ��:"+maxindex);
		
		if (maxindex>0) {
			for (int i = maxindex; i < tempshaiList.size()&&locationdex<shaiList.size(); i++) {
				shaiList.remove(locationdex);
				shaiList.add(locationdex, tempshaiList.get(i));
				locationdex++;
			}
		}
		
		
		
		
		if (maxindex > 0) {
			for (int i = 0; i < maxindex; i++) {
				shaiList.add(tempshaiList.get(i));
			}
		}else {
			for (int i = maxindex; i < tempshaiList.size()&&locationdex<shaiList.size(); i++) {
				shaiList.remove(locationdex);
				shaiList.add(locationdex, tempshaiList.get(i));
				locationdex++;
			}
		}

	}
	
	
	
	/**
	 * �ϲ�list
	 * 
	 * @param tempshaiList
	 * @param thList
	 */
	public static void compleANearMearge(List<NearFoodBean> tempshaiList, List<NearFoodBean> shaiList) {
		
		
		
		int maxindex = 0;
		int locationdex=0;
		for (int i = 0; i < tempshaiList.size(); i++) {
			for (int j = 0; j < shaiList.size(); j++) {
				if (maxindex==0) {
					Log.i(TAG, "��ͬ"+"��ʱ��:"+tempshaiList.get(i).getId()+":��ʱ��:"+shaiList.get(j).getId());
				}
				
				if (tempshaiList.get(i).getId().equals(shaiList.get(j).getId())) {
					maxindex = i;
					locationdex=j;
//					break;
					i=tempshaiList.size();
					j=shaiList.size();
					Log.i(TAG, "��ͬ��:"+maxindex);
				}
				
				
			}
		}

//		Log.i(TAG, "��ͬ��:"+maxindex);
		
		if (maxindex>0) {
			for (int i = maxindex; i < tempshaiList.size()&&locationdex<shaiList.size(); i++) {
				shaiList.remove(locationdex);
				shaiList.add(locationdex, tempshaiList.get(i));
				locationdex++;
			}
		}
		
		
		
		
		if (maxindex > 0) {
			for (int i = 0; i < maxindex; i++) {
				shaiList.add(tempshaiList.get(i));
			}
		}else {
			for (int i = maxindex; i < tempshaiList.size()&&locationdex<shaiList.size(); i++) {
				shaiList.remove(locationdex);
				shaiList.add(locationdex, tempshaiList.get(i));
				locationdex++;
			}
		}

	}
	

}
