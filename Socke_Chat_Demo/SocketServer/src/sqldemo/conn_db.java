package sqldemo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;
 
public class conn_db{
	Connection con=null;
	String url ="jdbc:mysql://localhost:3306/mydata";
	String username="root";
	String password ="Sun7615233";
	public void connection() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("数据库驱动装载成功");
			con = DriverManager.getConnection(url,username,password);
			System.out.println("数据库连接成功！");
			System.out.println("ok");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}