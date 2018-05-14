package com.saeyan.controller;

//파일 업로드를 위한 서블릿 클래스
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest; //파일 업로드를 직접 담당하는 클래스
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy; //파일 이름이 중복되면 알아서 바꿔주는데 그 때 사용하는 

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//여기를 바꿔주면 다운받는 경로가 바뀐다.
		String savePath = "upload";
		//최대 업로드 파일 크기 5MB로 제한
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "utf-8";
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉토리");
		System.out.println(uploadFilePath);
		
		try {
			MultipartRequest multi = new MultipartRequest(
			request, uploadFilePath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy()		
			//객체, 서버상의 실제 디렉토리, 최대 업로드 파일 크기, 인코딩 방법, 동일한 이름이 존재하면 새로운 이름이 부여됨.
			);
			String fileName = multi.getFilesystemName("uploadFile"); //업로드한 파일의 이름 얻기
			
			if(fileName==null) {
				System.out.println("파일이 업로드 되지 않음.");
			}else {
				out.println("<br> 글쓴이 :"+ multi.getParameter("name"));
				out.println("<br> 제 &nbsp; 목 :"+ multi.getParameter("title"));
				out.println("<br> 파일명 :"+ fileName);
			}
		}catch(Exception e) {
			System.out.println("예외 발생: "+e);
		}

	}

}
