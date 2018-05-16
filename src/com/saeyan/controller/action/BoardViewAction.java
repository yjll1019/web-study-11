package com.saeyan.controller.action;
//게시글 상세 보기 페이지로 이동하는 액션 클래스.
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardViewAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		String url="/board/boardView.jsp";
		
		String num = request.getParameter("num");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.updateReadCount(num); //조회수 높임.
		
		BoardVO board = dao.selectBoardByNum(num); //게시글 번호를 파라미터로 넘겨서  게시글 가져오기.
		
		request.setAttribute("board", board); //게시글 객체 넘기기
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
