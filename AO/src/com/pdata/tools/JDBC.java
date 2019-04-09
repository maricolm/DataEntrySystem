package com.pdata.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import com.mysql.jdbc.Driver;

/*
 * mulming
 * 下午2:56:49
 */
public class JDBC {
	
	//获取数据库连接
	public static Connection getConnection() throws SQLException {
		Driver driver=new com.mysql.jdbc.Driver();
		String url="jdbc:mysql://127.0.0.1:3306/oa?useUnicode=true&amp;characterEncoding=utf-8";
		//String url="jdbc:mysql://47.104.12.22:3306/oa?useUnicode=true&amp;characterEncoding=utf-8";
		Properties info=new Properties();
		info.put("user","root");
		info.put("password", "admin");
		Connection con=driver.connect(url,info);
		return con;
	}
	
	//释放数据库连接资源
	public static void releaseDB(ResultSet re,Statement st,Connection con){
		if(re!=null){
			try {
				re.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
 
}
