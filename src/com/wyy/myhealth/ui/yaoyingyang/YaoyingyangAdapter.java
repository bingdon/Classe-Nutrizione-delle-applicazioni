package com.wyy.myhealth.ui.yaoyingyang;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wyy.myhealth.MainActivity;
import com.wyy.myhealth.R;
import com.wyy.myhealth.bean.NearFoodBean;
import com.wyy.myhealth.contants.ConstantS;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class YaoyingyangAdapter extends BaseAdapter {

	
	private List<NearFoodBean> list=new ArrayList<>();
	
	private LayoutInflater inflater;
	
	private ImageLoader imageLoader=ImageLoader.getInstance();
	
	private DisplayImageOptions options;
	
	private LocationListener listener;
	
	public YaoyingyangAdapter (List<NearFoodBean> list,Context context){
		this.list=list;
		inflater=LayoutInflater.from(context);
		this.options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.pic_loading)
		.showImageForEmptyUri(R.drawable.pic_empty)
		.showImageOnFail(R.drawable.pic_failure).cacheInMemory(true)
		.cacheOnDisc(true).considerExifParams(true)
		.build();
	}
	
	
	public void setClickListener(LocationListener listener){
		this.listener=listener;
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
		
		final int adapterPostion=position;
		
		ViewHolder holder=null;
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.yaoyy_ada, null);
			
			holder.foodimg=(ImageView)convertView.findViewById(R.id.bottom_pic);
			holder.tuijianImageView=(ImageView)convertView.findViewById(R.id.recommend_tag_img);
			
			holder.foodtags=(TextView)convertView.findViewById(R.id.food_tags);
			holder.taste=(ImageView)convertView.findViewById(R.id.yao_taste_level);
			holder.energyimg=(ImageView)convertView.findViewById(R.id.yao_enger_level);
			holder.renqiTextView=(TextView)convertView.findViewById(R.id.renqi_num);
			
			holder.shoucang=(ImageView)convertView.findViewById(R.id.shoucang_img);
			holder.distanceTextView=(TextView)convertView.findViewById(R.id.distance);
			
			convertView.setTag(holder);
			
		} else {
			holder=(ViewHolder)convertView.getTag();
		}
		
		imageLoader.displayImage(list.get(position).getFoodpic(), holder.foodimg, options);
		
		holder.foodtags.setText(list.get(position).getTags());
		
		holder.renqiTextView.setText(""+list.get(position).getVisitcount());
		
		if (MainActivity.Wlatitude==0) {
			
			holder.distanceTextView.setVisibility(View.INVISIBLE);
			
		}else {
			
			String distance=list.get(position).getDistance();
			double d=-1;
			try {
				d=Double.valueOf(distance);
			} catch (Exception e) {
				// TODO: handle exception
			}
			Log.i("����", "����:"+d);
			if (d<0) {
				holder.distanceTextView.setVisibility(View.INVISIBLE);
			}else {
				
				holder.distanceTextView.setText(""
						+ d + "km");
				holder.distanceTextView.setVisibility(View.VISIBLE);
			}
			
			
		}
		
		
		String enegrgystr=list.get(position).getEnergy();
		
		int engere=0;
		try {
			engere=Integer.valueOf(enegrgystr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		holder.energyimg.setImageResource(ConstantS.LEVEL_POINT[engere]);
		
		String tasteStr=list.get(position).getTastelevel();
		int taste=0;
		
		try {
			taste=Integer.valueOf(tasteStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		holder.taste.setImageResource(ConstantS.LEVEL_POINT[taste]);
		
		holder.shoucang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listener!=null) {
					listener.onLocationClick(adapterPostion);
				}
				
			}
		});
		
		
		holder.distanceTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listener!=null) {
					listener.onLocationClick(adapterPostion);
				}
			}
		});
		
		return convertView;
	}

	/* ��ſؼ� */
	public final class ViewHolder {

		public ImageView energyimg;
		public ImageView taste;
		public ImageView foodimg;
		public TextView foodtags;
		public TextView distanceTextView;
		public TextView renqiTextView;
		public ImageView shoucang; 
		public ImageView tuijianImageView;
	}

	public interface LocationListener{
		public void onLocationClick(int postion);
	}
	
}
