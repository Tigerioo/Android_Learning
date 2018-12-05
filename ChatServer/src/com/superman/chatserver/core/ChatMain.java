/**
 * 
 */
package com.superman.chatserver.core;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.superman.chatserver.util.ConstantUtil;

/**
 * <p>Title: com.superman.chatserver.core.RunMain.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2001-2013 Newland SoftWare Company</p>
 *
 * <p>Company: Newland SoftWare Company</p>
 *
 * @author Lewis.Lynn
 *
 * @version 1.0 CreateTime：2014-2-28 下午10:46:09
 */

public class ChatMain {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(ChatMain.class);
		PropertyConfigurator.configure(ConstantUtil.log4jFilePath);
		ServerThread server = null;
		try {
			server = new ServerThread(); 
			server.start();
		} catch (Exception e) {
			logger.error(e.toString(), e);
			server.finalize();
		}
	}
}
