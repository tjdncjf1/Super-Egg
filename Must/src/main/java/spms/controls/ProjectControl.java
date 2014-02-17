package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import spms.dao.ProjectDao;
import spms.vo.JsonResult;
import spms.vo.Member;
import spms.vo.Project;

@Controller
@RequestMapping("/project")
@SessionAttributes("loginUser")
public class ProjectControl {

	@Autowired
	ProjectDao projectDao;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String form() {
		return "project/addForm";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(Project project, Model model) throws Exception {

		int count = projectDao.insert(project);

		if (count > 0) {
			model.addAttribute("message", "등록 성공했습니다!");
		} else {
			model.addAttribute("message", "해당하는 번호의 정보가 없습니다.");
		}

		return "project/add";
	}

	@RequestMapping("/delete")
	public String delete(int no, Model model) throws Exception {
		projectDao.delete(no);
		return "redirect:list.do";
	}

	@RequestMapping("/list")
	public String list(String search, String keyword, Model model)	throws Exception {
		HashMap<String, Object> paramMap = new HashMap<>();
		if (search != null && keyword != null) {
			paramMap.put("search", search);
			String[] keywordList = keyword.split("\\s+");
			paramMap.put("keywords", keywordList);
		}
		model.addAttribute("projects", projectDao.selectList(paramMap));
		return "project/list";
	}

	@RequestMapping("/read")
	public String read(int no, Model model) throws Exception {
		model.addAttribute("project", projectDao.selectOne(no));
		return "project/read";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(Project project, Model model) throws Exception {
		Project origin = projectDao.selectOne(project.getNo()); 
		int updateCount = 6;
		if (origin.getTitle().equals(project.getTitle())) {
			project.setTitle(null); updateCount--;
		}
		
		if (origin.getContents().equals(project.getContents())) {
			project.setContents(null); updateCount--;
		}
		
		if (origin.getStartDate().equals(project.getStartDate())) {
			project.setStartDate(null); updateCount--;
		}
		
		if (origin.getEndDate().equals(project.getEndDate())) {
			project.setEndDate(null); updateCount--;
		}
		
		if (origin.getTags().equals(project.getTags())) {
			project.setTags(null); updateCount--;
		}
		
		if (origin.getState() == project.getState()) {
			project.setState(-1); updateCount--;
		}
		
		if (updateCount == 0) {
			return "redirect:list.do";
		}
		
		int count = projectDao.update(project);
		if (count > 0) {
			model.addAttribute("message", "변경 성공했습니다!");
		} else {
			model.addAttribute("message", "해당하는 번호의 정보가 없습니다.");
		}
		return "project/update";
	}

	@RequestMapping("/myProjects")
	public String myProjects(
			@ModelAttribute("loginUser") Member loginUser,  // 세션 정보 가져옴.
			// HttpSession session,      // 이렇게 해두 되지만 위처럼 어노테이션으로 구현하는게 더 편함.
			Model model)	throws Exception {
		
		// Member loginUser = (Member)session.getAttribute("loginUser");
		
		model.addAttribute("projects", 
				projectDao.selectMyProjects(loginUser.getNo()));
		
		return "project/myProjects";
	}
	
	@RequestMapping("/desc")
	public String desc(int no, Model model) throws Exception {
		model.addAttribute("project", projectDao.selectOne(no));
		return "project/desc";
	}
	
	@RequestMapping(value="/ajax/list", produces="application/json")
	public Object ajaxList() throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(projectDao.selectList(map));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/ajax/read", produces="application/json")
	public Object ajaxRead(int no) throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(projectDao.selectOne(no));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/ajax/delete", produces="application/json")
	public Object ajaxDelete(int no) throws Exception {
		try {
			projectDao.delete(no);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS);

		} catch (Throwable ex) {
			return new JsonResult().setResultStatus(JsonResult.FAILURE)
					.setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/ajax/add", method=RequestMethod.POST,
			produces="application/json") 
	public Object ajaxAdd(Project project) throws Exception {
		try {
			projectDao.insert(project);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS);

		} catch (Throwable ex) {
			return new JsonResult().setResultStatus(JsonResult.FAILURE)
					.setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/ajax/update", method=RequestMethod.POST,
			produces="application/json") 
	public Object ajaxUpdate(Project project) throws Exception {
		try {
			projectDao.update(project);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS);
			
		} catch (Throwable ex) {
			return new JsonResult().setResultStatus(JsonResult.FAILURE)
					.setError(ex.getMessage());
		}
	}
	
	
}
