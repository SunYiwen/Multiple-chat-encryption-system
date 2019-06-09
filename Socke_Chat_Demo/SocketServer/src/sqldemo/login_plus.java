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
        // 创建 JFrame 实例
        JFrame frame = new JFrame("多人加密聊天系统");
        frame.setSize(350, 200);
        frame.addWindowListener(new WindowAdapter() {//防止一个页面关闭所有页面都关闭的情况
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
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        //创建文本域用于用户输入
        JTextField accountText = new JTextField(20);
        accountText.setBounds(100,20,165,25);
        panel.add(accountText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        /* 
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("登陆");
        loginButton.setBounds(100, 80, 60, 25);
        panel.add(loginButton);
        
        //创建注册按钮
        JButton registButton = new JButton("注册");
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
