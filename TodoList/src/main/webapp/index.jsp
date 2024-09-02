<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--
 "/" 요청시 index.jsp 화면이 보여지는데
여기서 다른 서블릿이 응답할 수 있도록 요청위임을 하겠음

"/" 요청이 오면 "/main"서블릿으로 요청 위임

 --%>

 <jsp:forward page="/main"/>
