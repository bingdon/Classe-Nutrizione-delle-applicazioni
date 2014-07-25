package com.wyy.myhealth.ui.scan;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mpi.cbg.fly.Feature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.wyy.myhealth.R;
import com.wyy.myhealth.app.WyyApplication;
import com.wyy.myhealth.bean.HealthRecoderBean;
import com.wyy.myhealth.contants.ConstantS;
import com.wyy.myhealth.file.utils.FileUtils;
import com.wyy.myhealth.file.utils.SdUtils;
import com.wyy.myhealth.http.AsyncHttpResponseHandler;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.http.utils.JsonUtils;
import com.wyy.myhealth.imag.utils.PhoneUtlis;
import com.wyy.myhealth.imag.utils.PhotoUtils;
import com.wyy.myhealth.imag.utils.SavePic;
import com.wyy.myhealth.support.picfeure.Align;
import com.wyy.myhealth.ui.photoview.utils.Utility;
import com.wyy.myhealth.welcome.WelcomeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.SurfaceHolder.Callback;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class ScanFragment extends Fragment {

	private static final String TAG = ScanFragment.class.getSimpleName();
	private static final int SPLASH_DELAY_MILLIS = 1000;
	private Camera mCamera;
	private Camera.Parameters parameters;// 照相机参数集
	private FrameLayout mFrameLayout;
	private Activity context;
	public static int angle = 0;// 图像旋转角度
	private int cameraID;
	private boolean threadflag = false;
	private boolean takpic = false;

	private boolean isfit = false;
	// 计算完成标志
	private boolean iscomple = false;
	// 上传返回完成标志
	private boolean isfasongcm = false;

	private boolean backflag = false;

	private int count = 0;// 识别计时

	private boolean foucs = false;

	public ImageView saoImageView;
	// 发挥json数据
	private String json = "";
	
	private cameraView cView;
	
	private boolean isSurface=false;
	
	private ScanView scanView;
	
	private ImageView takepic;
	
	private ImageView openlight;
	
	private ImageView voiceSearch;
	
	private FrameLayout bottomLayout;

	public static ScanFragment newInstance(int postion) {
		ScanFragment scanFragment = new ScanFragment();
		scanFragment.setArguments(new Bundle());
		return scanFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(TAG, "======onCreateView======");
		
		initFilter();
		View rootView = inflater.inflate(R.layout.scan_lay, null);
		initView(rootView);
		return rootView;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i(TAG, "======onCreate======");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "======onDestroy======");
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.i(TAG, "======onDetach======");
		getActivity().unregisterReceiver(pageIndexReceiver);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		Log.i(TAG, "======onLowMemory======");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "======onPause======");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "======onResume======");
		takepic.setEnabled(true);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i(TAG, "======onSaveInstanceState======");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "======onStart======");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "======onActivityCreated======");
		context = getActivity();
		if (count == 0) {
			initCameraView();
			count=1;
		}

	}

	
	
	
	private void initView(View v) {
		saoImageView = (ImageView) v.findViewById(R.id.saomiao_k_img);
		mFrameLayout = (FrameLayout) v.findViewById(R.id.camera_view);
		
		takepic=(ImageView)v.findViewById(R.id.take_pic);
		openlight=(ImageView)v.findViewById(R.id.open_ligth);
		voiceSearch=(ImageView)v.findViewById(R.id.loop_yuyin);
		scanView=(ScanView)v.findViewById(R.id.scanView0);
		bottomLayout=(FrameLayout)v.findViewById(R.id.take_bottom_lay);
		
		ViewTreeObserver vto2 = saoImageView.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				saoImageView.getViewTreeObserver()
						.removeGlobalOnLayoutListener(this);
				ScanView.saoWidth = saoImageView.getWidth();
				ScanView.saoHeigth = saoImageView.getHeight();
			}
		});
		
		
		takepic.setOnClickListener(listener);
		openlight.setOnClickListener(listener);
		voiceSearch.setOnClickListener(listener);
		
	}

	private void initCameraView() {
		if (mCamera == null) {
			mCamera = Camera.open();
		}
		
		cView=new cameraView(context, mCamera);
		mFrameLayout.addView(new cameraView(context, mCamera));

	}

	class cameraView extends SurfaceView implements Callback {
		Size mPreviewSize;
		List<Size> mSupportedPreviewSizes;

		@SuppressWarnings("deprecation")
		public cameraView(Context context, Camera camera) {
			super(context);
			// TODO Auto-generated constructor stub
			SurfaceHolder surfaceHolder;
			mCamera = camera;
			surfaceHolder = getHolder();
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			surfaceHolder.addCallback(this);

			mSupportedPreviewSizes = mCamera.getParameters()
					.getSupportedPreviewSizes();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			if (holder.getSurface() == null) {
				return;
			}
			mCamera.stopPreview();

			mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width,
					height);
			Camera.Parameters parameters = mCamera.getParameters();
			parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
			mCamera.setParameters(parameters);
			// CameraUtils.setCamera(mCamera, width, height, this);
			requestLayout();
			try {
				setCameraDisplayOrientation(context, cameraID, mCamera);
				mCamera.setPreviewDisplay(holder);
				mCamera.startPreview();
			} catch (Exception e) {
				// TODO: handle exception
			}

			// mCamera.takePicture(null, null,BingCamera.this );
			new Thread(face_recon).start();

		}

		private Size getOptimalPreviewSize(List<Size> sizes, int width,
				int height) {
			// TODO Auto-generated method stub
			final double ASPECT_TOLERANCE = 0.2;
			double targetRatio = (double) width / height;
			if (sizes == null)
				return null;

			Size optimalSize = null;
			double minDiff = Double.MAX_VALUE;

			int targetHeight = height;

			// Try to find an size match aspect ratio and size
			for (Size size : sizes) {
				double ratio = (double) size.width / size.height;
				if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
					continue;
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}

			// Cannot find the one match the aspect ratio, ignore the
			// requirement
			if (optimalSize == null) {
				minDiff = Double.MAX_VALUE;
				for (Size size : sizes) {
					if (Math.abs(size.height - targetHeight) < minDiff) {
						optimalSize = size;
						minDiff = Math.abs(size.height - targetHeight);
					}
				}
			}
			return optimalSize;
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub

			isSurface=true;
			
			try {
				// Log.i(TAG, "Camera:" + mCamera);
				if (null == mCamera) {
					mCamera = Camera.open();
				}
				mCamera.setPreviewDisplay(holder);
				mCamera.startPreview();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			isSurface=false;
			if (mCamera != null) {
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();
				mCamera.release(); // release the camera for other applications
				mCamera = null;

			}
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// We purposely disregard child measurements because act as a
			// wrapper to a SurfaceView that centers the camera preview instead
			// of stretching it.
			final int width = resolveSize(getSuggestedMinimumWidth(),
					widthMeasureSpec);
			final int height = resolveSize(getSuggestedMinimumHeight(),
					heightMeasureSpec);
			setMeasuredDimension(width, height);

			if (mSupportedPreviewSizes != null) {
				mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes,
						width, height);
			}
		}

	}

	@SuppressLint("NewApi")
	public static void setCameraDisplayOrientation(Activity activity,
			int cameraId, android.hardware.Camera camera) {
		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
		android.hardware.Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		angle = result;
		ScanFoodActivity.angle = result;
		camera.setDisplayOrientation(result);
	}

	/**
	 * 识别线程
	 */
	Runnable face_recon = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!threadflag) {

				if (takpic) {
					mCamera.autoFocus(bingFocusCallback);
					takpic = false;

				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	};

	AutoFocusCallback bingFocusCallback = new AutoFocusCallback() {

		@Override
		public void onAutoFocus(boolean success, Camera camera) {
			// TODO Auto-generated method stub
			if (success) {
				mCamera.takePicture(null, null, pictureCallback);
				takpic = false;
			} else {
				Toast.makeText(context, R.string.autofousnotice,
						Toast.LENGTH_LONG).show();
				takepic.setEnabled(true);
			}

		}
	};

	PictureCallback pictureCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			Matrix matrix = new Matrix();
			matrix.setRotate(angle);
			Log.d(TAG, "是否可读:" + SdUtils.ExistSDCard());
			if (SdUtils.ExistSDCard()) {
				SavePic.saveToSDCard(data);
			} else {
				PhotoUtils.saveChatCode(data, context);
			}

			mCamera.startPreview();
			backflag = true;

			ExecutorService tExecutorService = Executors.newFixedThreadPool(2);
			tExecutorService.execute(cmpPicRunnable);
			tExecutorService.execute(sendbmp);

			foucs = true;
		}
	};

	// 上传比较线程
	Runnable sendbmp = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			Log.i(TAG, "=============发送线程============");
			isfasongcm = false;
			String mybmp = "";
			if (SdUtils.ExistSDCard()) {

				mybmp = PhoneUtlis.bitmapzoomToString(FileUtils.HEALTH_IMAG
						+ "/wyy.png");
				HealthHttpClient.cmpFoodPic(mybmp, WyyApplication.getInfo()
						.getId(), handler);

			} else {
				mybmp = PhoneUtlis.bitmapToString(context);
				HealthHttpClient.cmpFoodPic(mybmp, WyyApplication.getInfo()
						.getId(), handler);
			}

		}
	};

	// 本地计算线程
	private Runnable cmpPicRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i(TAG, "=============计算线程============");
			isfit = isComfortAble();
			if (isfasongcm) {
				if (isfit && !TextUtils.isEmpty(json)) {
					Log.i(TAG, "=============计算处理============");
					parseJson(json);
				} else {
					logindialogfire();
				}
			}
		}
	};

	// 上传结果处理
	private AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {

		@Override
		public void onSuccess(String content) {
			// TODO Auto-generated method stub
			super.onSuccess(content);
			isfasongcm = true;
			Log.i(TAG, "返回结果:" + content);
			json = content;
			if (iscomple && isfit) {
				Log.i(TAG, "=============发送处理============");
				parseJson(content);
			}

		}

		@Override
		public void onFailure(Throwable error, String content) {
			// TODO Auto-generated method stub
			super.onFailure(error, content);
			isfasongcm = true;
			logindialogfire();
		}

	};

	private void parseJson(String content) {
		JSONObject mJsonObject = null;
		String result = null;

		if (content != null) {
			foucs = false;
			try {
				mJsonObject = new JSONObject(content);
				result = mJsonObject.getString("result");
				if (result.equals("1") && count != 0) {
					Log.i(TAG, "" + count);
					JSONArray comments = mJsonObject.getJSONArray("comments");
					if (comments != null && comments.length() > 0) {

						HealthRecoderBean healthRecoderBean=JsonUtils.getHealthRecoder(comments.getJSONObject(0));
						logindialog(healthRecoderBean);

					} else {
						logindialogfire();
					}

				} else {
					logindialogfire();
				}
				Log.i(TAG, "" + result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i(TAG, "异常");

				// 扫描 失败和成功的 写死测试
				logindialogfire();
			}

		} else {
			if (count != 0) {
				logindialogfire();
			}

		}

		json = "";

	}

	// 比较失败
	public void logindialogfire() {
		takpic = false;
		isfit = false;
		Intent intent = new Intent();
		intent.setClass(context, ScanResultActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	// 比较成功
	public void logindialog(HealthRecoderBean healthRecoderBean) {
		takpic = false;
		isfit = false;
		Log.i("=========", "扫描成功");
		Intent intent = new Intent();
		intent.putExtra("foods", healthRecoderBean);
		intent.setClass(context, ScanResultActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);

	}

	private int secFood() {
		long time = System.currentTimeMillis();
		Align align = new Align();
		List<Feature> fs1;
		fs1 = align.createHistogram(PhoneUtlis
				.getSmallBitmap(FileUtils.HEALTH_IMAG + "/wyy.png"));
		int featureNumber = fs1.size();
		Log.i(TAG, "指数:" + featureNumber + "耗时:"
				+ ((System.currentTimeMillis() - time) / 1000.00) + "s");
		return featureNumber;
	}

	private boolean isComfortAble() {
		iscomple = false;
		int feturenum = secFood();
		if (feturenum > 50 && feturenum < 300) {
			iscomple = true;
			return true;
		}

		iscomple = true;
		return false;
	}

	private BroadcastReceiver pageIndexReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action=intent.getAction();
			if (action.equals(ConstantS.PAGE_INDEX_CHANG)) {
				if (!isSurface) {
					mHandler.sendEmptyMessageDelayed(0, SPLASH_DELAY_MILLIS);
				}
			}else if (action.equals(ConstantS.ACTION_HIDE_UI_CHANGE)) {
				changeUI();
			}
			
			
		}
	};

	private void initFilter() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConstantS.PAGE_INDEX_CHANG);
		filter.addAction(ConstantS.ACTION_HIDE_UI_CHANGE);
		getActivity().registerReceiver(pageIndexReceiver, filter);
	}

	/**
	 * Handler:跳转到不同界面
	 */
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				initCameraView();
				break;
				
			case 1:
				mFrameLayout.addView(cView);
				break;
				
			default:
				break;
			}
			super.handleMessage(msg);
		}

	};

	
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.take_pic:
				takepic2web();
				break;

			case R.id.open_ligth:
				openlight();
				break;
				
			case R.id.loop_yuyin:
				
				break;
				
			default:
				break;
			}
		}
	};
	
	
	
	private void takepic2web(){
		if (Utility.isConnected(getActivity())) {
			takpic=true;
			takepic.setEnabled(false);
			scanView.setScroll(true);
		}else {
			Toast.makeText(getActivity(), R.string.neterro, Toast.LENGTH_SHORT).show();
		}
		
		
	}
	
	
	private void openlight(){
		if (mCamera.getParameters().getFlashMode()
				.equals(Parameters.FLASH_MODE_TORCH)) {
			try {
				parameters = mCamera.getParameters();
				parameters.setFlashMode(Parameters.FLASH_MODE_OFF);// �?��
				mCamera.setParameters(parameters);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			try {
				parameters = mCamera.getParameters();
				parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);// �?��
				mCamera.setParameters(parameters);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	private void changeUI(){
		
		if (scanView.isShown()) {
			scanView.setVisibility(View.INVISIBLE);
			saoImageView.setVisibility(View.INVISIBLE);
			bottomLayout.setVisibility(View.INVISIBLE);
			
			try {
				mCamera.stopPreview();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}else {
			scanView.setVisibility(View.VISIBLE);
			saoImageView.setVisibility(View.INVISIBLE);
			bottomLayout.setVisibility(View.VISIBLE);
			scanView.setScroll(false);
			try {
				mCamera.startPreview();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	
	
	
}
