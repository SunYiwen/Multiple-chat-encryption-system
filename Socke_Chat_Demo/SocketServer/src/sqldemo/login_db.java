package sqldemo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import com.zhongqihong.client.ChatManager;
import com.zhongqihong.client.StartClient;
import com.zhongqihong.client.view.*;

public class login_db extends conn_db implements ActionListener {
	JTextField userLabel,passwordLabel;
	JButton loginButton,regisButton;
	register re;
	ResultSet rs;
	public void setusername(JTextField a){
		userLabel = a;
	}
	public void setpasswordLabel(JTextField n){
		passwordLabel = n;
	}
	public void setButton(JButton b1,JButton b2){
		loginButton = b1;
		regisButton = b2;
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == loginButton){
			if(userLabel.getText().equals(""))			//判断用户输入是否为空；
				JOptionPane.showMessageDialog(null, "请填写账号！");
			else if(passwordLabel.getText().equals(""))
				JOptionPane.showMessageDialog(null, "请输入密码");
			else{
				String username = userLabel.getText();
				String password = passwordLabel.getText();
				try {
					connection(); 		//加载conn_db类，连接数据库；
					boolean com = compareWithSql(username,password);
					if(com) {
						MainWindow frame = new MainWindow();
						frame.setVisible(true);
						ChatManager.getChatManager().setWindow(frame);
						JOptionPane.showMessageDialog(null, "登录成功");
						
						}
					else{
						JOptionPane.showMessageDialog(null, "账号或密码不正确，请重新输入");
						userLabel.setText("");
						passwordLabel.setText("");
					}
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == regisButton){
			new JFrame().dispose();
			re = new register();
		}
	}

	//用户输入检查
	boolean compareWithSql(String username,String password) throws Exception{
		String sql;		
		Connection con = super.con;
		Statement stmt = con.createStatement();
		sql = "select * from users";
		//System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while(rs.next()){				//用户输入的信息和数据库中的信息做比较，判断输入是否正确；
			String registername = rs.getString(1);
			String registerpassword = rs.getString(2);
			if(registername.equals(username) && registerpassword.equals(password)){
				//break;
				return true;
			}
			//System.out.println("wrong:"+registername + "   " + registerpassword);
			//System.out.println(username + "   " + password);
		}		
		//System.out.println("hahahaha");
		return false;

	}

}