package com.zhongqihong.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;
public class ServerSocket_Test {

	public static void main(String[] args) {
		new ServerListener().start();
	}

}
