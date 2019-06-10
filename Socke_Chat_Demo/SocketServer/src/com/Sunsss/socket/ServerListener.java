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
		//port参数表示服务器监听的端口号，从1-65535
		try {

			ServerSocket serverSocket =new ServerSocket(12345);
			while (true) {//由于可能当有多个客户端连接时，accept方法就会产生多个Socket对象，需加一个while循环监听来自客户端的连接
			 socket=	serverSocket.accept();//侦听事务的连接，accept是一个阻塞的方法，会阻塞当前的main线程，并且返回的是一个Socket类型
				//建立连接，表示serverSocket在监听，如果监听到有客户端连接则会调用accept方法，然后返回一个Socket，最后建立连接
				JOptionPane.showMessageDialog(null, "有客户端连接到了本机的12345端口");
				
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
			//给出dest地址？
			File directory = new File("D:\\mytext\\out.txt");//将密文写入文件
			if(!directory.exists()) {
				directory.mkdirs();
			}
			File files = new File(directory.getAbsolutePath()+File.separator+fileName);
			FileOutputStream fos = new FileOutputStream(files);
			byte[] bytes = new byte[1024];
			int length = 0;
			while((length = dis.read(bytes, 0, bytes.length))!=-1) {
				fos.write(bytes, 0, length);//应该是写到文件里了，点击按钮触发事件发生
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
