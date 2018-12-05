package com.superman.fragment_test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends Activity implements OnClickListener{

	//短信闹钟fragment
	private AlarmFragment alarmFragment;
	
	//拦截fragment
	private IntercepterFragment intercepterFragment;
	
	//短信闹钟界面布局
	private View alarmView;
	
	//拦截器界面布局
	private View intercepterView;
	
	//管理fragment
	private FragmentManager fragmentManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initViews();
		fragmentManager = getFragmentManager();
		setTabSelection(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.alarm_layout:
			setTabSelection(0);
			break;
		case R.id.intercepter_layout:
			setTabSelection(1);
		default:
			break;
		}
	}

	private void setTabSelection(int index){
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		
		switch (index) {
		case 0:
			if(alarmFragment == null){
				alarmFragment = new AlarmFragment();
				transaction.add(R.id.content, alarmFragment);
			} else {
				transaction.show(alarmFragment);
			}
			break;
		case 1:
			if(intercepterFragment == null){
				intercepterFragment = new IntercepterFragment();
				transaction.add(R.id.content, intercepterFragment);
			} else {
				transaction.show(intercepterFragment);
			}
			break;
		default:
			if(alarmFragment == null){
				alarmFragment = new AlarmFragment();
				transaction.add(R.id.content, alarmFragment);
			} else {
				transaction.show(alarmFragment);
			}
			break;
		}
		transaction.commit();
	}
	
	private void hideFragments(FragmentTransaction transaction){
		if(alarmFragment != null)
			transaction.hide(alarmFragment);
		if(intercepterFragment != null)
			transaction.hide(intercepterFragment);
	}
	
	/**
	 * 初始化布局
	 */
	private void initViews(){
		alarmView = findViewById(R.id.alarm_layout);
		intercepterView = findViewById(R.id.intercepter_layout);
		
		alarmView.setOnClickListener(this);
		intercepterView.setOnClickListener(this);
	}
}
