package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//컨트롤러에서 요청이 들어오면 한 가지 방식으로 비지니스 로직이 수행되도록 하기 위한 추상 메소드.

public interface Action  {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
