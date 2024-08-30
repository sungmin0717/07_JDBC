<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%-- 조회되는 사용자의 ID --%>
  <title>${u.userId}사용자 상세 조회</title>
  <link rel="stylesheet" href="/resources/css/selectUser">
</head>


<body>
  <%-- 조회되는 사용자의 ID --%>
  <h1>${u.userId}사용자 상세 조회</h1>

  <hr>


<%-- 사용자 정보 수정 --%>
<form action="/updateUser" method="POST">
    <table border="1" name="main">
      <tr>
        <th>사용자 번호</th>
        <th id="userNoTd">${u.userNo}</th>
      </tr>
    
      <tr>
        <th>아이디</th>
        <th>${u.userId}</th>
      </tr>
  
      <tr>
        <th>비밀번호</th>
        <th>
          <input type="text" name = "userPw" value="${u.userPw}">
        </th>
      </tr>
  
      <tr>
        <th>이름</th>
        <th>
          <input type="text" name="userName" value="${u.userName}">
        </th>
      </tr>
  
      <tr>
        <th>등록일</th>
        <th>${u.enrollDate}</th>
      </tr>
  
  
      </table>
    <div>
        <button type="submit"  id="updateBtn">수정</button>
        <button type="button" id="deleteBtn">삭제</button>
        <button type="button" id="goToList">목록으로 돌아가기</button>
    
    </div>
    <%-- 수정 시 누구의 비밀번호 이름을 수정할지 지정하기 위해 
        userNo을 form에 숨겨놓기
    --%>

  <input type="hidden" name="userNo" value="${u.userNo}">
 </form>
 
  <%-- session에 message가 존재하는 경우 --%>
  <c:if test="${!empty sessionScope.message}">
    <script>
      alert("${sessionScope.message}");
    </script>

    <%-- session에 존재하는 message 제거 --%>
    <c:remove var="message" scope="session" />
  </c:if>


  <script src="/resources/js/selectUser.js"></script>
</body>


</html>