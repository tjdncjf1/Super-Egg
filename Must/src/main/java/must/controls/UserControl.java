package must.controls;

import must.dao.UserDao;
import must.vo.JsonResult;
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
	public void insertUser(User user) throws Exception {
		try {
			userDao.insertUser(user);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value="/selectNo", produces="application/json") 
	public Object selectNo(String email) throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(userDao.selectNo(email));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
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
