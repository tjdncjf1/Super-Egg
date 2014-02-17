package spms.controls;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import spms.dao.MemberDao;
import spms.vo.JsonResult;

@Controller
@RequestMapping("/auth")
public class AuthControl {

	@Autowired(required=false)
	MemberDao memberDao; 

	//	@RequestMapping(value="/login", method=RequestMethod.GET)
	//	public String form(@CookieValue(required=false) String email, Model model) {
	//		if (email != null) {
	//			model.addAttribute("email", email);
	//			model.addAttribute("checkSaveEmail", "checked");
	//		}
	//		return "auth/login";
	//	}

	@RequestMapping(value="/login", method=RequestMethod.POST,
			produces="application/json")
	public Object login(String email, String password) throws Exception {

		try {
			HashMap<String, String> sqlparamMap = new HashMap<String,String>();
			sqlparamMap.put("email", email);
			sqlparamMap.put("password", password);

			memberDao.selectByEmailPassword(sqlparamMap);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS);
		} catch (Throwable ex) {
			return "auth/loginFail";
		}
	}

	@RequestMapping("/logout")
	public String logout(SessionStatus status) throws Exception {
		status.setComplete(); // 세션완료
		return "redirect:login.do";
	}

	@RequestMapping("/loginUser")
	public String loginUser() throws Exception {
		return "auth/loginUser";
	}

}
