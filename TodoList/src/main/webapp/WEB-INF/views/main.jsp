<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>메인페이지가될친구</title>
  <link rel="stylesheet" href="/resources/css/main.css">
</head>

<body>
  <header>
    <h1>Todo_List</h1>
    
    <h3>전체 Todo의 갯수 : ${fn:length(todoList)} / 완료된 Todo 갯수 : ${completeCount}</h3>
    <hr>
  </header>

  <main>
    <h4>할 일 추가</h4>
    <form action="/todo/add" method="post" id="addForm">
      <div>
        제목 : <input type="text" name="title"> 
      </div>
      <div>
        <textarea name="detail" rows="3" cols="50" placeholder="상세 내용"></textarea>
      </div>
      <button>추가</button>
    </form>

    <hr>
    <%-- 할 일 목록 출력 --%>
    <table id="todoList" border="1">
      <thead>
        <tr>
          <th>번호</th>
          <th>할 일 제목</th>
          <th id="completeOrder">완료 여부</th>
          <th>등록 날짜</th>
        </tr>
      </thead>

      <tbody>
        <c:forEach items="${todoList}" var="todo" varStatus="vs">
          <tr>
            <th>${vs.count}</th>

            <th>
              <%-- 제목 클릭 시 인덱스 번호를 이용하여 
                  todoList의 인덱스번째 요소 내용을 조회하기
                  (쿼리스트링 이용 : 주소?K=V&K=V&K=V&...)
              --%>
              <a href="/todo/detail?listNo=${todo.listNo}" style="color:${todo.color}">${todo.title}</a>
            </th>

            <%-- 완료여부 --%>
            <th>
              <c:if test="${todo.complete}" > O </c:if>
              <c:if test="${not todo.complete}" > X </c:if>
            </th>

            <td>${todo.regDate}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </main>


  <%-- 정렬 저장용 숨은값 --%>
  <input type="hidden" name="order" value="${order}">
  <input type="hidden" name="completeCount" value="${completeCount}">

  <%-- session 범위에 message가 있을경우 --%>
  <c:if test="${not empty sessionScope.message}" >
    <script>
      alert(" ${message} ");
      // JSP 해석 우선순위
      // -> Java(EL/JSTL) > Front(html,css,js)
    </script>

    <%-- message를 한번 출력 후 제거 --%>
    <c:remove var="message" scope="session" />

  </c:if>

<script src="/resources/js/main.js"></script>
</body>

</html>