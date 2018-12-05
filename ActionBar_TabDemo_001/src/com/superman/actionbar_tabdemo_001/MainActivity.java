package com.superman.actionbar_tabdemo_001;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener{

	//define a ActionBar
	private ActionBar actionBar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//get a ActionBar
		actionBar = getSupportActionBar();
		//set Navigation mode to NAVIGATION_MODE_TABS
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//add tab
		actionBar.addTab(actionBar.newTab().setText("Tab1").setIcon(R.drawable.ic_launcher).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Tab2").setIcon(R.drawable.ic_launcher).setTabListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBar.TabListener#onTabReselected(android.support.v7.app.ActionBar.Tab, android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBar.TabListener#onTabSelected(android.support.v7.app.ActionBar.Tab, android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction trans) {
		switch (tab.getPosition()) {
		case 0:
			Toast.makeText(this, "This is Tab1!", Toast.LENGTH_SHORT).show();
			Fragment fragment = new DetailFragment();
			break;
		case 1:
			Toast.makeText(this, "This is Tab2!", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBar.TabListener#onTabUnselected(android.support.v7.app.ActionBar.Tab, android.support.v4.app.FragmentTransaction)
	 */
	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

}
