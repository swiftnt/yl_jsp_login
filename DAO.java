package com.yl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String _className = "";
	private String _url = "";
	
	
	
	/*根据不同的数据库类型返回不同的classForName
	 * mysqlOrSqlserver 数据库类型 "mysql" 或者 "sqlserver"
	 * 输入的字符串一定是 "mysql" 或者 "sqlserver"
	 */	
	public String getClassForName(String mysqlOrSqlserver) {
		if (mysqlOrSqlserver.equals("sqlserver")){
			_className = "sun.jdbc.odbc.JdbcOdbcDriver";
		}else if (mysqlOrSqlserver.equals("mysql")){
			_className =  "com.mysql.jdbc.Driver";
		}
		return _className;
	}
	
	
	/*	根据数据库类型获取url
	 * 	mysqlOrSqlserver  输入数据库类型， 如果数据库是mysql则输入"mysql"， 如果是sqlserver则输入"sqlserver"
	 * 	db_name 网站的数据库名
	 */	
	public String getUrl(String mysqlOrSqlserver, String db_name){
		if (mysqlOrSqlserver.equals("mysql")){
			_url = "jdbc:mysql://localhost/"+db_name;
		}else if (mysqlOrSqlserver.equals("sqlserver")){
			_url = "jdbc:odbc:"+db_name;
		}
		return _url;
	}
	
	/* 判断用户名是否存在,若返回true，代表存在，否则代表没有该用户。
	 * classForName 连接数据库 Class.ForName()时输入的字符串
	 * url 数据库建立连接时的url, 从getUrl()获取
	 * user_tb_name 用户表名
	 * db_username 数据库用户名
	 * db_password 数据库密码
	 * col_user_name 用户表中用户名所在的列名
	 * col_user_pasw 用户表中密码所在的列名
	 * username 登录时输入的用户名
	 * password 登录时输入的密码
	 */	
	public boolean isExist(String classForName, String url, String user_tb_name, String db_username, String db_password, String col_user_name, String username) throws ClassNotFoundException, SQLException{
		Class.forName(classForName);
		conn = DriverManager.getConnection(url,db_username,db_password);
		stmt = conn.createStatement();
		String sql = "select * from " + user_tb_name + " where " + col_user_name + " = " + "'" +username +"'";
		rs = stmt.executeQuery(sql);
		return rs.next();
	}
	
	/* 登录 返回true表示登录成功
	 * classForName 连接数据库 Class.ForName()时输入的字符串
	 * url 建立连接时输入的字符串
	 * user_tb_name 用户表名
	 * db_username 数据库用户名
	 * db_password 数据库密码
	 * col_user_name 用户表中用户名所在的列名
	 * col_user_pasw 用户表中密码所在的列名
	 * username 登录时输入的用户名
	 * password 登录时输入的密码
	 */
	public boolean isLoginSuccessful(String classForName, String url, String user_tb_name, String db_username, String db_password, String col_user_name, String col_user_pasw, String username, String password) throws ClassNotFoundException, SQLException{
		Class.forName(classForName);
		conn = DriverManager.getConnection(url,db_username,db_password);
		stmt = conn.createStatement();
		String sql = "select * from " + user_tb_name + " where " + col_user_name + " = " + "'" +username +"'" + " and " + col_user_pasw + " = " + "'" + password + "'";
		rs = stmt.executeQuery(sql);
		return rs.next();
	}
	
	
	/* 注册 返回值大于1代表注册成功
	 * classForName 连接数据库 Class.ForName()时输入的字符串
	 * url 建立连接时输入的字符串
	 * user_tb_name 用户表名
	 * db_username 数据库用户名
	 * db_password 数据库密码
	 * col_user_name 用户表中用户名所在的列名
	 * col_user_pasw 用户表中密码所在的列名
	 * username 登录时输入的用户名
	 * password 登录时输入的密码
	 */
	public int Register(String classForName, String url, String user_tb_name, String db_username, String db_password, String col_user_name, String col_user_pasw, String username, String password) throws ClassNotFoundException, SQLException{		
		Class.forName(classForName);
		conn = DriverManager.getConnection(url,db_username,db_password);
		stmt = conn.createStatement();
		String sql = "insert into "+user_tb_name+"("+col_user_name+","+col_user_pasw+") values ("+ "'" + username + "'" + "," + "'" + password + "'" + ")";
		System.out.println(sql);
		return stmt.executeUpdate(sql);
		
	}
	
}

