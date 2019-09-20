<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>수업</title>
  <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>
  <link rel='stylesheet' href='/css/common.css'>
</head>
<body>

<jsp:include page="../header.jsp"/>

<div id='content'>
<h1>수업</h1>
<%
Lesson lesson= (Lesson) request.getAttribute("lesson");
%>
<form action='/lesson/update' method='post'>
번호 : <input type='text' name='no' value='<%=lesson.getNo()%>' readonly><br>
수업명 : <textarea name='title' rows='5' cols='50'><%=lesson.getTitle()%></textarea><br>
수업내용 :<textarea name='contents' rows='5' cols='50'><%=lesson.getContents()%></textarea><br>
시작일 : <textarea name='startDate' rows='5' cols='50'><%=lesson.getStartDate()%></textarea><br>
종료일 : <textarea name='endDate' rows='5' cols='50'><%=lesson.getEndDate()%></textarea><br>
총수업시간 : <textarea name='totalHours' rows='5' cols='50'><%=lesson.getTotalHours()%></textarea><br>
일수업시간 : <textarea name='dayHours' rows='5' cols='50'><%=lesson.getDayHours()%></textarea><br>
<button>변경</button>
<a href='/lesson/delete?no=lesson.getNo()'>삭제</a>
            
</form>
</div>

<jsp:include page="../footer.jsp"/>

</body>
</html>