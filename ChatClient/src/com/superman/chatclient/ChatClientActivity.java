package com.superman.chatclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ChatClientActivity extends Activity implements Runnable{

	private EditText usernameEdit;
	private EditText ipEdit;
	private EditText historyEdit;
	private EditText messageEdit;
	
	private Button loginButton;
	private Button sendButton;
	private Button leaveButton;
	
	//声明字符串， name存储用户名
	//chat_txt存储发言信息
	//chat_in 存储从服务器接收到的信息
	private String username, ip, chat_txt, chat_in;
	private static final int PORT = 8152;
	
	Socket socket;
	
	Thread thread;
	
	DataInputStream in;
	DataOutputStream out;
	
	//是否登录的标记
	boolean flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		//获取main.xml中的一系列组件
		usernameEdit = (EditText)findViewById(R.id.username);
		ipEdit = (EditText)findViewById(R.id.ip);
		historyEdit = (EditText)findViewById(R.id.history);
		messageEdit = (EditText)findViewById(R.id.message);
		
		loginButton = (Button)findViewById(R.id.LoginButton);
		sendButton = (Button)findViewById(R.id.SendButton);
		leaveButton = (Button)findViewById(R.id.LeaveButton);
		
		//为三个按钮注册监听器
		loginButton.setOnClickListener(listener);
		sendButton.setOnClickListener(listener);
		leaveButton.setOnClickListener(listener);
	}
	
	View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			//进入聊天室的处理
			case R.id.LoginButton:
				if(flag){
					Toast.makeText(ChatClientActivity.this, "已经登陆过了!", Toast.LENGTH_SHORT).show();
					return;
				}
				//获取用户名
				username = usernameEdit.getText().toString();
				//获取服务器IP
				ip = ipEdit.getText().toString();
				//判断用户名是否有效及ip是否有效
				if(username != "" && username != null && username != "用户名输入" && ip != null){
					try {
						//创建socket对象
						socket = new Socket(ip, PORT);
						
						//创建客户端数据输入/输出流
						in = new DataInputStream(socket.getInputStream());
						out = new DataOutputStream(socket.getOutputStream());
						
						Date now = new Date(System.currentTimeMillis());
						DateFormat format = new SimpleDateFormat("HH:mm:ss");
						String nowStr = format.format(now);
						
						out.writeUTF("$$" + username + " " + nowStr + " 上线了！");
						
					} catch (UnknownHostException e) {
						Log.e("show", e.toString());
						Toast.makeText(ChatClientActivity.this, "server connect failure : " + e.toString(), Toast.LENGTH_SHORT).show();
						return ;
					} catch (IOException e) {
						Log.e("show", e.toString());
						Toast.makeText(ChatClientActivity.this, "server connect failure : " + e.toString(), Toast.LENGTH_SHORT).show();
						return ;
					}
					
					thread = new Thread(ChatClientActivity.this);
					//开启线程，监听服务器端是否有消息
					thread.start();
					//说明已经登录成功
					flag = true;
				}
				break;
			case R.id.SendButton:
				if(!flag){
					Toast.makeText(ChatClientActivity.this, "没有登录，请登录!", Toast.LENGTH_SHORT).show();
					return;
				}
				//获取客户端输入的发言内容
				chat_txt = messageEdit.getText().toString();
				if(chat_txt != null){
					Date now = new Date(System.currentTimeMillis());
					DateFormat format = new SimpleDateFormat("HH:mm:ss");
					String nowStr = format.format(now);
					
					try {
						//发言，向服务器发送消息
						out.writeUTF("^_^" + username + " " + nowStr + " 说\n" + chat_txt);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					try {
						out.writeUTF("请发言");
					} catch (IOException e) {
						Log.d("chat", e.toString());
					}
				}
				messageEdit.setText("");
				break;
			//退出聊天室的按钮事件处理
			case R.id.LeaveButton:
				if(flag){
					if(!flag){
						Toast.makeText(ChatClientActivity.this, "没有登录，请登录!", Toast.LENGTH_SHORT).show();
						return ;
					}
					try {
						out.writeUTF("==" + username + "下线了！");
					} catch (IOException e) {
						e.printStackTrace();
					} finally{
						try {
							out.close();
							in.close();
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				flag = false;
				Toast.makeText(ChatClientActivity.this, "已退出！", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat_client, menu);
		return true;
	}

	@Override
	public void run() {
		while(true){
			try {
				//读取服务器发来的信息
				chat_in = in.readUTF();
				chat_in = chat_in + "\n";
				mHandler.sendMessage(mHandler.obtainMessage());
			} catch (IOException e) {
				Log.e("e", e.toString());
			}
		}
	}
	
	Handler mHandler = new Handler(){
		//将消息显示在客户端的对话框中
		public void handleMessage(Message msg){
			historyEdit.append(chat_in);
			//刷新
			super.handleMessage(msg);
		}
		
	};

}
