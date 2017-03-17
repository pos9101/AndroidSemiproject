package test.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import test.com.SeatVO;




/**
 * Servlet implementation class moviecontroller
 */
@WebServlet({"/index.do", "/cinema11select.do", "/cinema12select.do", "/cinema21select.do", "/cinema22select.do", "/cinema31select.do", "/cinema32select.do",
	"/cinema11insertOK.do", "/cinema12insertOK.do", "/cinema21insertOK.do", "/cinema22insertOK.do", "/cinema31insertOK.do", "/cinema32insertOK.do",
	 "/button.do", "/cinema1search.do", "/confirm.do", "/seatsearch.do", "/kong.do", "/logan.do", "/haebing.do"
	,"/cinemaALLjson.do","/cinema11json.do","/cinema12json.do","/cinema21json.do","/cinema22json.do","/cinema31json.do","/cinema32json.do","/cinema3json.do"
	,"/cinema11deleteOK.do", "/cinema12deleteOK.do", "/cinema21deleteOK.do", "/cinema22deleteOK.do", "/cinema31deleteOK.do", "/cinema32deleteOK.do"
	,"/reservaDeleteOK.do"})
public class Moviecontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MovieService service = new MovieServiceimpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Moviecontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session;
        String seat=null;
        
        String cPath = request.getContextPath();
		String sPath = request.getServletPath();
		System.out.println("getContextPath>>"+cPath);
		System.out.println("getServletPath>>"+sPath);
		
		if(sPath.equals("/index.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema11select.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema11select.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema12select.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema12select.jsp");
			rd.forward(request, response);
			
		}else if(sPath.equals("/cinema21select.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema21select.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema22select.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema22select.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema31select.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema31select.jsp");
			rd.forward(request, response);
			
		}else if(sPath.equals("/cinema32select.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema32select.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/button.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/button.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema1search.do")){
//			seat = request.getParameter("seat");
//			request.setAttribute("seat", seat);
//			System.out.println("search.do"+seat);
//			response.sendRedirect("cinema1search.jsp");
//			RequestDispatcher rd = 
//					request.getRequestDispatcher("MovieJSP/cinema1search.jsp?seat="+seat);
//			rd.forward(request, response);
		}
		
		else if(sPath.equals("/confirm.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/confirm.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/seatsearch.do")){
			String id = request.getParameter("id");
			System.out.println("seatsearch.do");
			System.out.println("id...admin>>>>"+id);
			if(id == ""){
	             response.sendRedirect("confirm.do");
	             return;
	          }
	         
	        RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/seatsearch.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinemaALLjson.do")){
			CinemaAlljson json1 = new CinemaAlljson();
			json1.execute(request, response);
		}
		
		/////////////////////////////////x 관 x 시간대 JSON///////////////
		
		
		else if(sPath.equals("/cinema11json.do")){
			CinemaSelectedjson json2 = new CinemaSelectedjson();
			json2.execute(request, response, 11);
			System.out.println("json2 11>>>"+json2);
		}
		
		else if(sPath.equals("/cinema12json.do")){
			CinemaSelectedjson json2 = new CinemaSelectedjson();
			json2.execute(request, response, 12);
		}
		
		else if(sPath.equals("/cinema21json.do")){
			CinemaSelectedjson json2 = new CinemaSelectedjson();
			json2.execute(request, response, 21);
		}
		
		else if(sPath.equals("/cinema22json.do")){
			CinemaSelectedjson json2 = new CinemaSelectedjson();
			json2.execute(request, response, 22);
		}
		
		else if(sPath.equals("/cinema31json.do")){
			CinemaSelectedjson json2 = new CinemaSelectedjson();
			json2.execute(request, response, 31);
		}
		
		else if(sPath.equals("/cinema32json.do")){
			CinemaSelectedjson json2 = new CinemaSelectedjson();
			json2.execute(request, response, 32);
		}
		//////////////////////////////////////////////////////////////////////
		
		else if(sPath.equals("/cinema3json.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema3json.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
		///////////////////////////////////////////////
		//selectOK
		//////////////////////////////////////////////
		
		
		
		
		
		else if(sPath.equals("/cinema11insertOK.do")){
			System.out.println("cinema11insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			System.out.println("seat_ciNm"+request.getParameter("seat_ciNm"));
			
			 String id = request.getParameter("id");
	         seat = request.getParameter("seat");
	         System.out.println("OKseat>>>"+seat);
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));
	         String seat_ciNm = request.getParameter("seat_ciNm");

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);
	         vo.setSeat_ciNm(seat_ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
				System.out.println("search.do"+seat);
				RequestDispatcher rd = 
						request.getRequestDispatcher("MovieJSP/cinema1search.jsp?seat="+seat+"&ciNm="+ciNm+"&seat_ciNm="+seat_ciNm);
				rd.forward(request, response);
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema11select.do");

	         }
	    }
		
		else if(sPath.equals("/cinema12insertOK.do")){
			System.out.println("cinema12insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			System.out.println("seat_ciNm"+request.getParameter("seat_ciNm"));
			
			 String id = request.getParameter("id");
	         seat = request.getParameter("seat");
	         System.out.println("OKseat>>>"+seat);
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));
	         String seat_ciNm = request.getParameter("seat_ciNm");

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);
	         vo.setSeat_ciNm(seat_ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
				System.out.println("search.do"+seat);
				RequestDispatcher rd = 
						request.getRequestDispatcher("MovieJSP/cinema1search.jsp?seat="+seat+"&ciNm="+ciNm+"&seat_ciNm="+seat_ciNm);
				rd.forward(request, response);
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema12select.do");

	         }
			
		}else if(sPath.equals("/cinema21insertOK.do")){
			System.out.println("cinema21insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			System.out.println("seat_ciNm"+request.getParameter("seat_ciNm"));
			
			 String id = request.getParameter("id");
	         seat = request.getParameter("seat");
	         System.out.println("OKseat>>>"+seat);
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));
	         String seat_ciNm = request.getParameter("seat_ciNm");

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);
	         vo.setSeat_ciNm(seat_ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
				System.out.println("search.do"+seat);
				RequestDispatcher rd = 
						request.getRequestDispatcher("MovieJSP/cinema1search.jsp?seat="+seat+"&ciNm="+ciNm+"&seat_ciNm="+seat_ciNm);
				rd.forward(request, response);
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema21select.do");

	         }
		}
		
		else if(sPath.equals("/cinema22insertOK.do")){
			System.out.println("cinema22insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			System.out.println("seat_ciNm"+request.getParameter("seat_ciNm"));
			
			 String id = request.getParameter("id");
	         seat = request.getParameter("seat");
	         System.out.println("OKseat>>>"+seat);
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));
	         String seat_ciNm = request.getParameter("seat_ciNm");

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);
	         vo.setSeat_ciNm(seat_ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
				System.out.println("search.do"+seat);
				RequestDispatcher rd = 
						request.getRequestDispatcher("MovieJSP/cinema1search.jsp?seat="+seat+"&ciNm="+ciNm+"&seat_ciNm="+seat_ciNm);
				rd.forward(request, response);
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema22select.do");

	         }
		}
		
		else if(sPath.equals("/cinema31insertOK.do")){
			System.out.println("cinema31insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			System.out.println("seat_ciNm"+request.getParameter("seat_ciNm"));
			
			 String id = request.getParameter("id");
	         seat = request.getParameter("seat");
	         System.out.println("OKseat>>>"+seat);
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));
	         String seat_ciNm = request.getParameter("seat_ciNm");

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);
	         vo.setSeat_ciNm(seat_ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
				System.out.println("search.do"+seat);
				RequestDispatcher rd = 
						request.getRequestDispatcher("MovieJSP/cinema1search.jsp?seat="+seat+"&ciNm="+ciNm+"&seat_ciNm="+seat_ciNm);
				rd.forward(request, response);
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema31select.do");

	         }
			
		}else if(sPath.equals("/cinema32insertOK.do")){
			System.out.println("cinema32insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			System.out.println("seat_ciNm"+request.getParameter("seat_ciNm"));
			
			 String id = request.getParameter("id");
	         seat = request.getParameter("seat");
	         System.out.println("OKseat>>>"+seat);
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));
	         String seat_ciNm = request.getParameter("seat_ciNm");

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);
	         vo.setSeat_ciNm(seat_ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
				System.out.println("search.do"+seat);
				RequestDispatcher rd = 
						request.getRequestDispatcher("MovieJSP/cinema1search.jsp?seat="+seat+"&ciNm="+ciNm+"&seat_ciNm="+seat_ciNm);
				rd.forward(request, response);
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema32select.do");

	         }
		}
		
		
		///////////////////////////////////////////////////////
		//deleteOK
		//////////////////////////////////////////////////////
		
		
		
		
		else if(sPath.equals("/cinema11deleteOK.do")){
			
			String seat_ciNm = request.getParameter("deleteBtn");
			out.append(seat_ciNm);
			
			SeatVO vo = new SeatVO();
	         vo.setSeat_ciNm(seat_ciNm);;
	         System.out.println("seat_ciNm>>>> "+seat_ciNm);

	         int result = service.seat_delete(vo);
	         System.out.println("delete:" + result);

	         if (result == 1) {
	            System.out.println("delete successed");

	            response.sendRedirect("cinema11select.do");
	         } else {
	            System.out.println("delete failed");
	            response.sendRedirect("MovieJSP/cinema1search.jsp");
	         }
		}
		
		else if(sPath.equals("/cinema12deleteOK.do")){
			
			String seat_ciNm = request.getParameter("deleteBtn");
			out.append(seat_ciNm);
			
			SeatVO vo = new SeatVO();
	         vo.setSeat_ciNm(seat_ciNm);;
	         System.out.println("seat_ciNm>>>> "+seat_ciNm);

	         int result = service.seat_delete(vo);
	         System.out.println("delete:" + result);

	         if (result == 1) {
	            System.out.println("delete successed");

	            response.sendRedirect("cinema12select.do");
	         } else {
	            System.out.println("delete failed");
	            response.sendRedirect("MovieJSP/cinema1search.jsp");
	         }
		}
		
		else if(sPath.equals("/cinema21deleteOK.do")){
			
			String seat_ciNm = request.getParameter("deleteBtn");
			out.append(seat_ciNm);
			
			SeatVO vo = new SeatVO();
	         vo.setSeat_ciNm(seat_ciNm);;
	         System.out.println("seat_ciNm>>>> "+seat_ciNm);

	         int result = service.seat_delete(vo);
	         System.out.println("delete:" + result);

	         if (result == 1) {
	            System.out.println("delete successed");

	            response.sendRedirect("cinema21select.do");
	         } else {
	            System.out.println("delete failed");
	            response.sendRedirect("MovieJSP/cinema1search.jsp");
	         }
		}
		
		else if(sPath.equals("/cinema22deleteOK.do")){
			
			String seat_ciNm = request.getParameter("deleteBtn");
			out.append(seat_ciNm);
			
			SeatVO vo = new SeatVO();
	         vo.setSeat_ciNm(seat_ciNm);;
	         System.out.println("seat_ciNm>>>> "+seat_ciNm);

	         int result = service.seat_delete(vo);
	         System.out.println("delete:" + result);

	         if (result == 1) {
	            System.out.println("delete successed");

	            response.sendRedirect("cinema22select.do");
	         } else {
	            System.out.println("delete failed");
	            response.sendRedirect("MovieJSP/cinema1search.jsp");
	         }
		}
		
		else if(sPath.equals("/cinema31deleteOK.do")){
			
			String seat_ciNm = request.getParameter("deleteBtn");
			out.append(seat_ciNm);
			
			SeatVO vo = new SeatVO();
	         vo.setSeat_ciNm(seat_ciNm);;
	         System.out.println("seat_ciNm>>>> "+seat_ciNm);

	         int result = service.seat_delete(vo);
	         System.out.println("delete:" + result);

	         if (result == 1) {
	            System.out.println("delete successed");

	            response.sendRedirect("cinema31select.do");
	         } else {
	            System.out.println("delete failed");
	            response.sendRedirect("MovieJSP/cinema1search.jsp");
	         }
		}
		
		else if(sPath.equals("/cinema32deleteOK.do")){
			
			String seat_ciNm = request.getParameter("deleteBtn");
			out.append(seat_ciNm);
			
			SeatVO vo = new SeatVO();
	         vo.setSeat_ciNm(seat_ciNm);;
	         System.out.println("seat_ciNm>>>> "+seat_ciNm);

	         int result = service.seat_delete(vo);
	         System.out.println("delete:" + result);

	         if (result == 1) {
	            System.out.println("delete successed");

	            response.sendRedirect("cinema32select.do");
	         } else {
	            System.out.println("delete failed");
	            response.sendRedirect("MovieJSP/cinema1search.jsp");
	         }
		}
		
		///////////////////////////////////////////
		//movie////////////////////////////////////
		///////////////////////////////////////////
		
		else if(sPath.equals("/kong.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/kong.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/logan.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/logan.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/haebing.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/haebing.jsp");
			rd.forward(request, response);
		}
		
		
		
		////////////////////////////////////////////
		///////////reservaDeleteOK//////////////////////
		/////////////////////////////////////////////
		
		else if(sPath.equals("/reservaDeleteOK.do")){
			String seat_ciNm = request.getParameter("deleteBtn");
			out.append(seat_ciNm);
			
			SeatVO vo = new SeatVO();
	         vo.setSeat_ciNm(seat_ciNm);;
	         System.out.println("seat_ciNm>>>> "+seat_ciNm);

	         int result = service.seat_delete(vo);
	         System.out.println("delete:" + result);

	         if (result == 1) {
	            System.out.println("delete successed");

	            response.sendRedirect("seatsearch.do");
	         } else {
	            System.out.println("delete failed");
	            response.sendRedirect("MovieJSP/seatsearch.jsp");
	         }
		}
		
	}

}
