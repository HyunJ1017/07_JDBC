<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User_관리_프로젝트</title>
  <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>

  <header>
  <h1>User Management Page</h1>
  </header>


  <main>

    <h1>Login</h1>

    <form action="/login" method="post">
      <div>
        ID : <input type="text" name="userId">
      </div>
      <div>
        PW : <input type="password" name="userPw">
      </div>
      <div class="rowDiv">
        <button>로그인</button>
        <div class="hrefDiv"><a href="/signUp">사용자등록</a></div>
      </div>
    </form>




  </main>


  <footer>


  </footer>


<%-- session에 message가 존재하는 경우 --%>
<c:if test="${!empty sessionScope.message}" >
  <script>
    alert("${sessionScope.message}");
  </script>
  <c:remove var="message" scope="session" />
</c:if>

</body>
</html>