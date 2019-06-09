package Example1;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class FileChoose extends JFrame{
	String path="example.bmp";
	String str;
			//C:\\Users\\john\\Desktop\\original_5MV1_7216000155f2118f.jpg";
	JFrame jf=new JFrame("��ʮ��λλͼ��д");
	Container container=jf.getContentPane();
	JLabel showpic=new JLabel("��ʾ�ļ�·��",JLabel.CENTER);
	JLabel pswtip=new JLabel("�ڴ˼�����д��Ϣ");
	JLabel pathtip=new JLabel("��ѡ��Ҫ��д��Ҫ������24λλͼ");
	JButton okbutton=new JButton("��д");
	JButton choose=new JButton("ѡ���ļ�");
	JButton undo=new JButton("����");
	JTextField text=new JTextField();
	public static void main(String[] args) {
		//new test().CreateFrame("hello");
		//new myframe().createframe();
		//String path;
		FileChoose frame=new FileChoose();
		//frame.setVisible(true);
		//System.out.println(FileChoose.)
	}
	public FileChoose()
	{
		super();
		container.add(showpic);
		container.add(okbutton);
		container.add(choose);
		container.add(text);
		container.add(undo);
		container.add(pswtip);
		container.add(pathtip);
		container.setLayout(null);
		jf.setVisible(true);
		jf.setSize(600,550);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		okbutton.setBounds(400, 400,100,30);
		undo.setBounds(400, 450, 100, 30);
		choose.setBounds(250, 320,100,30);
		pathtip.setBounds(200, 60, 200, 30);
		text.setBounds(200, 400, 170, 30);
		showpic.setIcon(new ImageIcon("example.jpg"));
		showpic.setHorizontalTextPosition(JLabel.CENTER);
		showpic.setVerticalTextPosition(JLabel.BOTTOM);
		pswtip.setBounds(80, 400, 120,30);
		
		jf.add(showpic);
		showpic.setBounds(100, 0, 400,400);
		//setBounds(100,100,300,200);
		//setTitle("�ļ�ѡ��Ի���");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar=new JMenuBar();
		JMenu filemenu=new JMenu("�ļ�");
		jf.setJMenuBar(menuBar);
		menuBar.add(filemenu);
		JMenuItem open = new JMenuItem("��");
		JMenuItem exit = new JMenuItem("�˳�");
		filemenu.add(open);
		filemenu.add(exit);
		
		
		exit.addActionListener(new ActionListener()

		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		/*
		open.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e)
			{
				JFileChooser filechoose = new JFileChooser();
				int returnval=filechoose.showOpenDialog(null);//getContentPane());
				if(returnval==JFileChooser.APPROVE_OPTION)
				{
					File pic = filechoose.getSelectedFile();
					path=pic.getAbsolutePath();
					Image myimage =null;
			        try{
			            myimage=ImageIO.read(new File(path));
			        }catch(IOException ex){}
			        

					showpic.setIcon(new ImageIcon(myimage));
					
					//showpic.setBounds(300, 300, 200, 200);
					//System.out.println(path);
			
				}
			}
		});
		*/
		okbutton.addMouseListener(new MouseAdapter()
		{
		public void mouseClicked (MouseEvent arg0)//throws Throwable
		{
			jiami first = new jiami();
			first.setname(path,  "a.bmp");
			str=text.getText()+"$";
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
		choose.addMouseListener(new MouseAdapter()
		{
		public void mouseClicked (MouseEvent arg0)//throws Throwable
		{
			JFileChooser filechoose = new JFileChooser();
			int returnval=filechoose.showOpenDialog(null);//getContentPane());
			if(returnval==JFileChooser.APPROVE_OPTION)
			{
				File pic = filechoose.getSelectedFile();
				path=pic.getAbsolutePath();
				Image myimage =null;
		        try{
		            myimage=ImageIO.read(new File(path));
		        }catch(IOException ex){}
		        

				showpic.setIcon(new ImageIcon(myimage));
				showpic.setText("�ļ�·���� "+path);
				
				//showpic.setBounds(300, 300, 200, 200);
				//System.out.println(path);
		
			}
		}
		});
		undo.addMouseListener(new MouseAdapter()
		{
		public void mouseClicked (MouseEvent arg0)//throws Throwable
		{
			String text;
			jiemi second = new jiemi();
			try {
				text=second.gettext(path);
				JOptionPane.showMessageDialog(getContentPane(), "��д����ϢΪ"+text);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(getContentPane(), "����ʧ��");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			
		}
		});
		/*
		choose.addMouseListener(new MouseAdapter()
		{
		public void mouseClicked (MouseEvent arg0)//throws Throwable
		{
			JFileChooser filechoose = new JFileChooser();
			int returnval=filechoose.showOpenDialog(null);//getContentPane());
			if(returnval==JFileChooser.APPROVE_OPTION)
			{
				File pic = filechoose.getSelectedFile();
				path=pic.getAbsolutePath();
				Image myimage =null;
		        try{
		            myimage=ImageIO.read(new File(path));
		        }catch(IOException ex){}
			
		}
		});
		*/
		
	
		}

}
