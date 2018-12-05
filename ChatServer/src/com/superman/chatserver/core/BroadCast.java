/**
 * 
 */
package com.superman.chatserver.core;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * <p>Title: com.superman.chatserver.core.BroadCast.java</p>
 *
 * <p>Description: 服务端向客户端广播线程</p>
 *
 * <p>Copyright: Copyright (c) 2001-2013 Newland SoftWare Company</p>
 *
 * <p>Company: Newland SoftWare Company</p>
 *
 * @author Lewis.Lynn
 *
 * @version 1.0 CreateTime：2014-2-28 下午9:43:22
 */

public class BroadCast extends Thread{
	
	private Logger logger = Logger.getLogger(BroadCast.class);
	
	ClientThread clientThread;
	ServerThread serverThread;
	
	String str;
	
	public BroadCast(ServerThread serverThread){
		this.serverThread = serverThread;
	}
	
	//该方法不停的向客户端发送信息
	public void run(){
		while(true){
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				logger.error(e.toString(), e);
			}
			
			synchronized(serverThread.messages){
				if(serverThread.messages.isEmpty()){
					continue;
				}
				//获取服务器端存储的第一条信息
				str = (String)this.serverThread.messages.firstElement();
			}
			
			synchronized(serverThread.clients){
				for (int i = 0; i < serverThread.clients.size(); i++) {
					clientThread = (ClientThread)serverThread.clients.elementAt(i);
					try {
						logger.info("下发消息:" + str);
						clientThread.out.writeUTF(str);
					} catch (IOException e) {
						logger.error(e.toString(), e);
					}
					//从Vector数组中删除已经发送过的那条数据信息
					this.serverThread.messages.removeElement(str);
				}
			}
			
		}
	}
	
}
