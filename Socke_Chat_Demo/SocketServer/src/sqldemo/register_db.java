package sqldemo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
 
import javax.swing.*;
 
public class register_db extends conn_db implements ActionListener{
 
	JTextField username,password;
	JButton okButton,resetButton;
	Statement stmt;
	ResultSet rs;
//	double acc;
//	String name;
//	Connection con = null;
	
	public void setaccountField(JTextField a){
		username = a;
	}
	public void setpasswordField(JTextField n){
		password = n;
	}
	public void setokButton(JButton b1){
		okButton = b1;
	}
	public void setresetButton(JButton b2){
		resetButton = b2;
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == okButton){
			if(username.getText().equals(""))			//�ж��û������Ƿ�Ϊ�գ�
				JOptionPane.showMessageDialog(null, "�������˺�","����Ի���",JOptionPane.WARNING_MESSAGE);
			else if(password.getText().equals(""))
				JOptionPane.showMessageDialog(null,"����������","����Ի���",JOptionPane.WARNING_MESSAGE);
			else{
				String user = username.getText();
				String pass = password.getText();
				System.out.println("username:"+user);
				System.out.println("password:"+pass);
				try {
					connection();
					writeInSql(user,pass);
				} catch (Exception e1) {
					System.out.println("����ʧ��");
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == resetButton){
			username.setText("");
			password.setText("");
		}
	}
	
	void writeInSql(String acc,String name) throws Exception{
	//	System.out.println("into writeSql");
		String sql;
		Connection con = super.con;
		Statement stmt = con.createStatement();
		
		//������������ݿ����û��"my"���򴴽���ִ�в�������������Ѵ��ڣ���ֱ��ִ�в�������������
		//sql = "select * from users";
		
		//stmt.executeUpdate(sql);
		//System.out.println(sql+"successful!");		//���
		//System.out.println("������ɹ�");	
		
		//������ı����л�ȡ�����ݣ�
		sql = "insert into users values('"+acc+"','"+name+"')";
		int rw = stmt.executeUpdate(sql);
		//System.out.println(sql);
		if(rw <= 0){				//�ж������Ƿ����ɹ�
			JOptionPane.showMessageDialog(null,"ע��ʧ��");
		}
		else{
			JOptionPane.showMessageDialog(null, "ע��ɹ�");
		}
	}
}