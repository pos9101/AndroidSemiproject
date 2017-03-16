package project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import project.model.SignDAO;
import project.model.SignDAOimpl;
import project.model.SignVO;

/**
 * Servlet implementation class MovieController
 */
@WebServlet({ "/index.do", "/select_json.do", "/insert_json.do", "/update_json.do", "/delete_json.do", "/search_json.do",
	"/login_json.do"})
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieController() {
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
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String sPath = request.getServletPath();
		System.out.println("getContextPatt>>"+sPath);
		
		if(sPath.equals("/index.do")){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}else if(sPath.equals("/insert_json.do")){
			new MovieAction(request, response).insertAction();
		}else if(sPath.equals("/update_json.do")){
			new MovieAction(request, response).updateAction();
		}else if(sPath.equals("/search_json.do")){
			new MovieAction(request, response).searchAction();
		}else if(sPath.equals("/delete_json.do")){
			new MovieAction(request, response).deleteAction();
		}else if(sPath.equals("/login_json.do")){
			new MovieAction(request, response).loginAction();
	}
		
	}

}
