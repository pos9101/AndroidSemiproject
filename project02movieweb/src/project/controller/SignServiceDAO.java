package project.controller;

import java.util.List;

import project.model.SignVO;

public interface SignServiceDAO {
	public int insert(SignVO vo);

	public int update(SignVO vo);

	public int delete(SignVO vo);

	public SignVO search(SignVO vo);

	public List<SignVO> select();
	
	public boolean loginCheck(String id, String pw);
}
