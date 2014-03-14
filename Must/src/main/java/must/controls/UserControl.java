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
	
//	@RequestMapping("/addItem") 
//	public void insertItem(User user) throws Exception {
//		try {
//			userDao.insert(user);
//		} catch (Throwable ex) {
//			ex.printStackTrace();
//		}
//	}
	
	@RequestMapping("/selectNo") 
	public void selectNo(String email) throws Exception {
		try {
			userDao.selectNo(email);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	
//	@RequestMapping("/check") 
//	public void check(String email, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		try {
//			request.setCharacterEncoding("utf-8");
//			ArrayList<User> ulist = (ArrayList<User>) userDao.selectList();
//			PrintWriter out = response.getWriter();
//			
//			for (User u : ulist) { 
//				if (email.equals(u.getEmail())) {
//					out.println("false");
//				} else {
//					out.println("true");
//				}
//			}
//			
//		} catch (Throwable ex) {
//			ex.printStackTrace();
//		}
//	}

}
