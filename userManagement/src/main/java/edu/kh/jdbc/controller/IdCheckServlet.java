package edu.kh.jdbc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signUp/idCheck")
public class IdCheckServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 비동기로 /signUp/idCheck GET 방식 요청시
		// 응답으로 html 문서코드가 아닌
		// 특정 값만 반환하는 코드 작성
		
		// 전달받은 파라미터 얻어오기
		String userId = req.getParameter("userId");
		
		// 테스트용 코드
		int result = 0;
		if(userId.equals("user01")) result =1;
		
		// HTML이 아니라 "값"을 반환하기 위한 응답세팅
//		resp.setContentType("text/html; charset=UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		// - application/json : JS에서 사용 가능한 값(쉽게말해서)
		
		// 클러이언트와 연결된 출력용 스트림 얻어오기
		PrintWriter out = resp.getWriter();
		out.print(result);
		
		
		
		
		
		
		
		
	}

}
