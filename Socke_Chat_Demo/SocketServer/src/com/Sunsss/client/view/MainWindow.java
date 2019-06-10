package com.Sunsss.client.view;



import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.Sunsss.client.ChatManager;
import com.Sunsss.socket.ServerListener;

import Example1.jiami;
import Example1.jiemi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//txt.setEditable(false);
		txt = new JTextArea();
		txt.setText("Ready...");
		txt.setEditable(false);
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

		JButton button_1 = new JButton("send");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatManager.getChatManager().send(send.getText());
				if (send.getText().length() != 0) {
					appendText("��˵:" + send.getText());
					send.setText("");
				}

			}
		});

		JButton button_2 = new JButton("choose");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int returnVal = chooser.showOpenDialog(button_2);// ��Ϊ��ѡ��
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String filepath = chooser.getSelectedFile().getAbsolutePath();
					// ��ʾ��������

					ChatManager.getChatManager().send("��ѡ����:" + chooser.getSelectedFile().getName());
					appendText("��ѡ����:" + chooser.getSelectedFile().getName());
					File oldfile = new File(filepath);
					File encrypfile = new File("D:\\mytext\\in.txt");

					AES.encryptFile(oldfile, encrypfile);// ��ָ���ļ����м��� ���Ǽ��ܵ�ָ���ļ���
					send.setText("");
					// д��socket��
					try {
						ChatManager.getChatManager().sendFile(encrypfile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					;

				}

			}
		});

		JButton button_3 = new JButton("receive");
		button_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser2 = new JFileChooser();// ��ѡ���ļ���ҳ�棻
				chooser2.setMultiSelectionEnabled(true);
				int returnVal = chooser2.showOpenDialog(button_2);// ��Ϊ��ѡ��
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String filepath = chooser2.getSelectedFile().getAbsolutePath();
					// ��ʾ��������

					ChatManager.getChatManager().send("��ѡ����:" + chooser2.getSelectedFile().getName());
					appendText("��ѡ����:" + chooser2.getSelectedFile().getName());
					try {
						ServerListener.getFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // �ȴ����л����Ϣ��ת��
						// File choosefile = new File(chooser.getSelectedFile().getPath());//ѡ��ļ����ļ�
						// ת�����ļ���ʽ
					File encfile = new File("D:\\mytext\\in.txt");
					File decrypfile = new File("D:\\mytext\\out.txt");
					AES.decryptFile(encfile, decrypfile);// �����ļ����Ķ����ӵ��Ķ�ȥ�ǹ涨��
					send.setText("");
				}

			}
		});

		JButton button_4 = new JButton("simg");
		button_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0)// throws Throwable
			{
				JFileChooser filechoose = new JFileChooser();
				int returnval = filechoose.showOpenDialog(null);// getContentPane());
				if (returnval == JFileChooser.APPROVE_OPTION) {
					File pic = filechoose.getSelectedFile();
					String path = pic.getAbsolutePath();
					ChatManager.getChatManager().send("��ѡ����:" + filechoose.getSelectedFile().getName());
					appendText("��ѡ����:" + filechoose.getSelectedFile().getName());
					Image myimage = null;
					JFrame frame = new JFrame();
					JPanel panel = new JPanel();
					frame.add(panel);
					JLabel pswtip = new JLabel("�ڴ˼�����д��Ϣ");
					JButton okbutton = new JButton("��д");
					JTextField text = new JTextField(20);
					panel.add(pswtip);
					panel.add(text);
					panel.add(okbutton);
					frame.setSize(350, 100);
					frame.addWindowListener(new WindowAdapter() {// ��ֹһ��ҳ��ر�����ҳ�涼�رյ����
						public void windowClosing(WindowEvent e) {
							frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
						}
					});
					frame.setVisible(true);
					okbutton.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent arg0)// throws Throwable
						{
							jiami first = new jiami();
							first.setname(path, "a.bmp");
							String str = text.getText() + "$";
							try {
								first.write(str);
								JOptionPane.showMessageDialog(getContentPane(), "�ɹ�����д�ļ�a.bmp����������");
							} catch (Throwable e) {
								JOptionPane.showMessageDialog(getContentPane(), "��дʧ�ܣ�");
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			}
		});

		JButton button_5 = new JButton("gimg");
		button_5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0)// throws Throwable
			{
				JFileChooser filechoose = new JFileChooser();
				int returnval = filechoose.showOpenDialog(null);// getContentPane());
				if (returnval == JFileChooser.APPROVE_OPTION) {
					File pic = filechoose.getSelectedFile();
					String path = pic.getAbsolutePath();
					// ChatManager.getChatManager().send("��ѡ����:" +
					// filechoose.getSelectedFile().getName());
					// appendText("��ѡ����:" + filechoose.getSelectedFile().getName());
					String text;
					jiemi second = new jiemi();
					try {
						text = second.gettext(path);
						JOptionPane.showMessageDialog(getContentPane(), "��д����ϢΪ" + text);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(getContentPane(), "����ʧ��");
						e.printStackTrace();
					}

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
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)

										.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.DEFAULT_SIZE)
										.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.DEFAULT_SIZE)
										.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.DEFAULT_SIZE)
										.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 30,
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
								.addComponent(button_1).addComponent(button_2).addComponent(button_3)
								.addComponent(button_4).addComponent(button_5))

						.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	public void appendText(String in) {
		txt.append("\n" + in);
	}
}
