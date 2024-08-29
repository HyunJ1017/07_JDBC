<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- 
 1. "/" 메인페이지 요청시
 2. -> index.jap가 매핑되는데
 3. -> 바로 /main Servlet으로 요청 위임
 
 - Servlet으로 거쳐가므로 jsp 파일에 java에서 가져온 자료를 사용할 수 있음
 --%>
<jsp:forward page="/main"/>