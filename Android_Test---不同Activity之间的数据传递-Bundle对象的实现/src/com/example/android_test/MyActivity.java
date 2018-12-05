package com.example.android_test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylayout);
		
		Bundle bundle = this.getIntent().getExtras();
		
		double height = bundle.getDouble("height");
		String sex = bundle.getString("sex");
		
		String text = "身高:" + height + ", 性别:" + sex;
		
		TextView tv = (TextView)findViewById(R.id.myTextView1);
		tv.setText(text);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
