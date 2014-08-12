package com.wyy.myhealth.ui.yaoyingyang;

import java.util.ArrayList;
import java.util.List;

import com.wyy.myhealth.bean.NearFoodBean;
import com.wyy.myhealth.ui.absfragment.ListBaseFragYP;

public class TodayFoodRecFragment extends ListBaseFragYP {

	private static final String TAG = TodayFoodRecFragment.class.getSimpleName();

	private List<NearFoodBean> list = new ArrayList<>();

	private List<NearFoodBean> list2 = new ArrayList<>();

	private static String postiontag = "postion";

	private YaoyingyangAdapter yaoyingyangAdapter;

	@Override
	protected void onInitAdapter() {
		// TODO Auto-generated method stub
		super.onInitAdapter();
		yaoyingyangAdapter = new YaoyingyangAdapter(list, getActivity());
		bingListView.setAdapter(yaoyingyangAdapter);
//		mRefreshLayout.setOnRefreshListener(this);
//		bingListView.setXListViewListener(this);
//		yaoyingyangAdapter.setClickListener(this);
//		getNerabyFoods();
//		bingListView.setOnItemClickListener(this);
	}
	
}
