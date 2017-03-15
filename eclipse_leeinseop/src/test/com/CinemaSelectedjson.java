package test.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class CinemaSelectedjson {
	MovieService service = new MovieServiceimpl();


	public String urlparse(int ciNm) {
		HttpURLConnection conn = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		URL url = null;

		String json11 = "";

		try {
			url = new URL("http://localhost:8090/Semiproject_movie/cinemaALLjson.do");
			conn = (HttpURLConnection) url.openConnection();
			is = conn.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String str = null;
			StringBuilder sb = new StringBuilder();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}

			final String txtJSON = sb.toString();

			// System.out.println("txtJSON>>>>"+txtJSON);

			JSONArray jarr = new JSONArray(txtJSON);
			JSONObject[] objs = new JSONObject[jarr.length()];
			json11 += "[";
			for (int i = 0; i < jarr.length(); i++) {
				objs[i] = jarr.getJSONObject(i);

				// System.out.println(objs[i].getInt("ciNm"));
				if (objs[i].getInt("ciNm") == ciNm) {
					json11 += objs[i].toString() + ",";
				}

			}

			json11 += "]";
			json11 = json11.replace(",]", "]");

			System.out.println("json11>>>>" + json11);

			return json11;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}// end urlparse()

	public void execute(HttpServletRequest request, HttpServletResponse response, int ciNm) throws IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		PrintWriter printWriter = response.getWriter();

		String json11 = urlparse(ciNm);
		printWriter.append(json11);
	}

}
