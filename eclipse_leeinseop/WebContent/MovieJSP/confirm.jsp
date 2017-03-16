<%@page import="test.com.SeatDAOimpl"%>
<%@page import="test.com.SeatDAO"%>
<%@page import="test.com.SeatVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script type="text/javascript" src="./MovieJSP/jquery-3.1.1.js"></script>
	<%
		String id=null;
		SeatVO vo = new SeatVO();
	   vo.setId(id);
	   SeatDAO dao = new SeatDAOimpl();
	   SeatVO svo = dao.search(vo);
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="boardInsertOK.do" method="post" name="writeform"
		onsubmit="return writeCheck()">
	아이디를 넣으시면 예매내역을 확인할 수 있습니다.

	<div class="row">
		<input type="text" class="form-control" placeholder="아이디을 작성하세요"
			name="title" /><br />
	</div>
	<button type="submit" class="btn btn-danger">확인</button>
	</form>
</body>
</html>