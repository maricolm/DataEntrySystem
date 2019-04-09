/**  
 * @Title:  Jdbcs.java   
 * @Package test   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: muliming     
 * @date:   2018年4月25日 下午5:54:44   
 * @Copyright: 2018
 */  
package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.Driver;
import com.pdata.tools.JDBC;
import com.pdata.until.ExcelOfData;

/**   
 * @ClassName:  Jdbcs   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: muliming 
 * @date:   2018年4月25日 下午5:54:44     
 * @Copyright: 2018 
 */
public class Jdbcs {
	
	public static void main(String[] args) throws SQLException {

	}

	public static Connection testDriver() throws SQLException {
		Driver driver=new com.mysql.jdbc.Driver();
		String url="jdbc:mysql://127.0.0.1:3306/oa?useUnicode=true&amp;characterEncoding=utf-8";
		Properties info=new Properties();
		info.put("user","root");
		info.put("password", "admin");
		Connection con=driver.connect(url,info);
		return con;
	}
	
	//test 查询数据集
	public List<List<String>> resret() throws Exception{
		Connection connection = null;
	    Statement statement = null;
	    ResultSet rs = null;
	    
      //1.获取Connection
      connection = testDriver();
      //2.获取Statement
      statement = connection.createStatement();
      //3.准备Sql
      String sql = "select * from t_adm ";
      //4.执行查询，得到ResultSet
      rs = statement.executeQuery(sql);
     List<List<String>> li=new ArrayList<List<String>>();
      //5.处理ResultSet
      while(rs.next()){
    	 List<String> l=new ArrayList<String>();
        //rs.get+数据库中对应的类型+(数据库中对应的列别名)
        String id = rs.getString(1);
        String name = rs.getString(2);
        String email = rs.getString(3);
        for(int i=2;i<6;i++){
        	l.add(rs.getString(i));
        } 
	        li.add(l);
      } 
		return li;
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

}
