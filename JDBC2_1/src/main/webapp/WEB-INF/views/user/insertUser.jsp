<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>html문서 제목</title>
</head>
<body>
  <h1>user insert 페이지</h1>

  <form action="/user/insertUser/insertServlet" method="post">
    id : <input type="text" name="inputId" value="${userId}"><br>
    pw : <input type="text" name="inputPw" value="${userPw}"><br>
    name : <input type="text" name="inputName" value="${userName}"><br>
    <button>입력</button>
  </form>

<form action="/"><button>돌아가기</button></form>


<c:if test="${not empty sessionScope.message}" >
  <script>alert("${message}");</script>
</c:if>
<c:remove var="message" scope="session" />

</body>
</html>