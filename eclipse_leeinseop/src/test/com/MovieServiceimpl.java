package test.com;

import java.util.List;

public class MovieServiceimpl implements MovieService {
	SeatDAO seat_dao = new SeatDAOimpl();

	@Override
	public int seat_insert(SeatVO vo) {
		// TODO Auto-generated method stub
		return seat_dao.insert(vo);
	}

	@Override
	public int seat_update(SeatVO vo) {
		// TODO Auto-generated method stub
		return seat_dao.update(vo);
	}

	@Override
	public int seat_delete(SeatVO vo) {
		// TODO Auto-generated method stub
		return seat_dao.delete(vo);
	}

	@Override
	public SeatVO seat_search(SeatVO vo) {
		// TODO Auto-generated method stub
		return seat_dao.search(vo);
	}

	@Override
	public List<SeatVO> seat_select() {
		// TODO Auto-generated method stub
		return seat_dao.select();
	}

}
