package must.dao;

import java.util.List;
import java.util.Map;

import must.vo.User;

public interface UserDao {
	
	public void insertUser(User user) throws Exception;
	public List<User> selectList(Map<String, Object> sqlparam) throws Exception;
	public int selectNo(String email) throws Exception;
	public List<User> emailCheck(String userEmail) throws Exception;
	
}

