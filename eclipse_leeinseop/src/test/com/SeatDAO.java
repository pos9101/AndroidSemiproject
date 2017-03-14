package test.com;

import java.util.List;

public interface SeatDAO {

		public int insert(SeatVO vo);

		public int update(SeatVO vo);

		public int delete(SeatVO vo);

		public SeatVO search(SeatVO vo);

		public List<SeatVO> select();

}
