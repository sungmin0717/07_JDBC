<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>사용자 등록</title>
  <link rel="stylesheet" href="/resources/css/signUp.css">
  <%-- Css 파일 연결 (webapp 폴더를 기준으로 경로 작성) --%>
</head>


<body>
  <h1>사용자 등록</h1>

  <form action="/signUp" method="POST" id="signUpForm">
  <div>
    ID : <input type="text" name = "userId" id="userId">
    
      <%-- css 파일 연결 (webapp 폴더를 기준으로 경로 작성) --%>
    <span id="check"></span>
  </div>

  <div>
    PW : <input type="password" name = "userPw">
  </div>

  <div>
    NAME : <input type="text" name = "userName">
  </div>

  <div>
    <button> -등록해주쎼요- </button>
  </div>

  <div>
    <button name = "back"> 뒤로 돌아가기.</button>
  </div>

</form>



<script src = "/resources/js/signUp.js"></script>
</body>


</html>