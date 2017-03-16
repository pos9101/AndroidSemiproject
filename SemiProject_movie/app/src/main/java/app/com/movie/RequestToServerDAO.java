package app.com.movie;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RequestToServerDAO {

	private String URL_SEARCH = "http://192.168.0.131:8090/project02movieweb/search_json.do";
	private final String URL_INSERT = "http://192.168.0.131:8090/project02movieweb/insert_json.do";
	private final String URL_UPDATE = "http://192.168.0.131t:8090/project02movieweb/update_json.do";
	private final String URL_DELETE = "http://192.168.0.131:8090/project02movieweb/delete_json.do";
	private final String URL_LOGIN = "http://192.168.0.131:8090/project02movieweb/login_json.do";
	

	private HttpURLConnection conn = null;
	
	
	public SignVO search(SignVO vo) throws JSONException {

		String jstr =requestQuery(URL_SEARCH+"?id="+vo.getId());
		 JSONObject jboj = new JSONObject(jstr);
					 vo.setId(jboj.getString("id"));
					 vo.setEmail(jboj.getString("emal"));
					 vo.setName(jboj.getString("name"));
					 vo.setTel(jboj.getString("tel"));
		return vo;
	}
	
	public boolean insert(SignVO vo){
		String jstr =requestQuery(URL_INSERT+"?id="+ vo.getId() + "&name="+ vo.getName()
				+ "&tel="+ vo.getTel() + "&email="+ vo.getEmail() + "&pw="+ vo.getPw());
		return Boolean.parseBoolean(jstr);
	}
	
	public boolean update(SignVO vo){
		String jstr =requestQuery(URL_UPDATE+"?id="+ vo.getId() + "&name="+ vo.getName()
				+ "&tel="+ vo.getTel() + "&email="+ vo.getEmail() + "&pw="+ vo.getPw());
		return Boolean.parseBoolean(jstr);
	}
	
	public boolean delete(SignVO vo){
		String jstr =requestQuery(URL_DELETE+"?id="+vo.getId());
		return Boolean.parseBoolean(jstr);
	}
	
	public boolean loginCheck(SignVO vo){
		String jstr =requestQuery(URL_LOGIN+"?id="+vo.getId()+"&pw="+ vo.getPw());
		return Boolean.parseBoolean(jstr);
	}
	
	private String requestQuery(String serverURL) {
		String lr ="";
		try {
			// settings
			URL url = new URL(serverURL);
			conn = (HttpURLConnection) url.openConnection();
			Log.i("con>>",conn.getResponseCode()+"");
//			conn.setRequestMethod("GET");
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//			conn.setConnectTimeout(60);
			
//			// request
//			OutputStream os = conn.getOutputStream();
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//			writer.write("?id=" + vo.getId() + "&name=" + vo.getName() + "&tel=" + vo.getTel() + "&email="
//					+ vo.getEmail() + "&pw=" + vo.getPw());
//
//			writer.flush();
//			writer.close();
//			os.close();
			
			// get result
			conn.connect();
			lr = lineRead();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			if (conn != null)
				conn.disconnect();
		} // end finally

		return lr;
	}// end search

	private String lineRead() {
		StringBuilder sb=null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;
			sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				if (sb.length() > 0) {
					sb.append("\n");
				}
				sb.append(line);
				System.out.println("response:" + sb.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}//end line Read()
	
	
}// end class
