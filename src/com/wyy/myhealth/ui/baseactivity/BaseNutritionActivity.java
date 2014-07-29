package com.wyy.myhealth.ui.baseactivity;

import com.wyy.myhealth.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseNutritionActivity extends BaseActivity {

	protected ImageView vitaminsimgs;
	protected ImageView proteinsimgs;
	protected ImageView mineralsimgs;
	protected ImageView fatimgs;
	protected ImageView sugarimgs;
	protected ImageView energyimgs;

	protected ImageView nextvitaminsimgs;
	protected ImageView nextproteinsimgs;
	protected ImageView nextmineralsimgs;
	protected ImageView nextfatimgs;
	protected ImageView nextsugarimgs;
	protected ImageView nextenergyimgs;

	protected TextView cproteinTextView;
	protected TextView cfatTextView;
	protected TextView csurgarTextView;
	protected TextView cengerTextView;
	

	protected int vitasocre = 0, proteinsocre = 0, minerasocre = 0,
			fatsocre = 0, sugarsocre = 0, energysocre = 0;

	private static final int[] images = { R.drawable.point0, R.drawable.point1,
			R.drawable.point2, R.drawable.point3, R.drawable.point4,
			R.drawable.point5 };
	private static final int[] energyimg = { R.drawable.point0,
			R.drawable.engergy_point1, R.drawable.engergy_point2,
			R.drawable.engergy_point3, R.drawable.engergy_point4,
			R.drawable.engergy_point5 };
	private static final int[] jianyiimages = { R.drawable.point0,
			R.drawable.jianyi1, R.drawable.jianyi2, R.drawable.jianyi3,
			R.drawable.jianyi4, R.drawable.jianyi5 };

	protected void initScoreV() {
		vitaminsimgs = (ImageView) findViewById(R.id.vitaminsimg);
		proteinsimgs = (ImageView) findViewById(R.id.proteinsimg);
		mineralsimgs = (ImageView) findViewById(R.id.mineralsimg);
		fatimgs = (ImageView) findViewById(R.id.fatimg);
		sugarimgs = (ImageView) findViewById(R.id.sugarimg);
		energyimgs = (ImageView) findViewById(R.id.sao_energyimg);

		nextvitaminsimgs = (ImageView) findViewById(R.id.jianyi_vitaminsimg);
		nextproteinsimgs = (ImageView) findViewById(R.id.jianyi_proteinsimg);
		nextmineralsimgs = (ImageView) findViewById(R.id.jianyi_mineralsimg);
		nextfatimgs = (ImageView) findViewById(R.id.jianyi_fatimg);
		nextsugarimgs = (ImageView) findViewById(R.id.jianyi_sugarimg);
		nextenergyimgs = (ImageView) findViewById(R.id.jianyi_sao_energyimg);

		cproteinTextView = (TextView) findViewById(R.id.chaobiao_proteins_text);
		cfatTextView = (TextView) findViewById(R.id.chaobiao_fat_text);
		csurgarTextView = (TextView) findViewById(R.id.chaobiao_sugar_text);
		cengerTextView = (TextView) findViewById(R.id.chaobiao_energy_text);

	}

	
	protected void getvitaminsimgs(int score) {

		vitaminsimgs.setBackgroundResource(images[score]);

	}

	protected void getproteinsimgs(int score) {

		proteinsimgs.setBackgroundResource(images[score]);

	}

	protected void getmineralsimgs(int score) {

		mineralsimgs.setBackgroundResource(images[score]);

	}

	protected void getfatimgs(int score) {

		fatimgs.setBackgroundResource(images[score]);

	}

	protected void getsugarimgs(int score) {

		sugarimgs.setBackgroundResource(images[score]);

	}

	protected void getenergysimgs(int score) {
		if (score == 0) {
			return;
		}
		energyimgs.setBackgroundResource(energyimg[score - 1]);

	}

	/*************** 获取建议分数 ***************/
	protected void getnextvitaminsimgs(int score) {

		nextvitaminsimgs.setBackgroundResource(jianyiimages[score]);

	}

	protected void getnextproteinsimgs(int score) {
		if (proteinsocre>score&&score!=0) {
			cproteinTextView.setVisibility(View.VISIBLE);
		}
		nextproteinsimgs.setBackgroundResource(jianyiimages[score]);

	}

	protected void getnextmineralsimgs(int score) {
		nextmineralsimgs.setBackgroundResource(jianyiimages[score]);

	}

	protected void getnextfatimgs(int score) {
		
		if (fatsocre>score&&score!=0) {
			cfatTextView.setVisibility(View.VISIBLE);
		}
		
		nextfatimgs.setBackgroundResource(jianyiimages[score]);

	}

	protected void getnextsugarimgs(int score) {

		if (sugarsocre>score&&score!=0) {
			csurgarTextView.setVisibility(View.VISIBLE);
		}
		
		nextsugarimgs.setBackgroundResource(jianyiimages[score]);

	}

	protected void getnextenergysimgs(int score) {
		if (score == 0) {
			return;
		}
		
		if (energysocre>score) {
			cengerTextView.setVisibility(View.VISIBLE);
		}
		nextenergyimgs.setBackgroundResource(jianyiimages[score]);

	}
	
}
