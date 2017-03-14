package test.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAOimpl implements SeatDAO {
	
	private final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "movie";
	private final String PASSWORD = "hi123456";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private final String SQL_INSERT = "insert into seat(id,seat,ciNm,seat_ciNm) " + "values(?,?,?,?)";
	private final String SQL_UPDATE = "update seat set  seat=?, ciNm=? ,seat_ciNm where id=?";
	private final String SQL_DELETE = "delete from seat where seat_ciNm=?";
	private final String SQL_SEARCH = "select id,seat,ciNm from seat where id=?";
	private final String SQL_SELECT = "select * from seat";
	
	public SeatDAOimpl() {
		System.out.println("SeatDAOimpl()");
		try {
			Class.forName(DRIVER_NAME);
			System.out.println("Driver Successsed");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Failed");
			e.printStackTrace();
		}
	}


	@Override
	public int insert(SeatVO vo) {
		System.out.println("insert()...");
		System.out.println(vo.getId());
		System.out.println(vo.getSeat());
		System.out.println(vo.getCiNm());
		System.out.println(vo.getSeat_ciNm());

		int flag = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("insert conn successed...");
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getSeat());
			pstmt.setInt(3, vo.getCiNm());
			pstmt.setString(4, vo.getSeat_ciNm());

			flag = pstmt.executeUpdate();
			System.out.println("pstmt Successed..." + flag);
		} catch (SQLException e) {
			System.out.println("SQL Failed...");
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return flag;
	}
	

	@Override
	public int update(SeatVO vo) {
		int flag = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("conn Successed...");

			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getSeat());
			pstmt.setInt(3, vo.getCiNm());
			pstmt.setString(4, vo.getSeat_ciNm());

			flag = pstmt.executeUpdate();
			System.out.println("update flag : " + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	@Override
	public int delete(SeatVO vo) {
		int flag = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setString(1, vo.getSeat_ciNm());

			flag = pstmt.executeUpdate();
			System.out.println("delete flag : " + flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return flag;
	}

	@Override
	public SeatVO search(SeatVO vo) {
		SeatVO vo2 = new SeatVO();

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("conn Successed...");

			pstmt = conn.prepareStatement(SQL_SEARCH);
			pstmt.setString(1, vo.getId());
			

			rs = pstmt.executeQuery();
			System.out.println("search rs : " + rs);

			while (rs.next()) {
				vo2.setId(rs.getString("id"));
				vo2.setSeat(rs.getString("seat"));
				vo2.setCiNm(rs.getInt("ciNm"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vo2;
	}

	@Override
	public List<SeatVO> select() {
		List<SeatVO> list = new ArrayList<SeatVO>();

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("conn Successed...");
			pstmt = conn.prepareStatement(SQL_SELECT);

			rs = pstmt.executeQuery();
			System.out.println("select rs : " + rs);

			while (rs.next()) {
				SeatVO vo2 = new SeatVO();
				vo2.setId(rs.getString("id"));
				vo2.setSeat(rs.getString("seat"));
				vo2.setCiNm(rs.getInt("ciNm"));
				vo2.setSeat_ciNm(rs.getString("seat_ciNm"));

				list.add(vo2);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
	

}
