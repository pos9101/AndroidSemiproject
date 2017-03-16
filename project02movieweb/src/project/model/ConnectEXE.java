package project.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectEXE {

	
	static final String  DRIVER= "oracle.jdbc.OracleDriver";
	static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	static final String USER = "movie";
	static final String PASSWORD = "hi123456";
	
	private Connection conn;

	public ConnectEXE() {
		try {
			Class.forName(DRIVER);
			System.out.println("!!!detected Driver!!!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection Conn() {
		try {

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("!!!connection success!!!");
		} catch (SQLException e) {
			System.out.println("connection failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public int Disconn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("disconnected...");
			} catch (SQLException e) {
				System.out.println("disconnected...err");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0;
	}

	public int Disconn(PreparedStatement pstmt) {

		if (pstmt != null) {
			try {
				pstmt.close();
				System.out.println("pstmt close...");
			} catch (SQLException e) {
				System.out.println("pstmt close...err");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int Disconn(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				System.out.println("resultset close....");
			} catch (SQLException e) {
				System.out.println("resultset close....err");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0;
	}

	public boolean loginCheck(String id, String pw) {
		// TODO Auto-generated method stub
		return false;
	}

}
