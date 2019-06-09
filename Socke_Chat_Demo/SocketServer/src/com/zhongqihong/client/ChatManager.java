package com.zhongqihong.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.zhongqihong.client.view.MainWindow;

public class ChatManager {
	private ChatManager() {
	}

	private static final ChatManager instance = new ChatManager();

	public static ChatManager getChatManager() {
		return instance;
	}

	MainWindow window;
	String IP;
	Socket socket;
	BufferedReader br;
	PrintWriter pw;

	public void setWindow(MainWindow window) {
		this.window = window;
		window.appendText("文本框已经和Manage绑定了");
	}

	public void connect(String ip) {
		this.IP = ip;
		new Thread() {

			@Override
			public void run() {
				try {
					socket = new Socket(IP, 12345);// 创建客户端，连接的端口是ServerSocket的端口
					pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
					br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					String line;

					while ((line = br.readLine()) != null) {
						if (line.length() != 0) {
							window.appendText("收到:" + line);
						}

					}
					br.close();
					pw.close();
					pw = null;
					br = null;

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void send(String out) {
		if (pw != null) {
			pw.write(out + "\n");
			pw.flush();
		} else {
			window.appendText("已中断与服务器的连接");
		}
	}
}
