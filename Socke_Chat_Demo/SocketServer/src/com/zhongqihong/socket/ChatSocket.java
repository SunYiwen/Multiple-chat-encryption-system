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
//����һ��Socket����������SocketListener������Socket����
	Socket socket;

	public ChatSocket(Socket s) {
		this.socket = s;
	}

	public void out(String out) {
		try {
			socket.getOutputStream().write((out + "\n").getBytes("UTF-8"));// �������Է������˵�����
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("�Ͽ���һ���ͻ�������");
			ChatManager.getchaChatManager().remove(this);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		out("���Ѿ����ӵ�������");
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));// ��ǰ�������᲻�϶�ȡ��ǰ�ͻ��˵�����
			String line = null;
			while ((line = br.readLine()) != null) {// �ͻ��˷��͸�������������
				// Ȼ��������ٽ����е���Ϣת����ÿһ���ͻ��ˣ�����publish����

				ChatManager.getchaChatManager().publish(this, line);
			}
			br.close();
			System.out.println("�Ͽ���һ���ͻ�������");
			ChatManager.getchaChatManager().remove(this);
		} catch (IOException e) {
			System.out.println("�Ͽ���һ���ͻ�������");
			ChatManager.getchaChatManager().remove(this);
			e.printStackTrace();
		}

	}
}
