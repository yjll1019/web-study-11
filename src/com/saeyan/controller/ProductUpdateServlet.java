package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/productUpdate.do")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
	
		ProductDAO pDao = ProductDAO.getInstance();
		ProductVO pVO = pDao.selectProdectByCode(code);
		
		request.setAttribute("product", pVO);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/productUpdate.jsp");
		dispatcher.forward(request, response);
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //request를 통해 받아올 때 한글이 꺠지지 않도록 한다.
		
		ServletContext context = getServletContext();
		String path =  context.getRealPath("upload");
		String encType = "utf-8";
		int sizeLimit = 20 * 1024 * 1024 ;
		
		MultipartRequest multi =  new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());

		String code = multi.getParameter("code");
		String name = multi.getParameter("name");
		int price =Integer.parseInt(multi.getParameter("price"));
		String description = multi.getParameter("description");
		String pictureUrl = multi.getParameter("pictureUrl");
		if(pictureUrl==null) {
			pictureUrl = multi.getParameter("nonmakeImg");
		}
		
		ProductVO pVO = new ProductVO();
		pVO.setCode(Integer.parseInt(code));
		pVO.setName(name);
		pVO.setPrice(price);
		pVO.setDescription(description);
		pVO.setPictureUrl(pictureUrl);
		
		ProductDAO pDao = ProductDAO.getInstance();
		pDao.updateProduct(pVO);
		
		response.sendRedirect("productList.do");
}
}
