package com.saeyan.controller.action;
//�Խñ� �� ���� �������� �̵��ϴ� �׼� Ŭ����.
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
		
		dao.updateReadCount(num); //��ȸ�� ����.
		
		BoardVO board = dao.selectBoardByNum(num); //�Խñ� ��ȣ�� �Ķ���ͷ� �Ѱܼ�  �Խñ� ��������.
		
		request.setAttribute("board", board); //�Խñ� ��ü �ѱ��
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
