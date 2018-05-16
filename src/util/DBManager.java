package util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//Connection객체를 얻고, 사용이 끝난 리소스를 해제하는 클래스.
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;


public class DBManager {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			//jdbc/myoracle이란 객체를 찾아서 ds가 받음.
			DataSource ds = (DataSource) envContext.lookup("jdbc/board");
			//생성된 ds로 Connection객체 받음.
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//selec문 실행 후 리소스 해제를 위한 메소드.
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement stmt) {
		try {
			conn.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
