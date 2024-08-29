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

  asd
  <h1>${selectAll.user}</h1>


<body>






</body>
</html>