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
		window.appendText("�ı����Ѿ���Manage����");
	}

	public void connect(String ip) {
		this.IP = ip;
		new Thread() {

			@Override
			public void run() {
				try {
					socket = new Socket(IP, 12345);// �����ͻ��ˣ����ӵĶ˿���ServerSocket�Ķ˿�
					pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
					br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
					String line;

					while ((line = br.readLine()) != null) {
						if (line.length() != 0) {
							window.appendText("�յ�:" + line);
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
			window.appendText("���ж��������������");
		}
	}
}
