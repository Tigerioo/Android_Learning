/**
 * 
 */
package com.superman.widget_demo_001;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * <p>Title: com.superman.widget_demo_001.WidgetService.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2001-2013 Newland SoftWare Company</p>
 *
 * <p>Company: Newland SoftWare Company</p>
 *
 * @author Lewis.Lynn
 *
 * @version 1.0 CreateTime：2014-3-20 下午8:01:50
 */

@SuppressLint("NewApi")
public class WidgetService extends RemoteViewsService {

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return(new LoremViewsFactory(this.getApplicationContext(),
                intent));
	}

}
