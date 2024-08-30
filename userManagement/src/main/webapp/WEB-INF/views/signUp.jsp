<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>사용자 등록</title>
  <link rel="stylesheet" href="/resources/css/main.css">
  <link rel="stylesheet" href="/resources/css/signUp.css">
</head>
<body>

  <header>

    <h1>사용자 등록</h1>

  </header>


  <main>

    <form action="/signUp" method="post" id="signUpForm">
     <div>
        ID : <input type="text" name="userId" id="userId">

        <%-- 아이디 중복 여부 확인해서 메시지 출력 --%>
        <span id="check" class="green">사용 가능한 아이디 입니다.</span>
      </div>
      <div>
        PW : <input type="password" name="userPw" id="userPw">
      </div>
      <div>
        Name : <input type="text" name="userName" id="userName">
      </div>
      <div class="rowDiv">
        <button id="insertBtn" disabled>등록</button><div class="hrefDiv"><a href="/">돌아가기</a></div>
      </div>
    </form>

  </main>


  <footer>



  </footer>

  <section class="colDiv">
    <a href="/"><div class="aDiv">1</div></a>
    <a href="/signUp"><div class="aDiv">1</div></a>
    <a href="/selectAll"><div class="aDiv">1</div></a>
  </section>

<script src="/resources/js/signUp.js"></script>
</body>
</html>