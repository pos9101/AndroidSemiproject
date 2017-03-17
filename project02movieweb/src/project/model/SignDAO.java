package project.model;

import java.util.List;

public interface SignDAO {

	public int insert(SignVO vo);

	public int update(SignVO vo);

	public int delete(SignVO vo);

	public SignVO search(SignVO vo);

	public List<SignVO> select();
	
	public boolean loginCheck(String id, String pw);
}