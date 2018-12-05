/**
 * 
 */
package com.superman.widget_demo_001;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * <p>Title: com.superman.widget_demo_001.WidgetProvider.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2001-2013 Newland SoftWare Company</p>
 *
 * <p>Company: Newland SoftWare Company</p>
 *
 * @author Lewis.Lynn
 *
 * @version 1.0 CreateTime：2014-3-20 下午8:08:49
 */

public class WidgetProvider extends AppWidgetProvider {
	public static String EXTRA_WORD=
		    "com.commonsware.android.appwidget.lorem.WORD";

		@SuppressWarnings("deprecation")
		@SuppressLint("NewApi")
		@Override
		  public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager,
		                        int[] appWidgetIds) {
		    for (int i=0; i<appWidgetIds.length; i++) {
		      Intent svcIntent=new Intent(ctxt, WidgetService.class);
		      
		      svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
		      svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
		      
		      RemoteViews widget=new RemoteViews(ctxt.getPackageName(),
		                                          R.layout.widget);
		      
		      widget.setRemoteAdapter(appWidgetIds[i], R.id.words,
		                              svcIntent);
//		      widget.setRemoteAdapter(R.id.words, svcIntent);

		      Intent clickIntent=new Intent(ctxt, LoremActivity.class);
		      PendingIntent clickPI=PendingIntent
		                              .getActivity(ctxt, 0,
		                                            clickIntent,
		                                            PendingIntent.FLAG_UPDATE_CURRENT);
		      
		      widget.setPendingIntentTemplate(R.id.words, clickPI);

		      appWidgetManager.updateAppWidget(appWidgetIds[i], widget);
		    }
		    
		    super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
		  }
}
