package com.saeyan.controller.action;

//작성한 글을 BoardVO객체에 넣고  BoardDAO insertBoard 메소드를 통해서 DB에 저장하는 클래스(모델).

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		
		BoardVO board = new BoardVO();
		
		board.setName(request.getParameter("name"));
		board.setPass(request.getParameter("pass"));
		board.setEmail(request.getParameter("email"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insertBoard(board); 
		
		new BoardListAction().execute(request, response); //게시글 리스트 보여주도록.
	}
	
}
