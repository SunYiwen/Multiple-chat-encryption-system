package sqldemo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login_plus {
	public static void main(String[] args) {    
        // ���� JFrame ʵ��
        JFrame frame = new JFrame("���˼�������ϵͳ");
        frame.setSize(350, 200);
        frame.addWindowListener(new WindowAdapter() {//��ֹһ��ҳ��ر�����ҳ�涼�رյ����
            public void windowClosing(WindowEvent e) {
                frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        JPanel panel = new JPanel();    
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel userLabel = new JLabel("�û���:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        //�����ı��������û�����
        JTextField accountText = new JTextField(20);
        accountText.setBounds(100,20,165,25);
        panel.add(accountText);

        // ����������ı���
        JLabel passwordLabel = new JLabel("����:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        /* 
         *�����������������ı���
         * �����������Ϣ���Ե�Ŵ��棬���ڰ�������İ�ȫ��
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // ������¼��ť
        JButton loginButton = new JButton("��½");
        loginButton.setBounds(100, 80, 60, 25);
        panel.add(loginButton);
        
        //����ע�ᰴť
        JButton registButton = new JButton("ע��");
        registButton.setBounds(205, 80, 60, 25);
        panel.add(registButton);
        
        login_db log;
        log = new login_db();
        loginButton.addActionListener(log);
		registButton.addActionListener(log);
		log.setusername(accountText);
		log.setpasswordLabel(passwordText);
		log.setButton(loginButton,registButton);
    }
}
