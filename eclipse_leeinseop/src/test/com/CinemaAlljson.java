package test.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

public class CinemaAlljson {
	MovieService service = new MovieServiceimpl();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
	response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		PrintWriter printWriter = response.getWriter();


      List<SeatVO> list = service.seat_select();
      JSONArray jArray = new JSONArray(list);
		
      printWriter.append(jArray.toString());
	}
	
	
	
	
	
	
}
