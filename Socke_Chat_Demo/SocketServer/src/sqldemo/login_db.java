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
			if(userLabel.getText().equals(""))			//�ж��û������Ƿ�Ϊ�գ�
				JOptionPane.showMessageDialog(null, "����д�˺ţ�");
			else if(passwordLabel.getText().equals(""))
				JOptionPane.showMessageDialog(null, "����������");
			else{
				String username = userLabel.getText();
				String password = passwordLabel.getText();
				try {
					connection(); 		//����conn_db�࣬�������ݿ⣻
					boolean com = compareWithSql(username,password);
					if(com) {
						MainWindow frame = new MainWindow();
						frame.setVisible(true);
						ChatManager.getChatManager().setWindow(frame);
						JOptionPane.showMessageDialog(null, "��¼�ɹ�");
						
						}
					else{
						JOptionPane.showMessageDialog(null, "�˺Ż����벻��ȷ������������");
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

	//�û�������
	boolean compareWithSql(String username,String password) throws Exception{
		String sql;		
		Connection con = super.con;
		Statement stmt = con.createStatement();
		sql = "select * from users";
		//System.out.println(sql);
		rs = stmt.executeQuery(sql);
		while(rs.next()){				//�û��������Ϣ�����ݿ��е���Ϣ���Ƚϣ��ж������Ƿ���ȷ��
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