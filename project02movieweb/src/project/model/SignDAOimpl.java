package project.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SignDAOimpl implements SignDAO {


	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ConnectEXE connexe;
	
	private final String SQL_INSERT = "insert into sign(name,tel,email,id,pw) " + "values(?,?,?,?,?)";
	private final String SQL_UPDATE = "update sign set  tel=?, email=?, pw=? where id=?";
	private final String SQL_DELETE = "delete from sign where id=?";
	private final String SQL_SEARCH = "select * from sign where id=?";
	private final String SQL_SELECT = "select * from sign";

	
	
	public SignDAOimpl() {
		this.connexe = new ConnectEXE(); 
		this.conn = connexe.Conn();	
	}

	@Override
	public int insert(SignVO vo) {
		System.out.println("insert()...");
		System.out.println(vo.getName());
		System.out.println(vo.getTel());
		System.out.println(vo.getEmail());
		System.out.println(vo.getId());
		System.out.println(vo.getPw());

		int flag = 0;
		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTel());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getId());
			pstmt.setString(5, vo.getPw());

			flag = pstmt.executeUpdate();
			System.out.println("pstmt Successed..." + flag);
		} catch (SQLException e) {
			System.out.println("SQL Failed...");
			e.printStackTrace();
		} finally {
			if (pstmt != null) connexe.Disconn(pstmt);
			if (conn != null) connexe.Disconn(conn);
		}

		return flag;
	}

	@Override
	public int update(SignVO vo) {
		System.out.println("update()...");
		System.out.println(vo.getId());
		System.out.println(vo.getPw());
		System.out.println(vo.getTel());
		System.out.println(vo.getEmail());

		int flag = 0;
		try {
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, vo.getTel());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPw());
			pstmt.setString(4, vo.getId());

			flag = pstmt.executeUpdate();
			System.out.println("update flag : " + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) connexe.Disconn(pstmt);
			if (conn != null) connexe.Disconn(conn);
		}
		return flag;
	}

	@Override
	public int delete(SignVO vo) {
		System.out.println("delete()...");
		System.out.println(vo.getId());
		System.out.println(vo.getPw());
		int flag = 0;

		try {
			pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setString(1, vo.getId());

			flag = pstmt.executeUpdate();
			System.out.println("delete flag : " + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) connexe.Disconn(pstmt);
			if (conn != null) connexe.Disconn(conn);
		}

		return flag;
	}

	@Override
	public SignVO search(SignVO vo) {
		System.out.println("search()...");
		System.out.println(vo.getId());
		System.out.println(vo.getPw());

		SignVO vo2 = new SignVO();

		try {
			pstmt = conn.prepareStatement(SQL_SEARCH);
			pstmt.setString(1, vo.getId());
			

			rs = pstmt.executeQuery();
			System.out.println("search rs : " + rs);

			while (rs.next()) {
				vo2.setName(rs.getString("name"));
				vo2.setTel(rs.getString("tel"));
				vo2.setEmail(rs.getString("email"));
				vo2.setId(rs.getString("id"));
				vo2.setPw(rs.getString("pw"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) connexe.Disconn(rs);
			if (pstmt != null) connexe.Disconn(pstmt);
			if (conn != null) connexe.Disconn(conn);
		}
		return vo2;
	}

	@Override
	public List<SignVO> select() {
		System.out.println("select()...");
		List<SignVO> list = new ArrayList<SignVO>();

		try {
			pstmt = conn.prepareStatement(SQL_SELECT);

			rs = pstmt.executeQuery();
			System.out.println("select rs : " + rs);

			while (rs.next()) {
				SignVO vo2 = new SignVO();
				vo2.setName(rs.getString("name"));
				vo2.setTel(rs.getString("tel"));
				vo2.setEmail(rs.getString("email"));
				vo2.setId(rs.getString("id"));
				vo2.setPw(rs.getString("pw"));

				list.add(vo2);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) connexe.Disconn(rs);
			if (pstmt != null) connexe.Disconn(pstmt);
			if (conn != null) connexe.Disconn(conn);
		}

		return list;
	}

	@Override
	public boolean loginCheck(String id, String pw) {
		boolean result = false;
		System.out.println("in loginCheck");
		String sql = "select * from sign where id=? and pw=?";
		System.out.println(" : " + id + " " + pw);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			System.out.println("getsuccess");
			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			System.out.println("get failed...");
			e.printStackTrace();
		} finally {
			if (rs != null) connexe.Disconn(rs);
			if (pstmt != null) connexe.Disconn(pstmt);
			if (conn != null) connexe.Disconn(conn);
		}
		return result;
	}
	
	
	

}
