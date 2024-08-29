package edu.kh.jdbc.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet{

	
	
	// 사용자 등록 페이지로 전환.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/signUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		// 요청 위임 포워드 구문.
	}
	
	//사용자 등록 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		
		try {
			// 전달 받은 파라미터를 한번에 저장할 User DTO 객체 생성
			String userId = req.getParameter("userId");
			String userPw = req.getParameter("userPw");
			String userName = req.getParameter("userName");
			
			User user = new User();
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setUserName(userName);
			//객체 담기
			
			//서비스(INSERT) 호출 후 
			// 결과(삽입된 행의 개수, int) 반환 받기.
			UserService service = new UserServiceImpl();
			// 다형성 업 캐스팅을 사용한다
			int result = service.insertUser(user);
			
			//결과에 따라 응답 방법 처리
			String message = null;
			
			if(result > 0) message = userId + "사용자 등록 성공";
			else		   message = "등록 실패";
			
			//page, request, sessoin, application
			//session 을 이요애서 message 값 전달
			
			HttpSession session = req.getSession();
			
			session.setAttribute("message", message);
			
			//메인 페이지로 리다이렉트 (재 요 청)
			resp.sendRedirect("/");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
