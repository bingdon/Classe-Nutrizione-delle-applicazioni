package com.wyy.myhealth.ui.message;

import com.wyy.myhealth.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PublicMsgAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		
		if (convertView==null) {
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.msg_public_ada_, parent, false);
			holder.time=(TextView)convertView.findViewById(R.id.time);
			holder.content=(TextView)convertView.findViewById(R.id.msg_content);
			convertView.setTag(holder);
			
		} else {
			holder=(ViewHolder)convertView.getTag();
		}
		
		
		
		return convertView;
	}

	
	public class ViewHolder{
		public TextView time;
		
		public TextView content;
	}
	
}
