package com.wyy.myhealth.ui.scan;

import java.util.List;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.iflytek.speech.ErrorCode;
import com.iflytek.speech.ISpeechModule;
import com.iflytek.speech.InitListener;
import com.iflytek.speech.RecognizerListener;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechConstant;
import com.iflytek.speech.SpeechRecognizer;
import com.wyy.myhealth.R;
import com.wyy.myhealth.http.AsyncHttpResponseHandler;
import com.wyy.myhealth.http.utils.HealthHttpClient;
import com.wyy.myhealth.ui.baseactivity.BaseActivity;
import com.wyy.myhealth.ui.baseactivity.interfacs.ActivityInterface;
import com.wyy.myhealth.ui.scan.utils.JsonParser;

public class VoiceSearceActivity extends BaseActivity implements ActivityInterface{
	private static final String TAG=VoiceSearceActivity.class.getSimpleName();
	private boolean hasengine=false;
	private SpeechRecognizer speechRecognizer;
	private EditText inputEditText;
	private View submintView;
	private FrameLayout wrapper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice_search);
		initView();
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		context=this;
		wrapper=(FrameLayout)findViewById(R.id.wrapper);
		inputEditText=(EditText)findViewById(R.id.input_food_edit);
	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	
	
	 // 判断手机中是否安装了讯飞语音+
	 private boolean checkSpeechServiceInstall(){
		 String packageName = "com.iflytek.speechcloud";
		 List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
		 for(int i = 0; i < packages.size(); i++){
			 PackageInfo packageInfo = packages.get(i);
			 if(packageInfo.packageName.equals(packageName)){
				 return true;
			 }else{
				 continue;
			 }
		 }
		 return false;
	 }
	 
	 
	 private InitListener mInitListener = new InitListener() {

			@Override
			public void onInit(ISpeechModule module, int code) {
				Log.d(TAG, "SpeechRecognizer init() code = " + code);
	        	if (code == ErrorCode.SUCCESS) {
	        		Toast.makeText(context, "初始化引擎成功", Toast.LENGTH_SHORT).show();
	        	}
			}
	    };
	 
	    
	    
  private RecognizerListener mRecognizerListener = new RecognizerListener.Stub() {
	        
	        @Override
	        public void onVolumeChanged(int v) throws RemoteException {
	        	
	        }
	        
	        @Override
	        public void onResult(final RecognizerResult result, boolean isLast)
	                throws RemoteException {
	        	runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (null != result) {
							
							Log.d(TAG, "recognizer result" + result.getResultString());
							String iattext = JsonParser.parseIatResult(result.getResultString());
							iattext=iattext.replaceAll("。", "");
							String text = inputEditText.getText().toString()+iattext;
							inputEditText.setText(text);
							speechRecognizer.stopListening(mRecognizerListener);
			            } else {
			                Log.d(TAG, "recognizer result : null");
			            }	
					}
				});
	            
	        }
	        
	        @Override
	        public void onError(int errorCode) throws RemoteException {
	        	
	        }
	        
	        @Override
	        public void onEndOfSpeech() throws RemoteException {
	        	endHandler.sendEmptyMessage(0);
	        }
	        
	        @Override
	        public void onBeginOfSpeech() throws RemoteException {
	        	
	        }
	    };  
	    
	    
	    public void setParam(){
			
			speechRecognizer.setParameter(SpeechConstant.LANGUAGE,  "zh_cn");
			speechRecognizer.setParameter(SpeechConstant.VAD_BOS,  "4000");
			speechRecognizer.setParameter(SpeechConstant.VAD_EOS,  "1000");
			
			String param = null;
			param = "asr_ptt="+ "1";
			speechRecognizer.setParameter(SpeechConstant.PARAMS, param+",asr_audio_path=/sdcard/iflytek/wavaudio.pcm");
			
		} 
	
	    
	    
	    private Handler endHandler=new Handler(){
	    	public void handleMessage(android.os.Message msg) {
	    		
	    	};
	    };
	    
	    
	    private void submitKeyWord(){
	    	if (TextUtils.isEmpty(inputEditText.getText().toString())) {
//	    		ToastUtils.showLong(YunYinInput.this, getResources().getString(R.string.nullcontentnotice));
				return;
			}
	    	
	    	HealthHttpClient.cmpFoodWords(inputEditText.getText().toString(), mhHandler);
	    	
	    }

	    
	    private AsyncHttpResponseHandler mhHandler=new AsyncHttpResponseHandler(){
	    	
	    };
	    
}
