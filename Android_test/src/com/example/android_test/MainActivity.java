package com.example.android_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Toast.makeText(this, "Hello World!", Toast.LENGTH_SHORT).show();
		
		listView = new ListView(this);
		String[] str = new String[]{"张三", "张三", "张三"};
		ArrayAdapter<?> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
		listView.setAdapter(adapter);
		setContentView(listView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
