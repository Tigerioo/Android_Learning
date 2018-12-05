package com.example.progressbar_demo_001;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button startButton;
	ProgressDialog progressBar;
	private int progressStatus = 0;
	private Handler progressBarHandler = new Handler();
	
	private long fileSize = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
	}
	
	private void addListenerOnButton(){
		startButton = (Button)findViewById(R.id.button);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				progressBar = new ProgressDialog(v.getContext());
				progressBar.setCancelable(true);
				progressBar.setMessage("Data Backup......");
				progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressBar.setProgress(0);
				progressBar.setMax(100);
				progressBar.show();
				
				progressStatus = 0;
				
				fileSize = 0;
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						while(progressStatus < 100){
							progressStatus = doSomeTasks();
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							progressBarHandler.post(new Runnable() {
								
								@Override
								public void run() {
									progressBar.setProgress(progressStatus);
								}
							});
							
							if(progressStatus >= 100){
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								progressBar.dismiss();
							}
						}
					}
				}).start();
				
			}
		});
	}

	public int doSomeTasks() {
		 
		while (fileSize <= 1000000) {
 
			fileSize++;
 
			if (fileSize == 100000) {
				return 10;
			} else if (fileSize == 200000) {
				return 20;
			} else if (fileSize == 300000) {
				return 30;
			} else if (fileSize == 400000) {
				return 40;
			} else if (fileSize == 500000) {
				return 50;
			} else if (fileSize == 600000) {
				return 60;
			} else if (fileSize == 700000) {
				return 70;
			} else if (fileSize == 800000) {
				return 80;
			} else if (fileSize == 900000) {
				return 90;
			} 
			
 
		}
 
		return 100;
 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
