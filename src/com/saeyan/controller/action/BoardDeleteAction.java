package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardDeleteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		
		BoardVO board = new BoardVO();

		String num = request.getParameter("num");
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.deletedBoard(num); 
		
		new BoardListAction().execute(request, response); //게시글 리스트 보여주도록.
	}
}
