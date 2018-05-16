package com.saeyan.controller.action;

//�ۼ��� ���� BoardVO��ü�� �ְ�  BoardDAO insertBoard �޼ҵ带 ���ؼ� DB�� �����ϴ� Ŭ����(��).

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
		
		new BoardListAction().execute(request, response); //�Խñ� ����Ʈ �����ֵ���.
	}
	
}
