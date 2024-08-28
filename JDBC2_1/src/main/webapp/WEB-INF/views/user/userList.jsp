<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>html문서 제목</title>
</head>
<body>
  <h1>전체조회</h1>
  <h3>${check}</h3>
  <hr>

  <table id="userList" border="1">
    <thead>
      <tr>
        <th>no</th>
        <th>id</th>
        <th>pw</th>
        <th>name</th>
        <th>date</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach items="${userList}" var="user" varStatus="vs">
        <tr>
          <th>${user.userNo}</th>
          <th>${user.userId}</th>
          <th>${user.userPw}</th>
          <td>${user.userName}</td>
          <td>${user.enrollDate}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <form action="/"><button>돌아가기</button></form>
</body>
</html>