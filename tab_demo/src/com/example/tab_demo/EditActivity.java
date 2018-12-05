package com.example.tab_demo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditActivity extends Activity {

	private Button keyWordButton;
	private SharedPreferences pre;
	private SharedPreferences.Editor edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_edit);
		super.onCreate(savedInstanceState);

		pre = getSharedPreferences("SmsAlarm", MODE_PRIVATE);
		edit = pre.edit();
		keyWordButton = (Button)findViewById(R.id.myButton1);
		
		
		keyWordButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText keyEdit = (EditText)findViewById(R.id.keyEdit);
				String newKeyword = keyEdit.getText().toString();
				String list_seq = pre.getString("list_seq", "");
				
				if(isExist(list_seq, newKeyword)){//关键字已存在
					Toast.makeText(EditActivity.this, "关键字已经存在!", Toast.LENGTH_SHORT).show();
				}else {//不存在
					int max_list = pre.getInt("max_list", 0);
					String str_max_list = String.valueOf(max_list+1);
					edit.putInt("max_list", (max_list+1));
					edit.putString("key" + str_max_list, newKeyword);
					if(list_seq.equals("")){
						edit.putString("list_seq", str_max_list);
					}else {
						edit.putString("list_seq", "");
					}
					
					edit.commit();
				}
			}
		});
		
	}
	
	/**
	 * 判断新增的关键字是否已经存在
	 * @param list_seq
	 * @param newKeyword
	 * @return
	 */
	private boolean isExist(String list_seq, String newKeyword){
		if(list_seq.equals("1")){
			String temp = pre.getString("key"+list_seq, "");
			if(temp.equals(newKeyword)){
				return true;
			}
		}else {
			String[] arr = list_seq.split("#");
			for (int i = 0; i < arr.length; i++) {
				String temp = pre.getString("key"+arr[i], "");
				if(temp.equals(newKeyword)){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

}
