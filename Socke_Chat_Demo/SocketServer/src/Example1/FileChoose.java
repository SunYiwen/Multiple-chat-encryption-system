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
	JFrame jf=new JFrame("二十四位位图隐写");
	Container container=jf.getContentPane();
	JLabel showpic=new JLabel("显示文件路径",JLabel.CENTER);
	JLabel pswtip=new JLabel("在此键入隐写消息");
	JLabel pathtip=new JLabel("请选择要隐写或要解析的24位位图");
	JButton okbutton=new JButton("隐写");
	JButton choose=new JButton("选择文件");
	JButton undo=new JButton("解析");
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
		//setTitle("文件选择对话框");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar=new JMenuBar();
		JMenu filemenu=new JMenu("文件");
		jf.setJMenuBar(menuBar);
		menuBar.add(filemenu);
		JMenuItem open = new JMenuItem("打开");
		JMenuItem exit = new JMenuItem("退出");
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
				JOptionPane.showMessageDialog(getContentPane(), "成功！隐写文件a.bmp保存在桌面");
			} catch (Throwable e) {
				JOptionPane.showMessageDialog(getContentPane(), "隐写失败！");
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
				showpic.setText("文件路径： "+path);
				
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
				JOptionPane.showMessageDialog(getContentPane(), "隐写的消息为"+text);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(getContentPane(), "解密失败");
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
