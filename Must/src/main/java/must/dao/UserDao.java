package must.dao;

import must.vo.User;

public interface UserDao {
	
	public void insertUser(User user) throws Exception;
//	public List<User> selectList() throws Exception;
	public int selectNo(String email) throws Exception;
	
}

