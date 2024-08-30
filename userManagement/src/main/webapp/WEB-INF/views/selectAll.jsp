<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>UMP</title>
  <link rel="stylesheet" href="/resources/css/main.css">
  <link rel="stylesheet" href="/resources/css/selectAll.css">
</head>
<body>
  <header>
    <h1>사용자 목록 조회</h1>
    <h3>${check}</h3>
    <hr>
  </header>

  <main>

    <c:if test="${not empty param.searchId}" >
    <h3>"${param.searchId}" 검색결과</h3>
    </c:if>

    <form action="/search">
      ID 검색 : <input type="text" name="searchId" placeholer="포함되는 아이디 검색" value="${param.searchId}">
      <button>검색</button>
    </form>

    <hr>

    <table id="userList" border="1">
      <thead>
        <tr>
          <th>NO</th>
          <th>아이디</th>
          <%-- <th>비밀번호</th> --%>
          <th>이름</th>
          <%-- <th>등록일</th> --%>
        </tr>
      </thead>
  
      <tbody>
        <%-- 조회결과가 없을경우 --%>
        <c:if test="${empty userList}" >
          <tr>
          <th colspan="3">조회 결과가 없습니다</th>
          </tr>
        </c:if>

        <c:if test="${not empty userList}" >
          <c:forEach items="${userList}" var="user" varStatus="vs">
            <tr>
              <td>${user.userNo}</td>
              <td><a href="/selectUser?userNo=${user.userNo}" >${user.userId}</a></td>
              <%-- <td>${user.userPw}</td> --%>
              <td>${user.userName}</td>
              <%-- <td>${user.enrollDate}</td> --%>
            </tr>
          </c:forEach>
        </c:if>
      </tbody>
    </table>


  <form action="/"><button>돌아가기</button></form>



  </main>


  <section class="colDiv">
    <a href="/"><div class="aDiv">1</div></a>
    <a href="/signUp"><div class="aDiv">1</div></a>
    <a href="/selectAll"><div class="aDiv">1</div></a>
  </section>

  <c:if test="${not empty sessionScope.message}" >
    <script>alert("${message}");</script>
    <c:remove var="message" scope="session" />
  </c:if>
</body>
</html>