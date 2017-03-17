package project.controller;

import java.util.List;

import project.model.SignDAO;
import project.model.SignDAOimpl;
import project.model.SignVO;

public class SignServiceDAOimpl implements SignServiceDAO{
	SignDAO dao = new SignDAOimpl();
	
	@Override
	public int insert(SignVO vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	@Override
	public int update(SignVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	@Override
	public int delete(SignVO vo) {
		// TODO Auto-generated method stub
		return dao.delete(vo);
	}

	@Override
	public SignVO search(SignVO vo) {
		// TODO Auto-generated method stub
		return dao.search(vo);
	}

	@Override
	public List<SignVO> select() {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public boolean loginCheck(String id, String pw) {
		// TODO Auto-generated method stub
		return dao.loginCheck(id, pw);
	}

}
