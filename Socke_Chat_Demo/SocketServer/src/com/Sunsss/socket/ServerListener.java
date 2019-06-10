package com.Sunsss.socket;



import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {
	static Socket socket;
	
	public void run() {
		//port������ʾ�����������Ķ˿ںţ���1-65535
		try {

			ServerSocket serverSocket =new ServerSocket(12345);
			while (true) {//���ڿ��ܵ��ж���ͻ�������ʱ��accept�����ͻ�������Socket�������һ��whileѭ���������Կͻ��˵�����
			 socket=	serverSocket.accept();//������������ӣ�accept��һ�������ķ�������������ǰ��main�̣߳����ҷ��ص���һ��Socket����
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
	
	public static void getFile() throws IOException {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String fileName = dis.readUTF();
			long fileLength = dis.readLong();
			//����dest��ַ��
			File directory = new File("D:\\mytext\\out.txt");//������д���ļ�
			if(!directory.exists()) {
				directory.mkdirs();
			}
			File files = new File(directory.getAbsolutePath()+File.separator+fileName);
			FileOutputStream fos = new FileOutputStream(files);
			byte[] bytes = new byte[1024];
			int length = 0;
			while((length = dis.read(bytes, 0, bytes.length))!=-1) {
				fos.write(bytes, 0, length);//Ӧ����д���ļ����ˣ������ť�����¼�����
				fos.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
//			try {
////				if(fos!=null) {
////					fos.close();
////				}
////				if(dis!=null) {
////					dis.close();
////				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
		}
		
		
	}
}
