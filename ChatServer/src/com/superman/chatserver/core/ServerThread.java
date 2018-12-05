/**
 * 
 */
package com.superman.chatserver.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * <p>Title: com.superman.chatserver.core.ServerThread.java</p>
 *
 * <p>Description: 服务器监听端口线程</p>
 *
 * <p>Copyright: Copyright (c) 2001-2013 Newland SoftWare Company</p>
 *
 * <p>Company: Newland SoftWare Company</p>
 *
 * @author Lewis.Lynn
 *
 * @version 1.0 CreateTime：2014-2-28 下午9:38:55
 */

public class ServerThread extends Thread{
	
	private Logger logger = Logger.getLogger(ServerThread.class);
	ServerSocket serverSocket;
	public static final int PORT = 8152;
	
	//创建一个vector对象，用来存储客户端连接的ClientThread对象
	Vector<ClientThread> clients;
	
	//创建一个Vector对象，用于存储客户端发送过来的信息
	Vector<Object> messages;
	
	//广播类， 负责向客户端广播消息
	BroadCast broadCast;
	
	String ip = "115.28.220.6";
	
//	InetAddress myIPaddress = null;
	
	public ServerThread(){
		//创建两个vector数据
		//client 负责储存所有与服务器建立连接的客户端
		//messages 负责储存服务器接收到的未发送出去的全部客户端消息
		clients = new Vector<ClientThread>();
		messages = new Vector<Object>();
		
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		
		//获取本地服务器地址信息
//		try {
//			myIPaddress = InetAddress.getLocalHost();
//		} catch (UnknownHostException e) {
//			logger.error(e.toString(), e);
//		}
//		
//		ip = myIPaddress.getHostAddress();
		logger.info("服务器地址： " + ip + " PORT: " + PORT);
		
		//创建广播线程并启动
		broadCast = new BroadCast(this);
		broadCast.start();
		
	}

	public void run() {
		while(true){
			try {
				Socket socket = serverSocket.accept();
				logger.info("接收到来自:" + socket.getInetAddress().getHostAddress() +  " 的信息");
				ClientThread clientThread = new ClientThread(socket, this);
				clientThread.start();
				
				if(socket != null){
					synchronized(clients){
						clients.addElement(clientThread);
					}
				}
			} catch (IOException e) {
				logger.error("建立客户端联机失败：" + e.toString(), e);
			}
		}
	}
	
	public void finalize(){
		try {
			serverSocket.close();
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
	}
}
