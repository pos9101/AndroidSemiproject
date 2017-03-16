<%@page import="java.util.List"%>
<%@page import="test.com.SeatDAOimpl"%>
<%@page import="test.com.SeatDAO"%>
<%@page import="test.com.SeatVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// 	String id = request.getParameter("id");
	String id = session.getAttribute("id").toString();
	System.out.println("seatsearch id>>>>" + id);

	SeatVO vo = new SeatVO();
	//     vo.setId(id);
	SeatDAO dao = new SeatDAOimpl();
	List<SeatVO> list = dao.select();

	request.setAttribute("list", list);
	System.out.println("search>>>" + list);

	for (int i = 0; i < list.size(); i++) {
		vo = list.get(i);
		System.out.println(vo.getSeat() + "," + vo.getCiNm());
	}

	String cinema = null;
	String cinemaNumber = null;
	String cinemaName = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">
  <div class="row">
  
  
<!-- 	<form action="index.do"><button type="submit" class="btn btn-info">홈페이지</button></form> -->
	
	<div class="container">
	<p class="text-center"><h3><%=id%>님 귀하의 예매내역입니다.</h3></p>
	</div>
	
	
	<br />
	
	
	<table border="1">

		<%
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				if ((vo.getCiNm() % 2) == 1) {
					cinema = "오전";
				} else if ((vo.getCiNm() % 2) == 0) {
					cinema = "오후";
				}
				if (vo.getCiNm() < 13) {
					cinemaNumber = "1관";
					cinemaName = "콩: 스컬 아일랜드";
				} else if (vo.getCiNm() < 23 && vo.getCiNm() > 13) {
					cinemaNumber = "2관";
					cinemaName = "로건";
				} else if (vo.getCiNm() < 33 && vo.getCiNm() > 23) {
					cinemaNumber = "3관";
					cinemaName = "해빙";
				}
		%>
		<tr>
			<td>영화: <%=cinemaName%><br/> 상영관: <%=cinemaNumber%> // 상영시간: <%=cinema%> // 좌석: <%=vo.getSeat()%></td>
		</tr>
		<%
			}
		%>
	</table>
	


</div>
</div>
</body>
</html>