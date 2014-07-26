package com.wyy.myhealth.ui.scan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wyy.myhealth.MainActivity;
import com.wyy.myhealth.R;
import com.wyy.myhealth.file.utils.FileUtils;
import com.wyy.myhealth.file.utils.SdUtils;
import com.wyy.myhealth.imag.utils.PhoneUtlis;
import com.wyy.myhealth.ui.baseactivity.SubmitActivity;
import com.wyy.myhealth.ui.baseactivity.interfacs.ActivityInterface;

public class ShareFoodActivity extends SubmitActivity implements
		ActivityInterface {

	private RatingBar tastRating;

	private TextView tags;
	
	private String tagString;

	private TextView commercai;

	private TextView place;
	
	private String placeString;

	private ImageView foodpic;

	private EditText content;

	private Bitmap foodimg;

	private CheckBox shaiCheck;

	private boolean ishai = true;

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		getSupportActionBar().setTitle(R.string.share);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_food);
		initView();
	}

	@Override
	protected void submitMsg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		context = this;
		tags = (TextView) findViewById(R.id.food_tag);
		content = (EditText) findViewById(R.id.share_content);
		foodpic = (ImageView) findViewById(R.id.food_pic);
		tastRating = (RatingBar) findViewById(R.id.taste_index);
		commercai = (TextView) findViewById(R.id.food_commercial);
		place = (TextView) findViewById(R.id.food_place);
		shaiCheck = (CheckBox) findViewById(R.id.isshaiyishai);
		shaiCheck.setOnCheckedChangeListener(checkedChangeListener);
		
		findViewById(R.id.food_commercial_fra).setOnClickListener(listener);
		findViewById(R.id.food_place_fra).setOnClickListener(listener);
		findViewById(R.id.food_tag_fra).setOnClickListener(listener);
		
		initData();
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

		Bitmap mBitmap = null;
		if (SdUtils.ExistSDCard()) {
			mBitmap = PhoneUtlis.getSmall2ZoomBitmap(FileUtils.PIC_PATH);
		} else {
			mBitmap = PhoneUtlis.getBitmap(context);
		}
		if (mBitmap != null) {
			foodpic.setImageBitmap(mBitmap);
		}
		
		
		place.setText(""+MainActivity.address);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.food_tag_fra:
				showinputTag();
				break;

			case R.id.food_commercial_fra:
				showinputCommerical();
				break;

			case R.id.food_place_fra:
				
				showinputAddress();
				break;

			default:
				break;
			}
		}
	};

	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			ishai = isChecked;
		}
	};
	
	
	private void showinputTag(){
		Intent intent =new Intent();
		intent.setClass(context, GetfoodTagActivity.class);
		startActivityForResult(intent, 0);

	}
	
	
	private void showinputAddress(){
		Intent intent =new Intent();
		intent.setClass(context, GetFoodAddress.class);
		startActivityForResult(intent, 1);

	}
	
	
	private void showinputCommerical(){
		Intent intent =new Intent();
		intent.setClass(context, GetCommercial.class);
		startActivityForResult(intent, 2);

	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		
		if (arg1!=RESULT_OK) {
			return;
		}
		
		
		if (arg0==0) {
			Bundle extras = arg2.getExtras();
			String tag=extras.getString("tag");
			tagString=tag;
			tags.setText(""+tagString);

		}else if (arg0==1) {
			
			Bundle extras = arg2.getExtras();
			String address=extras.getString("address");
			placeString=address;
			place.setText(""+placeString);
			
			
		}else if (arg0==2) {
			
		}
		
	}
	

}
