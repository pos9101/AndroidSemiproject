package project.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ProjectMain extends ConnectEXE {

	public static void main(String[] args) throws IOException {

		SignVO vo = new SignVO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SignDAO dao = new SignDAOimpl();
		List<SignVO> list = new ArrayList<SignVO>();
		
		String str = new String();
		str = br.readLine();

			vo.setName("park");
			vo.setTel("020");
			vo.setEmail("aaa@bbb.xxx");
			vo.setId(str);
			vo.setPw("7890");
		
		//!--insert
//		dao.insert(vo);
		
			
			//!--update
//			if((vo.getTel()==null)|(vo.getEmail()==null)|
//				(vo.getId()==null)|(vo.getPw()==null))
//			{System.out.println("plz retry");
//			}else{
//				dao.update(vo);
//			}
			
			
			//!--select
//		list = dao.select();
//		for (SignVO vo2 : list) {
//			System.out.println("Name: "+vo2.getName()+" Tel: "+vo2.getTel()+" Email: "+vo2.getEmail()+
//					" Id: "+vo2.getId()+" Pw: "+vo2.getPw()+":");
//		}

			
			//!--search
//			vo =dao.search(vo);
//			System.out.println("Name:"+vo.getName()+" Tel:"+vo.getTel()+" Email:"+vo.getEmail()+
//					" Id:"+vo.getId()+" Pw:"+vo.getPw());
			
			//!--loginCheck
//		String id = br.readLine();
//		String pw = br.readLine();
//		vo.setId(id);
//		vo.setPw(pw);
//			boolean flag = dao.loginCheck(id,pw);
//			
//			System.out.println("result:"+flag);
			
			//!--delete
//		int result =dao.delete(vo);
//		System.out.println("result:"+ result);
	}
}
