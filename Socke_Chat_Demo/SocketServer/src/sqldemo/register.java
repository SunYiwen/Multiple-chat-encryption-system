package sqldemo;
import java.awt.FlowLayout;
import javax.swing.*;

public class register {
	register_db regist=new register_db() ;	
	register(){
		init();
	}
	public void init() {
		JFrame frame = new JFrame("�û�ע��ҳ��");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel();
		frame.add(panel);
		panel.setLayout(null);

		JLabel userLabel = new JLabel("�û���:");
		userLabel.setBounds(10,20,80,25);
		panel.add(userLabel);

		JTextField accountText = new JTextField(20);
		accountText.setBounds(100,20,165,25);
		panel.add(accountText);

		JLabel passwordLabel = new JLabel("����:");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100,50,165,25);
		panel.add(passwordText);

		JButton sureButton = new JButton("ȷ��");
		sureButton.setBounds(100, 80, 60, 25);
		panel.add(sureButton);

		JButton resetButton = new JButton("����");
		resetButton.setBounds(205, 80, 60, 25);
		panel.add(resetButton);


		frame.setVisible(true);

		sureButton.addActionListener(regist);
		resetButton.addActionListener(regist);	
		regist.setaccountField(accountText);
		regist.setpasswordField(passwordText);
		regist.setokButton(sureButton);
		regist.setresetButton(resetButton);
	}
}