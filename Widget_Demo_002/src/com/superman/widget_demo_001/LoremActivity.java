/**
 * 
 */
package com.superman.widget_demo_001;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * <p>Title: com.superman.widget_demo_001.LoremActivity.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2001-2013 Newland SoftWare Company</p>
 *
 * <p>Company: Newland SoftWare Company</p>
 *
 * @author Lewis.Lynn
 *
 * @version 1.0 CreateTime：2014-3-20 下午8:09:51
 */

public class LoremActivity extends Activity{
	@Override
	  public void onCreate(Bundle state) {
	    super.onCreate(state);
	    setContentView(R.layout.main);
	    
	    String word=getIntent().getStringExtra(WidgetProvider.EXTRA_WORD);
	    
	    if (word==null) {
	      word="We did not get a word!";
	    }
	    
	    Toast.makeText(this, word, Toast.LENGTH_LONG).show();
	    AlertDialog.Builder builder = new AlertDialog.Builder(LoremActivity.this);
		// Get the layout inflater
		LayoutInflater inflater = getLayoutInflater();
		final View add_view = inflater.inflate(R.layout.main, null);
		builder.setView(add_view).setPositiveButton("add",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,int id) {
						Toast.makeText(LoremActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
					}
				})
		.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
						dialog.cancel();
					}
				}).show();

	    finish();
	  }
}
