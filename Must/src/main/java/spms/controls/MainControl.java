package spms.controls;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainControl {

	//-------------------------------------------------
	// 파라미터에 대해...
	// 1. JSP에 어떻게 값을 전달할 것인가?
	//-------------------------------------------------

	/* JSP에게 전달할 값과 뷰 이름을 함께 리턴 */
	// 최근에는 이 형식을 씀.
	@RequestMapping("/main")
	public String execute(Model model) throws Exception {
		return "main";
	}
	
	/* JSP에게 전달할 값과 뷰 이름을 함께 리턴 
	// 이 형식을 많이 씀.
	@RequestMapping("/main")
	public ModelAndView execute() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageTitle", "SPMS 메인5");
		mv.setViewName("main");
		return mv;
	}
	*/
	
	/* 뷰 컴포넌트에게 전달할 값을 담은 Model 객체를 리턴
	@RequestMapping("/main")
  public Model execute() throws Exception {
		ExtendedModelMap model = new ExtendedModelMap();
		model.addAttribute("pageTitle", "SPMS 메인3");
		return model;
	}
	 */ 

	/* 뷰 컴포넌트에게 전달할 값을 담을 Map 객체를 프론트 콘트롤러에게 요청
	 * 	- 요청 핸들러의 파라미터에 Map 타입의 변수 선언 
	@RequestMapping("/main")
	// map 객체를 얻어 올 수도 있다.
  public String execute(Map model) throws Exception {
		model.put("pageTitle", "SPMS 메인2");
		return "main";
	}
	*/ 
	
	/* 뷰 컴포넌트에게 전달할 값을 담을 Model 객체를 프론트 콘트롤러에게 요청
	 * 	- 요청 핸들러의 파라미터에 Model 타입의 변수 선언 
	@RequestMapping("/main")
	// 필요할 때 Model의 빈 객체를 받음
  public String execute(Model model) throws Exception {
		model.addAttribute("pageTitle", "SPMS 메인");
		return "main";
  }
	*/
		
	//-------------------------------------------------
	// 리턴 타입에 대해...
	//-------------------------------------------------
	/* JSP에 전달할 데이터와 JSP이름을 함께 리턴
	@RequestMapping("/main")
	public ModelAndView execute() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	 */	

	/* 리턴 값이 없으면, 뷰의 주소는 요청 URL과 같다. 
	 * 예) /member/list.do => member/list
	 * 예) /main.do => main
	@RequestMapping("/main")
	public void execute() throws Exception {

	}
	 */

	// .do는 적을 필요 없다. 프런트 콘트롤러가 /main.do가 들어오면 .do는 제거하고 
	//  여기 매핑한 메소드로 오게 된다.
	// 어떤 요청에 대해서 어떤 메서드가 호출 되는지 알 수 있게 해준다.
	/*
	@RequestMapping("/main")
  public String execute() throws Exception {
		// resultMap.put("pageTitle", "SPMS 메인");
		// page controller를 거쳐야만 jsp에 접근할 수 있게 했음.
		// jsp를 WEB-INF/view에 옮김. (외부에서는 WEB-INF 안을 접근 못함)
		return "main";
  }
	 */
}
