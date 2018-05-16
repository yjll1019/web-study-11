package com.saeyan.controller.action;
//게시글 수정 화면으로 이동하게 하는 액션 클래스.
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateFormAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		String url="/board/boardUpdate.jsp";
		
		String num = request.getParameter("num");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.updateReadCount(num); //조회 수 증가
		
		BoardVO board = dao.selectBoardByNum(num);  //수정하고자 하는 게시글의 번호를 매개변수로 전달하여 그 게시글의 객체를 리턴.
		
		request.setAttribute("board", board);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
