package com.wyy.myhealth.ui.healthrecorder;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import com.wyy.myhealth.R;
import com.wyy.myhealth.contants.ConstantS;
import com.wyy.myhealth.service.MainService;
import com.wyy.myhealth.utils.BingLog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class RecorderChatFragment extends Fragment {

	private static final String TAG=RecorderChatFragment.class.getSimpleName();
	
	private FrameLayout wrapper;

	private static final int TYPE_SIZE = 2;

	private int re_type = ConstantS.YINSHI;

	public static RecorderChatFragment newInstance(int id) {
		RecorderChatFragment recorderChatFragment = new RecorderChatFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(ConstantS.RECEODER, id);
		recorderChatFragment.setArguments(bundle);
		return recorderChatFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		re_type=getArguments().getInt(ConstantS.RECEODER, ConstantS.YINSHI);
		if (savedInstanceState!=null) {
			re_type = savedInstanceState.getInt(ConstantS.RECEODER);
		}
		

	}
	
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		BingLog.i(TAG, "============onDetach============");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.wrapper, container, false);
		wrapper = (FrameLayout) rootView.findViewById(R.id.wrapper);
		wrapper.addView(getChatView());
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState!=null) {
			re_type = savedInstanceState.getInt(ConstantS.RECEODER);
		}
		BingLog.i(TAG, "============onActivityCreated========re_type===="+re_type);
		
		wrapper.addView(getChatView());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt(ConstantS.RECEODER, re_type);
	}

	private XYMultipleSeriesDataset getBarDemoDataset() {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int nr = MainService.getThHealthRecoderBeans().size();
		if (re_type==ConstantS.YUNDONG) {
			nr=MainService.getSports().size();
		}
		for (int i = 0; i < TYPE_SIZE; i++) {
			CategorySeries series = new CategorySeries("Demo series " + (i + 1));
			if (i==0) {
				 series = new CategorySeries(getString(R.string.shijiajilu));
			}else {
				 series = new CategorySeries(getString(R.string.jianyijilu));
			}
		
			for (int k = 0; k < nr; k++) {
				try {
					if (i == 0) {
						series.add(getThReceoder(k));
					} else {
						series.add(getNeReceoder(k));
					}

				} catch (Exception e) {
					// TODO: handle exception
					series.add(0);
				}

			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}

	private void setChartSettings(XYMultipleSeriesRenderer renderer) {
		// renderer.setChartTitle(getString(R.string.health_recoder));
		// renderer.setChartTitleTextSize(50f);
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

	private double getThReceoder(int index) {
		double v = 0;
		switch (re_type) {
		case ConstantS.YINSHI:
			try {
				v=Double.valueOf(MainService.getThHealthRecoderBeans().get(index).getReasonable());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.YUNDONG:
			try {
				v=Double.valueOf(MainService.getSports().get(index));
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.ZHIFANG:
			try {
				v=Double.valueOf(MainService.getThHealthRecoderBeans().get(index).getFat());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.TANGLEI:

			try {
				v=Double.valueOf(MainService.getThHealthRecoderBeans().get(index).getSugar());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;

		case ConstantS.DANGBAIZHI:

			try {
				v=Double.valueOf(MainService.getThHealthRecoderBeans().get(index).getProtein());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.WEISHENGSU:

			try {
				v=Double.valueOf(MainService.getThHealthRecoderBeans().get(index).getVitamin());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.KUANGWUZHI:

			try {
				v=Double.valueOf(MainService.getThHealthRecoderBeans().get(index).getMineral());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		default:
			break;
		}
		
		
		return v;
		
	}
	
	
	
	private double getNeReceoder(int index) {
		double v = 0;
		switch (re_type) {
		case ConstantS.YINSHI:
			try {
				v=Double.valueOf(MainService.getNextHealthRecoderBeans().get(index).getReasonable());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.YUNDONG:

			break;

		case ConstantS.ZHIFANG:
			try {
				v=Double.valueOf(MainService.getNextHealthRecoderBeans().get(index).getFat());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.TANGLEI:

			try {
				v=Double.valueOf(MainService.getNextHealthRecoderBeans().get(index).getSugar());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;

		case ConstantS.DANGBAIZHI:

			try {
				v=Double.valueOf(MainService.getNextHealthRecoderBeans().get(index).getProtein());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.WEISHENGSU:

			try {
				v=Double.valueOf(MainService.getNextHealthRecoderBeans().get(index).getVitamin());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		case ConstantS.KUANGWUZHI:

			try {
				v=Double.valueOf(MainService.getNextHealthRecoderBeans().get(index).getMineral());
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;

		default:
			break;
		}
		
		
		return v;
		
	}
	
	
	

}
