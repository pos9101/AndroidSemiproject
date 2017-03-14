<%@page import="test.com.SeatDAOimpl"%>
<%@page import="test.com.SeatDAO"%>
<%@page import="test.com.SeatVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String seat = request.getParameter("seat");
	String seat_ciNm = request.getParameter("seat_ciNm");
	int ciNm = Integer.parseInt(request.getParameter("ciNm"));
	String cinema = null;
	String cinemaNumber = null;
	System.out.println("search.jsp "+seat);
	System.out.println("search.jsp "+ciNm);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>
	<%if((ciNm%2)==1){cinema="오전";}else if((ciNm%2)==0){cinema="오후";}
	  if(ciNm<13){cinemaNumber="1관";}else if(ciNm<23&&ciNm>13){cinemaNumber="2관";}else if(ciNm<33&&ciNm>23){cinemaNumber="3관";}%>
		회원님은 <%=cinemaNumber%> <%=cinema %> <strong><mark><%=seat%></mark></strong>를 선택하셨습니다.

		<form action="index.do">
			<button type="submit" class="btn btn-info">확인</button>
		</form>
		<form action="cinema<%=ciNm%>deleteOK.do">
			<button type="submit" class="btn btn-info" name="deleteBtn" value=<%=seat_ciNm%>>취소</button>
		</form>
		<a>seat_ciNm : <%=seat_ciNm %></a>
	</h3>
</body>
</html>