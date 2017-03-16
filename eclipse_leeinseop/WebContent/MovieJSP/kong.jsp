<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%
	String id = request.getParameter("id");
	session.setAttribute("id", id);
	System.out.println("받음>>>>"+id);
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hgroup>
	<h1>영화관, 영화시간선택</h1>
	</hgroup>
	<img src="./img/kong.png" class="img-rounded" width="200px"
		height="200px">
	<form action="cinema11select.do">
		<input type="hidden" name="id" value ="<%=id%>"/>
		<button type="submit" class="btn btn-info">1관 오전</button>
	</form>
	<form action="cinema12select.do">
		<input type="hidden" name="id" value ="<%=id%>"/>
		<button type="submit" class="btn btn-info">1관 오후</button>
	</form>
	<a>아이디: <%=id%></a>

</body>
</html>