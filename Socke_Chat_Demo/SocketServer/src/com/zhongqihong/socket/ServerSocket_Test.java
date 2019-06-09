package com.zhongqihong.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;
public class ServerSocket_Test {

	public static void main(String[] args) {
		new ServerListener().start();
		//运行的方法在command命令下输入:"telnet localhost 12345",每建立一个就是一个客户端，而且每个客户端享受不同的线程，等级是平等的
	}

}
