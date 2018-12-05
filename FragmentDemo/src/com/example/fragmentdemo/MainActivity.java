package com.example.fragmentdemo;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
//		getActionBar().setDisplayShowTitleEnabled(false);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
//		MenuItem searchItem = menu.findItem(R.id.action_search);
//		SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);
		
//		 SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//		    SearchView searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
//		    if(searchView == null) System.out.println("searchView is null");
//		    // Assumes current activity is the searchable activity
//		    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//		    searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default

		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	            Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();
	            return true;
	        case R.id.action_compose:
	        	Toast.makeText(MainActivity.this, "compose", Toast.LENGTH_SHORT).show();
	            return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
