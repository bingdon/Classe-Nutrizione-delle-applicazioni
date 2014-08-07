package com.wyy.myhealth.ui.icebox;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.wyy.myhealth.R;
import com.wyy.myhealth.bean.IceBoxFoodBean;
import com.wyy.myhealth.db.utils.IceDadabaseUtils;
import com.wyy.myhealth.ui.baseactivity.BaseActivity;
import com.wyy.myhealth.ui.baseactivity.interfacs.ActivityInterface;

public class IceBoxHistory extends BaseActivity implements ActivityInterface {

	private ListView listView;
	private HistoryAdapter historyAdapter;
	private List<IceBoxFoodBean> list=new ArrayList<>();
	private IceDadabaseUtils iceDadabaseUtils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ice_box_history);
		initView();
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		iceDadabaseUtils.close();
	}

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		getSupportActionBar().setTitle(R.string.ice_box_history);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		listView=(ListView)findViewById(R.id.ice_box_history);
		initData();
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		getHistory();
	}

	
	@SuppressWarnings("unchecked")
	private void getHistory(){
		iceDadabaseUtils=new IceDadabaseUtils(context);
		list=(List<IceBoxFoodBean>) iceDadabaseUtils.queryData();
		if (list==null) {
			list=new ArrayList<>();
		}
		
		historyAdapter=new HistoryAdapter(list, context);
		listView.setAdapter(historyAdapter);
		
	}
	
	
	
	
	
}
