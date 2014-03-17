package must.dao;

import java.util.List;

import must.vo.User;
import must.vo.UserItem;

public interface UserDao {
	
	public void insertUser(User user) throws Exception;
	public List<User> selectList() throws Exception;
	public int selectNo(String email) throws Exception;
	public List<UserItem> userItemList(int uNo) throws Exception; 
	
}

