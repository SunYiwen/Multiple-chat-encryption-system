package com.zhongqihong.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.zhongqihong.client.ChatManager;
import com.zhongqihong.socket.ServerListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextArea txt;
	private JTextField ip;
	private JTextField send;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txt = new JTextArea();
		txt.setText("Ready...");

		ip = new JTextField();
		ip.setText("127.0.0.1");
		ip.setColumns(10);

		JButton button = new JButton("\u8FDE\u63A5\u5230\u670D\u52A1\u5668");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatManager.getChatManager().connect(ip.getText());
			}
		});

		send = new JTextField();
		send.setText("\u60A8\u597D");
		send.setColumns(10);

		JButton button_1 = new JButton("\u53D1\u9001");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatManager.getChatManager().send(send.getText());
				if (send.getText().length() != 0) {
					appendText("我说:" + send.getText());
					send.setText("");
				}

			}
		});

		JButton button_2 = new JButton("choose");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int returnVal = chooser.showOpenDialog(button_2);//设为多选；
				if (returnVal== JFileChooser.APPROVE_OPTION) { 
					String filepath = chooser.getSelectedFile().getAbsolutePath();
					//显示到界面上
					
					 ChatManager.getChatManager().send("你选择了:"+chooser.getSelectedFile().getName());
					 appendText("你选择了:"+chooser.getSelectedFile().getName());
					 File oldfile = new File(filepath);
					 File encrypfile = new File("D:\\mytext\\in.txt");
					 
					 AES.encryptFile(oldfile, encrypfile);//对指定文件进行加密 但是加密到指定文件；
					 send.setText("");
					 //写入socket流
					 try {
						 ChatManager.getChatManager().sendFile(encrypfile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					};
					 
					
				}
				
			   
			}
		});

		JButton button_3 = new JButton("receive");
		button_3.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser2 = new JFileChooser();//打开选择文件的页面；
				chooser2.setMultiSelectionEnabled(true);
				int returnVal = chooser2.showOpenDialog(button_2);//设为多选；
				if (returnVal== JFileChooser.APPROVE_OPTION) { 
					String filepath = chooser2.getSelectedFile().getAbsolutePath();
					//显示到界面上
					
//					 ChatManager.getChatManager().send("你选择了:"+chooser2.getSelectedFile().getName());
//					 appendText("你选择了:"+chooser2.getSelectedFile().getName());
					try {
						ServerListener.getFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//先从流中获得信息再转换
					// File choosefile = new File(chooser.getSelectedFile().getPath());//选择的加密文件
					 //转换成文件格式
					File encfile  = new File("D:\\mytext\\in.txt");
					File decrypfile  = new File("D:\\mytext\\out.txt"); 
					 AES.decryptFile(encfile, decrypfile);//机密文件从哪儿来从到哪儿去是规定的
					  send.setText("");
				}
				
			   
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(ip, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE).addGap(18)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 119,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(send, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE).addGap(5)
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addGap(5)
										.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.DEFAULT_SIZE)
										.addGap(5).addComponent(button_3, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.DEFAULT_SIZE))
								.addComponent(txt, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txt, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(send, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1).addComponent(button_2).addComponent(button_3))

						.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	public void appendText(String in) {
		txt.append("\n" + in);
	}
}
