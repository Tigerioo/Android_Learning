/**
 * 
 */
package com.superman.chatserver.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * <p>Title: com.superman.chatserver.core.ClientThread.java</p>
 *
 * <p>Description: 维持服务器与单个客户端的连接线程， 负责接收客户端的消息</p>
 *
 * <p>Copyright: Copyright (c) 2001-2013 Newland SoftWare Company</p>
 *
 * <p>Company: Newland SoftWare Company</p>
 *
 * @author Lewis.Lynn
 *
 * @version 1.0 CreateTime：2014-2-28 下午9:41:16
 */

public class ClientThread extends Thread{
	
	private Logger logger = Logger.getLogger(ClientThread.class);
	Socket clientSocket;
	
	DataInputStream in = null;//输入流
	DataOutputStream out = null;//输出流
	
	ServerThread serverThread = null;
	
	public ClientThread(Socket socket, ServerThread serverThread){
		clientSocket = socket;
		this.serverThread = serverThread;
		
		try {
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		
	}

	//监听对应的客户端是否有信息发送
	public void run(){
		while(true){
			try {
				String message = in.readUTF();
				synchronized(serverThread.messages){
					if(message != null){
						//将客户端发送来的信息存于serverThread的messages数组中
						serverThread.messages.addElement(message);
						logger.info(clientSocket.getInetAddress().getHostAddress() + " 收到信息:" + message);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
