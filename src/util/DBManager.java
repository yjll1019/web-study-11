package util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//Connection��ü�� ���, ����� ���� ���ҽ��� �����ϴ� Ŭ����.
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;


public class DBManager {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			//jdbc/myoracle�̶� ��ü�� ã�Ƽ� ds�� ����.
			DataSource ds = (DataSource) envContext.lookup("jdbc/board");
			//������ ds�� Connection��ü ����.
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//selec�� ���� �� ���ҽ� ������ ���� �޼ҵ�.
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
