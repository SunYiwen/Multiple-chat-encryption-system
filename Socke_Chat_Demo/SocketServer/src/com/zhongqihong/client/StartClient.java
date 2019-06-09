package com.zhongqihong.client;

import java.awt.EventQueue;

import com.zhongqihong.client.view.MainWindow;
import sqldemo.*;

public class StartClient {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_plus now=new login_plus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
