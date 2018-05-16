package com.saeyan.controller;

//MVC������ Controller ������ �ϴ� ���� Ŭ����.

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.controller.action.Action;
import com.saeyan.controller.controller.ActionFactory;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = request.getParameter("command");
		System.out.println("BoardServlet���� ��û�� Ȯ�� ����. : "+command);
		
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command); //��û���� command�� getAction�޼ҵ忡 ����. action��ü ���ϵ�.
		
		if(action!=null) {
			action.execute(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); //�ѱ� ���� ���� ��
		doGet(request, response);//get�޼ҵ�� ��û ó��.
	}

}
