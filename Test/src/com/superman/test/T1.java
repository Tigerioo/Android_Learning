/**
 * 
 */
package com.superman.test;

import java.io.InputStream;


/**
 * <p>Title: com.superman.test.T1.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2001-2013 Newland SoftWare Company</p>
 *
 * <p>Company: Newland SoftWare Company</p>
 *
 * @author Lewis.Lynn
 *
 * @version 1.0 CreateTime：2014-3-17 下午3:35:43
 */

public class T1 {
	public static void main(String[] args) {
		InputStream inStream = ParseXmlService.class.getClassLoader().getResourceAsStream("http://localhost:8099/docs/version.xml");
		// 解析XML文件。 由于XML文件比较小，因此使用DOM方式进行解析
		ParseXmlService service = new ParseXmlService();
		try
		{
//			mHashMap = service.parseXml(inStream);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
