package com.saeyan.controller.action;
//게시글의 비밀번호를 확인하기 위한 액션 클래스.
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardCheckPassAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		String url=null;
		
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.selectBoardByNum(num);  //게시글 번호에 해당하는 게시글 객체 리턴.
		
		if(board.getPass().equals(pass)) {//성공시
			url="/board/checkSuccess.jsp"; 
		}else {
			url="/board/boardCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
