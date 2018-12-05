package com.example.choosefile_demo_001;

import java.net.URI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	final int ACTIVITY_CHOOSE_FILE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button) this.findViewById(R.id.Button01);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent tmpIntent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
				startActivityForResult(tmpIntent, 0);
/*				Intent chooseFile;
				Intent intent;
				chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
				chooseFile.setType("image/*");
				intent = Intent.createChooser(chooseFile, "Choose a file");
				startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);*/
				/*String[] proj = { MediaStore.Images.Media.DATA };
				StringBuilder buff = new StringBuilder();
				for (int i = 0; i < proj.length; i++) {
					buff.append(proj[i] + "\n");
					Toast.makeText(MainActivity.this, proj[i],Toast.LENGTH_SHORT).show();
				}
				TextView tv = (TextView)findViewById(R.id.text);
				tv.setText(buff.toString());*/
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0: {
			if (resultCode == RESULT_OK) {
				Uri uri=data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				String str = getRealPathFromURI(this, uri);
				Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
//				String filePath = getRealPathFromURI(this, uri);
				/*ImageView imageView = (ImageView) findViewById(R.id.image);
				File file = new File(filePath);
				if (file.exists()) {
					Toast.makeText(MainActivity.this, filePath,
							Toast.LENGTH_SHORT).show();
					Bitmap bitmap = BitmapFactory.decodeFile(filePath);
					imageView.setImageBitmap(bitmap);
				} else {
					Toast.makeText(MainActivity.this, "not exist",
							Toast.LENGTH_SHORT).show();
					imageView.setBackgroundResource(R.drawable.ic_launcher);
				}*/
				/*File file = new File(filePath);
				TextView tv = (TextView)findViewById(R.id.text);
				if(file.exists()){
					tv.setText(filePath);
				}else {
					tv.setText("File not exists");
				}*/
			}
		}
		}
	}

	public String getRealPathFromURI(Context context, Uri contentUri) {
		  Cursor cursor = null;
		  try { 
		    String[] proj = { MediaStore.Audio.Media.DATA };
		    cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
		    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
		    cursor.moveToFirst();
		    return cursor.getString(column_index);
		  } finally {
		    if (cursor != null) {
		      cursor.close();
		    }
		  }
		}
}
