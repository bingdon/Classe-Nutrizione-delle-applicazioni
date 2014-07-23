package com.wyy.myhealth.ui.fooddetails;

import android.os.Bundle;
import android.widget.ImageView;

import com.wyy.myhealth.R;
import com.wyy.myhealth.bean.Comment;
import com.wyy.myhealth.bean.HealthRecoderBean;
import com.wyy.myhealth.imag.utils.LoadImageUtils;
import com.wyy.myhealth.service.MainService;
import com.wyy.myhealth.ui.baseactivity.BaseNutritionActivity;
import com.wyy.myhealth.ui.baseactivity.interfacs.ActivityInterface;
import com.wyy.myhealth.utils.BingLog;

public class FoodNutritionActivity extends BaseNutritionActivity implements
		ActivityInterface {

	private static final String TAG = FoodNutritionActivity.class
			.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_nutri_info);
		initView();
	}

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		getSupportActionBar().setTitle(R.string.nutritionnfo);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		LoadImageUtils.loadImage4ImageV(
				(ImageView) findViewById(R.id.food_pic),
				FoodDetailsActivity.imgurl);
		initScoreV();
		initData();

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

		Comment comment = (Comment) getIntent().getSerializableExtra("comment");
		BingLog.i(TAG, "½á¹û:" + comment);
		if (null != comment && MainService.getNextHealthRecoderBeans() != null) {

			try {
				vitasocre = Integer.valueOf(comment.getVitamin());
				getvitaminsimgs(vitasocre);
				proteinsocre = Integer.valueOf(comment.getProtein());
				getproteinsimgs(proteinsocre);
				minerasocre = Integer.valueOf(comment.getMineral());
				getmineralsimgs(minerasocre);
				fatsocre = Integer.valueOf(comment.getFat());
				getfatimgs(fatsocre);
				sugarsocre = Integer.valueOf(comment.getSugar());
				getsugarimgs(sugarsocre);
				energysocre = Integer.valueOf(comment.getEnergy());
				getenergysimgs(energysocre);
			} catch (Exception e) {
				// TODO: handle exception

			}

			HealthRecoderBean healthRecoderBean = MainService
					.getNextHealthRecoderBeans().get(
							MainService.getNextHealthRecoderBeans().size() - 1);

			try {
				getnextvitaminsimgs(Integer.valueOf(healthRecoderBean
						.getVitamin()));
				getnextproteinsimgs(Integer.valueOf(healthRecoderBean
						.getProtein()));
				getnextmineralsimgs(Integer.valueOf(healthRecoderBean
						.getMineral()));
				getnextfatimgs(Integer.valueOf(healthRecoderBean.getFat()));
				getnextsugarimgs(Integer.valueOf(healthRecoderBean.getSugar()));
				getnextenergysimgs(Integer.valueOf(healthRecoderBean
						.getEnergy()));
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

}
