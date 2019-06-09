package com.zhongqihong.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread {
//创建一个Socket对象来接收SocketListener传来的Socket对象
	Socket socket;

	public ChatSocket(Socket s) {
		this.socket = s;
	}

	public void out(String out) {
		try {
			socket.getOutputStream().write((out + "\n").getBytes("UTF-8"));// 接收来自服务器端的数据
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("断开了一个客户端链接");
			ChatManager.getchaChatManager().remove(this);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		out("您已经连接到服务器");
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));// 当前服务器会不断读取当前客户端的数据
			String line = null;
			while ((line = br.readLine()) != null) {// 客户端发送给服务器的数据
				// 然后服务器再将所有的信息转发给每一个客户端，调用publish方法

				ChatManager.getchaChatManager().publish(this, line);
			}
			br.close();
			System.out.println("断开了一个客户端链接");
			ChatManager.getchaChatManager().remove(this);
		} catch (IOException e) {
			System.out.println("断开了一个客户端链接");
			ChatManager.getchaChatManager().remove(this);
			e.printStackTrace();
		}

	}
}
