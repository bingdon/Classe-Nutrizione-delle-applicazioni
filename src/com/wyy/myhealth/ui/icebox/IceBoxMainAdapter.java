package com.wyy.myhealth.ui.icebox;

import java.util.List;

import com.wyy.myhealth.R;
import com.wyy.myhealth.bean.IceBoxFoodBean;
import com.wyy.myhealth.ui.customview.BingGridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class IceBoxMainAdapter extends BaseAdapter {

	private List<List<IceBoxFoodBean>> list;

	private LayoutInflater inflater;

	private Context context;

	public IceBoxMainAdapter(List<List<IceBoxFoodBean>> iceBoxFoodBeansList,
			Context context) {
		this.list = iceBoxFoodBeansList;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.ice_box_fruit_platfrom,
					parent, false);
			holder.bingGridView = (BingGridView) convertView
					.findViewById(R.id.ice_box_grid_v);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.iceBoxChildAdapter = new IceBoxChildAdapter(list.get(position),
				context);

		holder.bingGridView.setAdapter(holder.iceBoxChildAdapter);

		return convertView;
	}

	public class ViewHolder {
		public BingGridView bingGridView;
		public IceBoxChildAdapter iceBoxChildAdapter;
	}

}
