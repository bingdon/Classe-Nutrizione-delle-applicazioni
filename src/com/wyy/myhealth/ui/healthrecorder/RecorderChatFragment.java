package com.wyy.myhealth.ui.healthrecorder;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import com.wyy.myhealth.R;
import com.wyy.myhealth.service.MainService;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class RecorderChatFragment extends Fragment {

	private FrameLayout wrapper;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.wrapper, container, false);
		wrapper=(FrameLayout)rootView.findViewById(R.id.wrapper);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		wrapper.addView(getChatView());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	private XYMultipleSeriesDataset getBarDemoDataset() {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		final int nr = MainService.getThHealthRecoderBeans().size();
		Random r = new Random();
		for (int i = 0; i < 2; i++) {
			CategorySeries series = new CategorySeries("Demo series " + (i + 1));
			for (int k = 0; k < nr; k++) {
				try {
					if (i==0) {
						series.add(Double.valueOf(MainService.getThHealthRecoderBeans().get(k).getEnergy()));
					}else {
						series.add(Double.valueOf(MainService.getNextHealthRecoderBeans().get(k).getFat()));
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					series.add(0);
//					series.add(100 + r.nextInt() % 100);
				}
				
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}

	private void setChartSettings(XYMultipleSeriesRenderer renderer) {
//		renderer.setChartTitle(getString(R.string.health_recoder));
//		renderer.setChartTitleTextSize(50f);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.GRAY);
		renderer.setMarginsColor(Color.GRAY);
		renderer.setXTitle("x values");
		renderer.setYTitle("y values");
		renderer.setXAxisMin(0.5);
		renderer.setXAxisMax(10.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(5);
	}

	public XYMultipleSeriesRenderer getBarDemoRenderer() {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[] { 20, 30, 15, 0 });
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r);
		r = new SimpleSeriesRenderer();
		r.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r);
		return renderer;
	}

	private View getChatView() {
		XYMultipleSeriesRenderer renderer = getBarDemoRenderer();
		setChartSettings(renderer);
		return ChartFactory.getBarChartView(getActivity(), getBarDemoDataset(),
				renderer, Type.DEFAULT);
	}

}
