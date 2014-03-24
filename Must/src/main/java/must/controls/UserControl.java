package must.controls;

import java.util.HashMap;

import must.dao.UserDao;
import must.vo.JsonResult;
import must.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user") 
public class UserControl {

	@Autowired(required=false)
	UserDao userDao;
	
	@RequestMapping(value="/login", produces="application/json", method=RequestMethod.POST)
	public Object login(String email, String password) throws Exception {
		try {
			HashMap<String, Object> sqlparam = new HashMap<>();
			sqlparam.put("email", email);
			sqlparam.put("password", password);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(userDao.selectList(sqlparam));
			  		
		}catch (Throwable ex){
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
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
	
//	@RequestMapping("/emailCheck") 
//	public String check(String userEmail) throws Exception {
//		try {
//			ArrayList<User> userInfo = (ArrayList<User>) userDao
//																											.emailCheck(userEmail);
//			if (userInfo.size() == 1) {
//				return "true";
//			} else {
//				return "false";
//			}
//			
//		} catch (Throwable ex) {
//			ex.printStackTrace();
//			return "false";
//		}
//	}

	@RequestMapping(value="/emailCheck", produces="application/json") 
	public Object check(String userEmail) throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(userDao.emailCheck(userEmail));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
}
