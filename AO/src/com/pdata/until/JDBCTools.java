package com.pdata.until;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pdata.tools.JDBC;

public class JDBCTools {
	
	/**
	 * 新建数据表，根据项目别名和属性，加入字段
	 * mulming
	 * creatDataTable
	 * @param alias 项目别名，即表名
	 * @param info 学校学院名和ID
	 * @param args 项目下的字段的别名  和info总共构成数据表的字段
	 * void
	 * 下午11:09:04
	 */
	public void creatDataTable(String alias,List<String> info,List<String> args) {
		Connection connection = null;
	    Statement statement = null;
		//1.1 拼接sql
		StringBuffer sql=new StringBuffer();
		sql.append("CREATE TABLE "+alias+"(");
		sql.append("id int(6) not null auto_increment,");
		for(int i=0;i<info.size();i++) {
			sql.append(info.get(i)+" "+"varchar(32),");
		}
		for(int j=0;j<args.size();j++) {
			sql.append(args.get(j)+" "+"varchar(32),");
		}
		sql.append("PRIMARY KEY (id)"+")ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		try {
			connection = JDBC.getConnection();
			//2.获取Statement
			statement = connection.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBC.releaseDB(null, statement, connection);
		}
		
	}
	
	
	/**
	 * 传入表的名称，返回查询的数据
	 * mulming
	 * findResultSet
	 * @param tableName 表名
	 * @param sql 查询条件
	 * @param len 表的长度
	 * @return 数据结果
	 * List<List<String>>
	 * 下午11:13:09
	 */
	public List<List<String>> findResultSet(String sql,int len){
		List<List<String>> data=new ArrayList<List<String>>();
		ExcelOfData ex=new ExcelOfData();
	    //获取id=2的customers数据表的记录，并打印
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet rs = null;
	    try {
	      //1.获取Connection
	      connection = JDBC.getConnection();
	      //2.获取Statement
	      statement = connection.createStatement();
	      //3.准备Sql
	      //String sql = "select * from t_adm";
	      //4.执行查询，得到ResultSet
	      rs = statement.executeQuery(sql);
	      //5.处理ResultSet
	      while(rs.next()){
	    	 List<String> l=new ArrayList<String>();
	        //rs.get+数据库中对应的类型+(数据库中对应的列别名)
	        for(int i=4;i<=len+3;i++){
	        	l.add(rs.getString(i+1));
	        } 
	        l.add("name_id="+rs.getString(1));
	        data.add(l);
	      }
	      return data; 
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    }finally{
	      //6.关闭数据库相应的资源
	     JDBC.releaseDB(rs, statement, connection);
	    }
	    return null;
	  }

	/**
	 * 给出sql,执行sql语句，-------把数据插入到表中  数据在sql语句中
	 * mulming
	 * insertDateToTabel
	 * @param sql
	 * void
	 * 下午11:16:04
	 */
	public void insertDateToTabel(String sql) {
		Connection connection = null;
	    Statement statement = null;
	    ResultSet rs = null;
	    try {
	      //1.获取Connection
	      connection = JDBC.getConnection();
		  //2.获取Statement
		  statement = connection.createStatement();
		  //3.执行查询，得到ResultSet
		  statement.executeUpdate(sql.toString());
	    }catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBC.releaseDB(null, statement, connection);
		}
	}
	
	/** 教育厅专用
	 * 传入表的名称，返回查询的数据
	 * mulming
	 * findResultSet
	 * @param tableName 表名
	 * @param sql 查询条件
	 * @param len 表的长度
	 * @return 数据结果
	 * List<List<String>>
	 * 下午11:13:09
	 */
	public List<List<String>> findResultSetOfEdu(String sql,int len){
		List<List<String>> data=new ArrayList<List<String>>();
		ExcelOfData ex=new ExcelOfData();
	    //获取id=2的customers数据表的记录，并打印
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet rs = null;
	    try {
	      //1.获取Connection
	      connection = JDBC.getConnection();
	      //2.获取Statement
	      statement = connection.createStatement();
	      //3.准备Sql
	      //String sql = "select * from t_adm";
	      //4.执行查询，得到ResultSet
	      rs = statement.executeQuery(sql);
	      //5.处理ResultSet
	      while(rs.next()){
	    	 List<String> l=new ArrayList<String>();
	        //rs.get+数据库中对应的类型+(数据库中对应的列别名)
	        for(int i=4;i<=len+3;i++){
	        	l.add(rs.getString(i+1));
	        } 
	        data.add(l);
	      }
	      return data; 
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    }finally{
	      //6.关闭数据库相应的资源
	     JDBC.releaseDB(rs, statement, connection);
	    }
	    return null;
	  }
	

}
