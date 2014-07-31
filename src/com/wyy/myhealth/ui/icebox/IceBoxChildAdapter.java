package com.wyy.myhealth.ui.icebox;

import java.util.List;

import com.wyy.myhealth.R;
import com.wyy.myhealth.bean.IceBoxFoodBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IceBoxChildAdapter extends BaseAdapter {

	private List<IceBoxFoodBean> list;
	
	private LayoutInflater inflater;
	
	public IceBoxChildAdapter(List<IceBoxFoodBean> list,Context context) {
		// TODO Auto-generated constructor stub
		this.list=list;
		this.inflater=LayoutInflater.from(context);
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
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.ice_box_food, parent, false);
			holder.foodpic=(ImageView)convertView.findViewById(R.id.food_case);
			holder.foodname=(TextView)convertView.findViewById(R.id.foodname);
			holder.fooddate=(TextView)convertView.findViewById(R.id.ice_food_date);
			holder.datenotice=(ImageView)convertView.findViewById(R.id.ice_food_date_img);
			holder.energy=(ImageView)convertView.findViewById(R.id.ice_box_energy);
			holder.foodlable=(ImageView)convertView.findViewById(R.id.ice_box_food_lable);
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder)convertView.getTag();
		}
		
		holder.foodname.setText(""+list.get(position).getName());
		holder.fooddate.setText(""+list.get(position).getNumday());
		
		return convertView;
	}

	public class ViewHolder{
		public ImageView foodpic;
		
		public TextView foodname;
		
		public TextView fooddate;
		
		public ImageView datenotice;
		
		public ImageView energy;
		
		public ImageView foodlable;
	}
	
}
