package must.controls;

import must.dao.UserDao;
import must.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user") 
public class UserControl {

	@Autowired(required=false)
	UserDao userDao;
	
	@RequestMapping("/add") 
	public void insert(User user) throws Exception {
		try {
			userDao.insert(user);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

}
