package com.superman.actionbar_tabdemo_001;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//get a ActionBar
		ActionBar actionBar = getSupportActionBar();
		
		//set Navigation mode to NAVIGATION_MODE_TABS
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab alarmTab = actionBar.newTab().setText("Tab1").setIcon(R.drawable.ic_launcher);
		ActionBar.Tab intercepterTab = actionBar.newTab().setText("Tab2").setIcon(R.drawable.ic_launcher);
		
		AlarmFragment alarmFragment = new AlarmFragment();
		IntercepterFragment intercepterFragment = new IntercepterFragment();
		
		alarmTab.setTabListener(new MyListener(alarmFragment));
		intercepterTab.setTabListener(new MyListener(intercepterFragment));
		
		//add tab
		actionBar.addTab(alarmTab);
		actionBar.addTab(intercepterTab);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	public class MyListener implements ActionBar.TabListener{
		
		private Fragment fragment;
		
		public MyListener(Fragment fragment){
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			
		}

		@Override
		public void onTabSelected(ActionBar.Tab tab, FragmentTransaction transaction) {
			System.out.println(tab.getPosition());
			transaction.replace(R.id.content, fragment);
		}

		/* (non-Javadoc)
		 * @see android.support.v7.app.ActionBar.TabListener#onTabUnselected(android.support.v7.app.ActionBar.Tab, android.support.v4.app.FragmentTransaction)
		 */
		@Override
		public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction transaction) {
			transaction.remove(fragment);
		}
		
	}
	
}
