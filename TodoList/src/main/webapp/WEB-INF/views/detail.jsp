<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${todo.title} 상세 조회</title>
  <link rel="stylesheet" href="/resources/css/detail.css">
</head>
<body>
  
  <main>


  
    <%-- 할일 제목 넣기 --%>
    <h1 style="color:${todo.color}">${todo.title}</h1>

    <%-- 완료여부 --%>
    <div class="complete">
      완료여부 : 
      <c:if test="${todo.complete}">
        <span class="green">O</span>
      </c:if>

      <c:if test="${not todo.complete}">
        <span class="red">X</span>
      </c:if>
    </div>

    <div>
      작성일 : ${todo.regDate}
    </div>

    <%-- 상세내용 --%>
    <div class="content">${todo.detail}</div>

    <div class="btn-container">
      <div>
        <button id="goToList">목록으로</button>
      </div>

      <div>
        <button id="completeBtn">완료 여부 변경</button>
        <button id="updateBtn">수정</button>
        <button id="deleteBtn">삭제</button>
      </div>
    </div>



  </main>

<input type="hidden" id=complete value="${todo.complete}">
<input type="hidden" id=title value="${todo.title}">

<%-- 세션에 message가 있다면 --%>
  <%--  해석순서 ${} > alert("") --%>
<c:if test="${not empty sessionScope.message}" >
  <script>
    alert("${message}");
  </script>
  <c:remove var="message" scope="session" />
</c:if>

<script src="/resources/js/detail.js"></script>
</body>
</html>