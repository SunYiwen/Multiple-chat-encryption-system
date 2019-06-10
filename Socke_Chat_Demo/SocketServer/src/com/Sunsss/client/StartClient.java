package com.Sunsss.client;



import java.awt.EventQueue;

import javax.swing.JOptionPane;

import com.Sunsss.client.view.MainWindow;
import sqldemo.*;

public class StartClient {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_plus now = new login_plus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
