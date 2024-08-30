<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>사용자 목록 조회</title>
</head>
 <%-- 
       - 클릭 시 /selectAll GET방식 요청 
       - 모든 사용자 회원번호, id, pw, name, enrollDate 조회
       - 조회 결과를 request scope에 세팅하여
         /WEB-INF/views/selectAll.jsp로 forward
      
       - <table> 태그를 이용해서 모든 정보 출력
        컬럼명 : 회원번호 | 아이디 | 비밀번호 | 이름 | 등록일
        hint. JSTL 중 <c:forEach> 사용
      --%>


  
  <h1>사용자 목록 조회</h1>
  <%-- 검색 결과만 있을 경우 empty 가 ture인 경우 : 빈칸--%>
  <c:if test="${not empty param.searchId}">

    <h3>"${param.searchId}" 검색 결과</h3>

  </c:if>

 <form action="/search">
    ID 검색 : <input 
                type="text" 
                name="searchId" 
                placeholder="포함되는 아이디 검색"
                value="${param.searchId}"
                > 

    <button>검색</button>
  </form>
  

  <table border= "1">
    <thead>
    <tr>
      <th>번호</th>
      <th>아이디</th>
      <%-- <th>비밀번호</th> --%>
      <th>이름</th>
      <%-- <th>등록일</th> --%>
    </tr>
    
    </thead>
    <tbody>
<%-- 조회결과가 없을경우 --%>
<c:if test="${empty userList}">
  <tr>
    <th colspan="5">조회 결과가</th>
  </tr>

</c:if>

    <%-- 조회 결과가 있을 경우 --%>
<c:if test="${not empty userList}">
        <c:forEach items="${userList}" var="user">
          <tr>
           <th>${user.userNo}</th>

           <th>
            <a href="/selectUser?userNo=${user.userNo}"> ${user.userId}</a>
           </th>
           <%-- <th>${user.userPw}</th> --%>
           <th>${user.userName}</th>
           <%-- <th>${user.enrollDate}</th> --%>
          
          </tr>
        </c:forEach>
</c:if>
    
    </tbody>

  </table>

  <%-- session에 message가 존재하는 경우 --%>
  <c:if test="${!empty sessionScope.message}">
    <script>
      alert("${sessionScope.message}");
    </script>

    <%-- session에 존재하는 message 제거 --%>
    <c:remove var="message" scope="session" />
  </c:if>



</body>
</html>