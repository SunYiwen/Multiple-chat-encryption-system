package com.zhongqihong.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {
	@Override
	public void run() {
		//port������ʾ�����������Ķ˿ںţ���1-65535
		try {

			ServerSocket serverSocket =new ServerSocket(12345);
			while (true) {//���ڿ��ܵ��ж���ͻ�������ʱ��accept�����ͻ�������Socket�������һ��whileѭ���������Կͻ��˵�����
				Socket socket=	serverSocket.accept();//������������ӣ�accept��һ�������ķ�������������ǰ��main�̣߳����ҷ��ص���һ��Socket����
				//�������ӣ���ʾserverSocket�ڼ���������������пͻ�������������accept������Ȼ�󷵻�һ��Socket�����������
				JOptionPane.showMessageDialog(null, "�пͻ������ӵ��˱�����12345�˿�");
				
				ChatSocket cs= new ChatSocket(socket);
			     cs.start();
			     ChatManager.getchaChatManager().add(cs);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
