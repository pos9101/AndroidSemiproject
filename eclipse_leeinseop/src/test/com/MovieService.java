package test.com;

import java.util.List;

import test.com.SeatVO;

public interface MovieService {
	public int seat_insert(SeatVO vo);
	public int seat_update(SeatVO vo);
	public int seat_delete(SeatVO vo);
	public SeatVO seat_search(SeatVO vo);
	public List<SeatVO> seat_select();
}
