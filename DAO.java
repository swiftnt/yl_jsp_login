package com.yl;

import java.sql.*;

public class DAO {
	private String className = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/yl";
	private String user = "root";
	private String paswd = "11111";
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public Connection getConn() {
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, paswd);			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public Statement getStatement() {
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, paswd);
			stmt = conn.createStatement();
			return stmt;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//查询
	public ResultSet getRs(String sql) {
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, paswd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public int updateMyDB(String sql) {
		int i = 0;
		conn = getConn();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			i = stmt.executeUpdate(sql);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//登录
	public boolean login(String username, String password) throws SQLException {
		boolean isSuccessful = false;
		DAO d = new DAO();
		String sql = "select * from _User where username = '"+username+"' and password = '"+password+"'";
		stmt = d.getStatement();
		rs = stmt.executeQuery(sql);
		try {
			if (rs.next()){
				isSuccessful = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isSuccessful;
	}
	
	public String getId(String username, String password) throws SQLException{
		String id = "";
		DAO d = new DAO();
		String sql = "select * from _User where username = '"+username+"' and password = '"+password+"'";
		stmt = d.getStatement();
		rs = stmt.executeQuery(sql);
		if (rs.next()){
			id = String.valueOf(rs.getInt(1));
		}
		
		return id;
	}
	
	//判断是否已经注册	
	public boolean isExist(String username) throws SQLException {
		DAO d = new DAO();
		String sql = "select * from _User where username = '"+username+"'";
		stmt = d.getStatement();
		rs = stmt.executeQuery(sql);
		return rs.next();		
	}
	
	//注册
	public boolean register(String username, String password, String email) throws SQLException{
		boolean isSuccessful = false;
		DAO d = new DAO();
		stmt = d.getStatement();
		String sql = "insert into _User (username,password,email) values ('"+username+"','"+password+"','"+email+"')";
		int i = stmt.executeUpdate(sql);
		if (i > 0) {
			isSuccessful = true;
		}
		return isSuccessful;
	}
	
	public ResultSet getUserRs(int id) throws SQLException {
		DAO d = new DAO();
		String sql = "select * from _User where id = " + id +"";
		stmt = d.getStatement();
		rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public static void main(String[] args) throws SQLException {
		DAO d = new DAO();
		ResultSet rss = d.getUserRs(1);
		while(rss.next()){
			rss.getString("username");
		}
		
	}
	
	
}
