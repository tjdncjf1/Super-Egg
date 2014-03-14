package must.dao;

import java.util.List;

import must.vo.User;

public interface UserDao {
	
	public void insert(User user) throws Exception;
	public List<User> selectList() throws Exception;
	public int selectNo(String email) throws Exception;
	
}

