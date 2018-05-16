package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardVO;

import util.DBManager;

//������ ��ȸ, ����, ����, ������ ���� DAOŬ����.

public class BoardDAO {
	
	private BoardDAO() {} // private ������
	
	private static BoardDAO instance = new BoardDAO(); //�����ڸ� ���� ��ü ���� >> �̱��� ����! >> �ν��Ͻ��� ������ �� ���� �����ǵ��� Ŭ������ �����ϴ� ��.
	
	public static BoardDAO getInstance() { return instance;}
	
	//��� �Խõ��� �ҷ����� �޼ҵ� selectAllBoard();
	public List<BoardVO> selectAllBoards(){
		String sql = "select * from board order by num desc"; //�Խñ� ��ȣ �������� == �ֽ� ��� �ۺ��� ��������
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		Connection conn = null; Statement stmt = null; ResultSet rs =null; //���ҽ� ��ü ����
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVO board = new BoardVO();
				
				board.setNum(rs.getInt("num"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setPass(rs.getString("pass"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadcount(rs.getInt("readcount"));
				board.setWritedate(rs.getTimestamp("writedate"));
				list.add(board);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	//�Խñ��� �����ϴ� �޼ҵ� inserBoard
	public void insertBoard(BoardVO b) {
		String sql = "insert into board values(num, ?,?,?,?,?,default, now())";
		
		Connection conn = null; PreparedStatement  stmt = null;
	
		try{ 
			conn = DBManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, b.getPass());
			stmt.setString(2, b.getName());
			stmt.setString(3, b.getEmail());
			stmt.setString(4, b.getTitle());
			stmt.setString(5, b.getContent());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt);
		}
	}
	
	//�Խñ��� ��ȸ���� ������Ű�� �޼ҵ� updateReadCount
	public void updateReadCount(String num) {
		String sql = "update board set readcount=readcount+1 where num=?";
		
		Connection conn = null; PreparedStatement  stmt = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, num);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt);
		}
	}
	
	//�Խ��� �� �� ���� ���� : �۹�ȣ�� ã�ƿ´�. ���н� NULL
	public BoardVO selectBoardByNum(String num) {
		String sql = "select * from board where num = ?";
		
		Connection conn = null; PreparedStatement  stmt = null; ResultSet rs =null;
		BoardVO board=null;
		try {
			
			conn = DBManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, num);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setPass(rs.getString("pass"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadcount(rs.getInt("readcount"));
				board.setWritedate(rs.getTimestamp("writedate"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt, rs);
		}
		return board;
	}
	
	//�Խñ��� �����ϴ� �޼ҵ� updateBoard
	public void updateBoard(BoardVO board) {
		String sql = "update board set name=?, email=?, pass=?, title=?, content=? where num = ?";
		
		Connection conn = null; PreparedStatement  stmt = null; 
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, board.getName());
			stmt.setString(2, board.getEmail());
			stmt.setString(3, board.getPass());
			stmt.setString(4, board.getTitle());
			stmt.setString(5, board.getContent());
			stmt.setInt(6, board.getNum());
			
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt);
		}
	}
	
	//�Խñ� ��ȣ�� ��й�ȣ�� ��ġ�ϴ� �Խñ��� ã�� BoardVO ��ü�� �����ϴ� checkPassWord
	public BoardVO checkPassWord(String pass, String num) {
		String sql = "select * from board where pass=? and num=?";
		
		Connection conn = null; PreparedStatement  stmt = null; ResultSet rs =null;
		BoardVO board=null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pass);
			stmt.setString(2, num);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setPass(rs.getString("pass"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadcount(rs.getInt("readcount"));
				board.setWritedate(rs.getTimestamp("writedate"));
			}
		}catch(SQLException e) {
		}finally {
			DBManager.close(conn, stmt,rs );
		}
		return board;
	}
	
	//�Խñ� ������ ���� �޼ҵ� deleteBoard
	public void deletedBoard(String num) {
		String sql="delete from board where num=?";
		
		Connection conn = null; PreparedStatement  stmt = null; 
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, num);
			stmt.executeUpdate();
		}catch(SQLException e) {
		}finally {
			DBManager.close(conn, stmt);
		}
	}
}
