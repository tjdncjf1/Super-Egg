package must.controls;

import must.dao.UserItemDao;
import must.vo.User;
import must.vo.UserItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userItem") 
public class UserItemControl {

	@Autowired(required=false)
	UserItemDao userItemDao;
	
	@RequestMapping("/insert") 
	public void insert(UserItem userItem) throws Exception {
		try {
			userItemDao.insert(userItem);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	

}
