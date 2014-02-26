package must.dao;

import java.util.List;
import java.util.Map;

import must.vo.Member;

// 규칙들  
// MemberMapper.xml에 MemberDao가 네임스페이스여야 한다.
public interface ItemDao {

	// MemberMapper.xml에 id와 메소드명이 일치해야한다.
	public List<Member> selectList() throws Exception;
	public Member selectOne(int no) throws Exception;
	public int insert(Member member) throws Exception;
	public int update(Member member) throws Exception;
	public int delete(int no) throws Exception;
	public Member selectByEmailPassword(Map<String,String> paramMap) 
			throws Exception;
	public List<Member> selectListByProject(int projectNo) throws Exception;
	
}







