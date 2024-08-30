<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%-- 조회되는 사용자 ID --%>
  <title>${user.userId} 사용자 상세 조회</title>
  <link rel="stylesheet" href="/resources/css/main.css">
  <link rel="stylesheet" href="/resources/css/selectUser.css">
</head>

<body>

  <header>
    <%-- 조회되는 사용자 ID --%>
    <h1>${user.userId} 사용자 상세 조회</h1>

    <hr>
  </header>


  <main>
    <%-- 사용자 정보 수정 --%>
    <form action="/updateUser" method="post" >

      <table border="1">
  
        <tr>
          <th>사용자 번호</th>
          <td id="userNoTd">${user.userNo}</td>
        </tr>
      
      
        <tr>
          <th>아이디</th>
          <td>${user.userId}</td>
        </tr>
  
        <tr>
          <th>비밀번호</th>
          <td>
            <input type="text" name="userPw" value="${user.userPw}">
          </td>
        </tr>
  
        <tr>
          <th>이름</th>
          <td>
            <input type="text" name="userName" value="${user.userName}">
          </td>
        </tr>
  
        <tr>
          <th>등록일</th>
          <td>${user.enrollDate}</td>
        </tr>
      
      </table>
  
      <div class="rowDiv">
        <button type="submit" id="updateBtn">수정</button>
        <button type="button" id="deleteBtn">삭제</button>
        <button type="button" id="goToLsit">목록으로 돌아가기</button>
      </div>

      <%-- Suvlet 요청시 보낼 USER_NO을 숨겨둠 --%>
      <input type="hidden" name="userNo" value="${user.userNo}">
    </form>
  
  </main>

  <footer>
  </footer>





  <section class="colDiv">
    <a href="/"><div class="aDiv">1</div></a>
    <a href="/signUp"><div class="aDiv">1</div></a>
    <a href="/selectAll"><div class="aDiv">1</div></a>
  </section>

  <c:if test="${!empty sessionScope.message}" >
    <script>alert("${message}");</script>
    <c:remove var="message" scope="session" />
  </c:if>

  <script src="/resources/js/selectUser.js"></script>
</body>
</html>