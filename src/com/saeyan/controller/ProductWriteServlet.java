package com.saeyan.controller;
// productWrite.do doGet() >> productWrite.jsp 실행
// productWrite.jsp에서  productWrite.do doPost() >> ProductDAO에서 insertProduct()실행
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
 * Servlet implementation class ProductWriteServlet
 */
@WebServlet("/productWrite.do")
public class ProductWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/productWrite.jsp");
		dispatcher.forward(request, response); //product.productWrite.jsp 로 포워딩한다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //폼에서 입력한 한글이 request를 통해 넘어올 때 깨지지 않도록!
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		String encType = "utf-8";
		int sizeLimit = 20 * 1024 * 1024;
		
		MultipartRequest multi =  new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		
		//파일 업로드를 위한 처리를 위해서는 모든 요청에 대한 처리를 MultipartRequest클래스의 getParameter로 해야한다. Request객체의 getParameter()메소드로는 파라미터 값을 얻어울 수 없음!
		String name = multi.getParameter("name"); //상품명 얻어오기.
		int price = Integer.parseInt(multi.getParameter("price"));
		String description = multi.getParameter("description");
		String pictureUrl = multi.getFilesystemName("pictureUrl");
		
		ProductVO pVO = new ProductVO();
		pVO.setName(name);
		pVO.setPrice(price);
		pVO.setDescription(description);
		pVO.setPictureUrl(pictureUrl);
		
		//아예 이 서블릿 클래스에서 db에 insert문 실행
		ProductDAO pDao = ProductDAO.getInstance();
		pDao.insertProduct(pVO);
		//insert문 실행 후 상품 리스트를 보여주도록 리다이렉트.
		response.sendRedirect("productList.do");
	}

}
