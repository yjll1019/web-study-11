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

//데이터 조회, 수정, 삭제, 삽입을 위한 DAO클래스.

public class BoardDAO {
	
	private BoardDAO() {} // private 생성자
	
	private static BoardDAO instance = new BoardDAO(); //생성자를 통한 객체 생성 >> 싱글톤 패턴! >> 인스턴스가 오로지 한 개만 생성되도록 클래스를 구현하는 것.
	
	public static BoardDAO getInstance() { return instance;}
	
	//모든 게시들을 불러오는 메소드 selectAllBoard();
	public List<BoardVO> selectAllBoards(){
		String sql = "select * from board order by num desc"; //게시글 번호 내림차순 == 최신 등록 글부터 나오도록
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		Connection conn = null; Statement stmt = null; ResultSet rs =null; //리소스 객체 생성
		
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
	
	//게시글을 삽입하는 메소드 inserBoard
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
	
	//게시글의 조회수를 증가시키는 메소드 updateReadCount
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
	
	//게시판 글 상세 내용 보기 : 글번호로 찾아온다. 실패시 NULL
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
	
	//게시글을 수정하는 메소드 updateBoard
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
	
	//게시글 번호와 비밀번호가 일치하는 게시글을 찾아 BoardVO 객체로 리턴하는 checkPassWord
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
	
	//게시글 삭제를 위한 메소드 deleteBoard
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
