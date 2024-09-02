package edu.kh.jdbc.controller;

import java.io.IOException;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
		
		// 전달 받은 파라미터 얻어오기
		String userId = req.getParameter("userId");
		//스트링 객체로 req = 전달인자 getParameter userId를 가져오겠다.
		//html이 스트링 html에 있는 userId 가져옴
		String userPw = req.getParameter("userPw");
		
		// id,pw가 일치하는 회원의 정보를 조회하는
		// 서비스 호출 후 결과 반환.
		UserService service = new UserServiceImpl();
		//service라는호출하는 UserServiceImpl에다 만들것이다.
		
		
		User loginUser = service.login(userId, userPw);
		//user클래스에있는 userId,userPw를 가져와 loginUser에 담을것이다.
		
		//로그인 성공 시 (조회 결과가 있는 경우
		//session scope에 로그인된 회원의 정보를 세팅
		// + 만료시간 설정
		
		HttpSession session = req.getSession(); // 세션 객체 얻어오기.
		//Http세션을 session에 담을것이다 Session타입을
		
		
		if( loginUser != null) { // 로그인 성공했을떄 null이 아님.
			session.setAttribute("loginUser", loginUser);
			//세션 페이지가 꺼지기전까지 
			session.setMaxInactiveInterval(1800); //초단위, 3분
		}else { //로그인 실패
			session.setAttribute("message", "ID 또는 PW맞지가 않어");
		}
		
		//메인 페이지(/) 리다이렉트
		resp.sendRedirect("/");
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
