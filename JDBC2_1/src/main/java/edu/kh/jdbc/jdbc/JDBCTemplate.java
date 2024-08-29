package edu.kh.jdbc.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			
			if(conn != null && !conn.isClosed()) return conn;
			
			Properties prop = new Properties();
			
			String filePath = "/workspace/07_JDBC/JDBC2_1/driver.xml";
			prop.loadFromXML(new FileInputStream(filePath));
			// -> prop에 저장된 값(driver.xml에서 읽어온 값)
			
			Class.forName(prop.getProperty("driver"));
			String url      = prop.getProperty("url");
			String userName = prop.getProperty("userName");
			String password = prop.getProperty("password");
			
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return conn;
	}
	
	
	//===========================================================================
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())conn.commit();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())conn.rollback();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	//===========================================================================
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) rs.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
}
