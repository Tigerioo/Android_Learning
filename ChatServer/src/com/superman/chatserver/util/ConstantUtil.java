package com.superman.chatserver.util;

import java.util.Properties;
import org.apache.log4j.Logger;


/**
 * 
 * @author lingq
 * 
 */
public class ConstantUtil {

	private static Logger logger = Logger.getLogger(ConstantUtil.class);
	public static String log4jFilePath = "";	
	
	static {
		Properties pros = System.getProperties();
		String dir = (String)pros.get("user.dir");
		String sepa = (String)pros.get("file.separator");
		if(dir.endsWith("bin")){
			log4jFilePath = "../conf" + sepa + "log4j.properties";	
		}else {
			log4jFilePath = sepa + dir + sepa + "conf" + sepa + "log4j.properties";	
		}
		
	}
	
}