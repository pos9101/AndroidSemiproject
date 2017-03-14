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

import org.json.JSONArray;

import test.com.SeatVO;




/**
 * Servlet implementation class moviecontroller
 */
@WebServlet({"/index.do", "/cinema11select.do", "/cinema12select.do", "/cinema21select.do", "/cinema22select.do", "/cinema31select.do",
	"/cinema11insertOK.do", "/cinema12insertOK.do", "/cinema21insertOK.do", "/cinema22insertOK.do", "/cinema31insertOK.do", "/cinema32insertOK.do",
	"/cinema32select.do", "/button.do", "/cinema1search.do", "/cinema2search.do", "/cinema3search.do", "/confirm.do"
	,"/cinema1json.do","/cinema2json.do","/cinema3json.do"})
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
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema1search.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema2search.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema2search.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema3search.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema3search.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/confirm.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/confirm.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema1json.do")){
			response.setContentType("application/json");
	  		response.setHeader("Cache-Control", "nocache");
	  		response.setCharacterEncoding("utf-8");
	  		PrintWriter printWriter = response.getWriter();


	          List<SeatVO> list = service.seat_select();
	          JSONArray jArray = new JSONArray(list);
				
	          printWriter.append(jArray.toString());
		}
		
		else if(sPath.equals("/cinema2json.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema2json.jsp");
			rd.forward(request, response);
		}
		
		else if(sPath.equals("/cinema3json.do")){
			RequestDispatcher rd = 
					request.getRequestDispatcher("MovieJSP/cinema3json.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
		
		else if(sPath.equals("/cinema11insertOK.do")){
			System.out.println("cinema11insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			 String id = request.getParameter("id");
	         String seat = request.getParameter("seat");
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
	            response.sendRedirect("index.do");
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
			 String id = request.getParameter("id");
	         String seat = request.getParameter("seat");
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
	            response.sendRedirect("index.do");
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema12select.do");

	         }
			
		}else if(sPath.equals("/cinema21insertOK.do")){
			System.out.println("cinema21insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			 String id = request.getParameter("id");
	         String seat = request.getParameter("seat");
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
	            response.sendRedirect("index.do");
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
			 String id = request.getParameter("id");
	         String seat = request.getParameter("seat");
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
	            response.sendRedirect("index.do");
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
			 String id = request.getParameter("id");
	         String seat = request.getParameter("seat");
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
	            response.sendRedirect("index.do");
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema31select.do");

	         }
			
		}else if(sPath.equals("/cinema32insertOK.do")){
			System.out.println("cinema32insert.do");
			System.out.println("id: "+request.getParameter("id"));
			System.out.println("seat: "+request.getParameter("seat"));
			System.out.println("ciNm: "+Integer.parseInt(request.getParameter("ciNm")));
			 String id = request.getParameter("id");
	         String seat = request.getParameter("seat");
	         int ciNm = Integer.parseInt(request.getParameter("ciNm"));

	         SeatVO vo = new SeatVO();
	         vo.setId(id);
	         vo.setSeat(seat);
	         vo.setCiNm(ciNm);

	         int result = service.seat_insert(vo);
	         System.out.println("seatInsert :" + result);
	         if (result == 1) {
	            System.out.println("seatInsert successed");
	            response.sendRedirect("index.do");
	         } else {
	            System.out.println("seatInsert Failed");
	            response.sendRedirect("cinema32select.do");

	         }
		}
	}

}
