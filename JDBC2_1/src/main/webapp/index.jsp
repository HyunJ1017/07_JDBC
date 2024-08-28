<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/index.css">
</head>

<body>
	<h1>User 관리 프로그램</h1>
  <main>

    <form action="/user/insertUser" method="post">
      <button class="button" id="btn1">
        User 등록<br>(ISERT)
      </button>
    </form>


    <form action="/user/allView" method="post">
      <button class="button" id="btn2">
        User 전체조회<br>(SELECT)
      </button>
    </form>

    <form action="/" method="post">
      <button class="button" id="btn3">
        User 이름검색<br>(SELECT)
      </button>
    </form>

    <form action="/" method="post">
      <button class="button" id="btn4">
        User 조회<br>(USER_NO, SELECT)
      </button>
    </form>

    <form action="/" method="post">
      <button class="button" id="btn5">
        User 삭제<br>(USER_NO, DELETE)
      </button>
    </form>

    <form action="/" method="post">
      <button class="button" id="btn6">
        User 이름수정<br>(ID, PW, UPDATE)
      </button>
    </form>

    <form action="/" method="post">
      <button class="button" id="btn7">
        User 등록<br>(ID중복검사)
      </button>
    </form>

    <form action="/" method="post">
      <button class="button" id="btn8">
        여러 User 등록하기
      </button>
    </form>







  </main>
  

  <script src="/resources/js/index.js"></script>
</body>
</html>