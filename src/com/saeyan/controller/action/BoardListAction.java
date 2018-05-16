package com.saeyan.controller.action;

//BoardServlet?command=board_list?=board_list 라는 요청을 받으면 게시글 리스트를 화면에 나타내기 위한 모델(액션클래스)

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardListAction implements Action{
   
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
      String url="/board/boardList.jsp";
      
      BoardDAO bDao = BoardDAO.getInstance();
      
      List<BoardVO> boardList = bDao.selectAllBoards();
      
      request.setAttribute("boardList", boardList);
      
      RequestDispatcher dispatcher = request.getRequestDispatcher(url);
      dispatcher.forward(request, response);
   }
}