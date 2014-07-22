package com.wyy.myhealth.ui.fooddetails;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyy.myhealth.R;
import com.wyy.myhealth.bean.Comment;
import com.wyy.myhealth.ui.absfragment.utils.TimeUtils;
import com.wyy.myhealth.ui.baseactivity.BaseListActivity;
import com.wyy.myhealth.ui.fooddetails.FoodCommentAdapter.AdapterListener;

public class FoodCommentInfoActivity extends BaseListActivity implements
		AdapterListener {

	private List<Comment> comments = new ArrayList<>();

	private FoodCommentAdapter adapter;
	// 评论View
	private View sendView;
	// 评论编辑
	private EditText sendEditText;
	// 评论按钮
	private Button sendButton;

	@SuppressWarnings("unchecked")
	@Override
	protected void onInitView() {
		// TODO Auto-generated method stub
		context = this;
		sendView = findViewById(R.id.send_v);
		initSendView(sendView);
		try {
			comments = (List<Comment>) getIntent().getSerializableExtra(
					"comment");
			TimeUtils.getCOmmentTime(comments);
			adapter = new FoodCommentAdapter(comments, context);
			baseListV.setAdapter(adapter);
			adapter.setListener(this);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	protected void onInitActionBar() {
		// TODO Auto-generated method stub
		super.onInitActionBar();
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.commentinfo);
	}

	@Override
	public void comment(int position) {
		// TODO Auto-generated method stub
		if (!sendView.isShown()) {
			sendView.setVisibility(View.VISIBLE);
		}
	}

	private void initSendView(View v) {
		sendButton = (Button) v.findViewById(R.id.send_msg_btn);
		sendEditText = (EditText) v.findViewById(R.id.send_msg_editText);
		sendButton.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.send_msg_btn:

				break;

			default:
				break;
			}
		}
	};

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (sendView.isShown()) {
				sendView.setVisibility(View.GONE);
				return true;
			}
		
		}

		return super.onKeyDown(keyCode, event);
	}
	
}
