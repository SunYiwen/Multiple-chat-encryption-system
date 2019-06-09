package com.zhongqihong.client;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import com.zhongqihong.client.view.MainWindow;
import sqldemo.*;

public class StartClient {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//login_plus log=new login_plus();
					JOptionPane.showMessageDialog(null, "µÇÂ¼³É¹¦");
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					ChatManager.getChatManager().setWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
