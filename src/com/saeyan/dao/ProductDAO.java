package com.saeyan.dao;
//db 수정, 삽입 , 삭제를 위한 클래스

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.ProductVO;

import uti.DBManager;

public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();
	
	//싱글톤 패턴 : 객체를 하나만 생성해서 사용하는 방법 >> 메모리 낭비를 막는다!
	public static ProductDAO getInstance() {
		return instance;
	}
	
	//최근 등록한 상품부터 보여주는 메소드 - select 
	public List<ProductVO> selectAllProducts(){
		String sql = "select * from product order by code desc"; //최근 등록한 상품부터 출력.
		List<ProductVO> list = new ArrayList<ProductVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConncetion();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				pVO.setCode(rs.getInt("code"));
				pVO.setName(rs.getString("name"));
				pVO.setPrice(rs.getInt("price"));
				pVO.setPictureUrl(rs.getString("pictureurl"));
				pVO.setDescription(rs.getString("description"));
				list.add(pVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//상품 등록을 위한 메소드 - insert
	public void insertProduct(ProductVO pVO) {

		String sql="insert into product values (code, ?,?,?,?)";
					
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = DBManager.getConncetion(); //연결은 되었음.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVO.getName());
			pstmt.setInt(2, pVO.getPrice());
			pstmt.setString(3, pVO.getPictureUrl());
			pstmt.setString(4, pVO.getDescription());
			//sql 문장을 실행하기 전에 바인딩변수(?)에 값을 채운다.
			pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//상품 클릭시 그 상품의 정보를 가져옥 위한 select
	public ProductVO selectProdectByCode(String code) {
		String sql = "select * from product where code=?";
		ProductVO pVO = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

		try {
			conn = DBManager.getConncetion();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pVO = new ProductVO();
				pVO.setCode(rs.getInt("code"));
				pVO.setName(rs.getString("name"));
				pVO.setPrice(rs.getInt("price"));
				pVO.setPictureUrl(rs.getString("pictureurl"));
				pVO.setDescription(rs.getString("description"));
			}
		}	
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return pVO;
	}
	
	public void updateProduct(ProductVO pVO) {
		String sql="update product set name=?, price=?, pictureurl=?, description=? where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConncetion();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVO.getName());
			pstmt.setInt(2, pVO.getPrice());
			pstmt.setString(3, pVO.getPictureUrl());
			pstmt.setString(4, pVO.getDescription());
			pstmt.setInt(5, pVO.getCode());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//상품 삭제를 위한 delete
	public void deleteProduct(String code) {
		String sql = "delete product where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConncetion();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(conn, pstmt);
		}
	}
}
