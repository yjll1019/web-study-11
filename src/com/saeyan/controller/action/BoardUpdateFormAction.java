package com.saeyan.controller.action;
//�Խñ� ���� ȭ������ �̵��ϰ� �ϴ� �׼� Ŭ����.
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
		
		dao.updateReadCount(num); //��ȸ �� ����
		
		BoardVO board = dao.selectBoardByNum(num);  //�����ϰ��� �ϴ� �Խñ��� ��ȣ�� �Ű������� �����Ͽ� �� �Խñ��� ��ü�� ����.
		
		request.setAttribute("board", board);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
