package spms.controls;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import spms.dao.TaskDao;
import spms.vo.Member;
import spms.vo.Task;

@Controller
@RequestMapping("/task")
@SessionAttributes("loginUser")
public class TaskControl {

	@Autowired
	TaskDao taskDao;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String form() {
		return "task/addForm";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(Task task, Model model) throws Exception {
		int count = taskDao.insert(task);

		if (count > 0) {
			model.addAttribute("message", "등록 성공했습니다!");
		} else {
			model.addAttribute("message", "등록 실패했습니다.");
		}
		return "task/add";
	}

	@RequestMapping("/delete")
	public String delete(int no, Model model) throws Exception {
		taskDao.delete(no); 
		return "redirect:list.do";
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(required=false)String search, 
			@RequestParam(required=false)String keyword, 
			@RequestParam(required=false, defaultValue="0")int projectNo, 
			Model model) throws Exception {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (search != null && keyword != null) {
			paramMap.put("search", search);
			String[] keywordList = keyword.split("\\s+");
			paramMap.put("keywords", keywordList);
		}
		if (projectNo > 0) {
			paramMap.put("projectNo", projectNo);
		}
		model.addAttribute("tasks", taskDao.selectList(paramMap));
		return "task/list";
	}

	@RequestMapping("/read")
	public String read(int no, Model model) throws Exception {
		model.addAttribute("task", taskDao.selectOne(no));
		return "task/read";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(Task task, Model model) throws Exception {
		Task temp = taskDao.selectOne(task.getNo());
		
		int updateCount = 6;
		if (temp.getProjectNo() == task.getProjectNo()) {
			task.setProjectNo(-1); --updateCount;
		}

		if (temp.getTitle().equals(task.getTitle())) {
			task.setTitle(null); --updateCount;
		}
		
		if (temp.getStartDate().equals(task.getStartDate())) {
			task.setStartDate(null); --updateCount;
		}
		
		if (temp.getEndDate().equals(task.getEndDate())) {
			task.setEndDate(null); --updateCount;
		}
		
		if (temp.getState() == task.getState()) {
			task.setState(-1); --updateCount;
		}

		if (temp.getTags().equals(task.getTags())) {
			task.setTags(null); --updateCount;
		}

		if (updateCount == 0) {
			return "redirect:list.do";
		}
		
		int count = taskDao.update(task);

		if (count > 0) {
			model.addAttribute("message", "변경 성공했습니다!");
		} else {
			model.addAttribute("message", "변경 실패했습니다.");
		}
		return "task/update";
	}
	
	@RequestMapping("/myTasks")
	public String myTasks(@ModelAttribute("loginUser") Member loginUser,  
			Model model)	throws Exception {
		
		model.addAttribute("tasks", taskDao.selectMyTasks(loginUser.getNo()));
		
		return "task/myTasks";
	}


}
