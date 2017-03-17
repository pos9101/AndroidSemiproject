package project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import project.model.SignDAO;
import project.model.SignDAOimpl;
import project.model.SignVO;

public class MovieAction {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private SignVO vo;
	private SignDAO dao ;
	private PrintWriter out;
	private String result = "false";

	
	public MovieAction(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println(request.toString());
		this.request= request;
		this.response= response;
		out = response.getWriter();
		vo= new SignVO();
		dao = new SignDAOimpl();
	}
	
	public void searchAction(){
		//input
		System.out.println("insearchAction");
		System.out.println(request.getParameter("id"));
		vo.setId(request.getParameter("id"));
		vo = dao.search(vo);
		
		//output
		JSONObject jObj = new JSONObject(vo);
		out.append(jObj.toString());
	}//end searchAction
	
	
	public void updateAction(){
		System.out.println("updatechAction");
		//input
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		vo.setName(request.getParameter("name"));
		vo.setTel(request.getParameter("tel"));
		vo.setEmail(request.getParameter("email"));
		
		//output
		int flag = dao.update(vo);
		
		if (flag>0){
			result = "true";
			out.append(result);
		}else{
			result = "false";
			out.append(result);
		}
		
	}//end updateAction
	
	public void insertAction() {
		System.out.println("insertchAction");
		//input
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		vo.setName(request.getParameter("name"));
		vo.setTel(request.getParameter("tel"));
		vo.setEmail(request.getParameter("email"));
		System.out.println("insertAction");
		System.out.println("into:"+"&name="+vo.getName()+
				"&tel="+vo.getTel()+"&email="+vo.getEmail()+"&pw="+vo.getPw());
		
//		output
		int flag = dao.insert(vo);
		System.out.println("flag="+flag);
		if (flag>0){
			result = "true";
			System.out.println("result="+result);
			out.append(result);
		}else{
			result = "false";
			out.append(result);
		}
		
	}//end insertAction
	
	public void deleteAction(){
		System.out.println("deletechAction");
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("pw"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("tel"));
		System.out.println(request.getParameter("email"));
		
		vo.setId(request.getParameter("id"));
		
		
		
		
		int flag = dao.delete(vo);
		if (flag>0){
			result = "true";
			out.append(result);
		}else{
			result = "false";
			out.append(result);
		}
	}//end deleteAction

	public void loginAction() {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		boolean flag = dao.loginCheck(id, pw);
		if (flag==true){
			result = "true";
			System.out.println("result="+result);
			out.append(result);
		}else{
			result = "false";
			out.append(result);
		}
	}
	
}
