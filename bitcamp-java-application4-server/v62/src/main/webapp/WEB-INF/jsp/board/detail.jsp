<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>게시물 보기</title>
  <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>
  <link rel='stylesheet' href='/css/common.css'>
</head>
<body>

<jsp:include page="../header.jsp"/>
    
<div id='content'>
<h1>게시물</h1>
<form action='update' method='post'>
번호 : <input type='text' name='no' value='${board.no}' readonly><br>
내용 : <textarea name='contents' rows='5'
            cols='50'>${board.contents}</textarea><br>
등록일: ${board.createdDate}<br>
조회수: ${board.viewCount}<br>

<c:if test="${board.memberNo == memberNo}" >
  <button onclick="location='detailedit?no=${board.boardNo}'">수정</button>
  <button onclick="location='delete?no=${board.boardNo}'">삭제</button>
</c:if>

</div>

<jsp:include page="../footer.jsp"/>

</body>
</html>
