package spms.controls;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spms.dao.MemberDao;
import spms.vo.JsonResult;
import spms.vo.Member;

@Controller
@RequestMapping("/member") 
public class MemberControl {
	Logger log = Logger.getLogger(MemberControl.class);

	@Autowired
	ServletContext servletContext;

	@Autowired(required=false)
	MemberDao memberDao;

	@RequestMapping(value="/add", method=RequestMethod.GET) 
	public String form() {
		return "member/addForm";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST) 
	public String add(
			Member member, 
			@RequestParam(value="photoFile", required=false) MultipartFile photoFile,  
			Model model) throws Exception {

		if (photoFile.getSize() > 0) 
			member.setPhoto(saveFile(photoFile));

		int count = memberDao.insert(member);
		if (count > 0) {
			model.addAttribute("message", "등록 성공입니다!");
		} else {
			model.addAttribute("message", "등록 실패입니다!");
		}

		return "member/add";
	}

	@RequestMapping("/delete")
	public String delete(int no, Model model) throws Exception {
		memberDao.delete(no);
		return "redirect:list.do";
	}

	@RequestMapping("/list")
	public String list(Model model) throws Exception {

		model.addAttribute("members", memberDao.selectList());
		return "member/list";
	}

	@RequestMapping("/read")
	public String read(int no, Model model) throws Exception {
		Member member = memberDao.selectOne(no);

		model.addAttribute("member", member);
		return "member/updateForm";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(Member member, 
			@RequestParam(value="photoFile", required=false) MultipartFile photoFile,
			Model model) throws Exception {

		if (photoFile.getSize() > 0) {
			member.setPhoto(saveFile(photoFile));
		}

		int count = memberDao.update(member);
		if (count > 0) {
			model.addAttribute("message", "변경 성공입니다!");
		} else {
			model.addAttribute("message", "변경 실패입니다!");
		}

		return "member/update";
	}

	private String saveFile(MultipartFile photoFile) throws IOException {
		String originFilename = photoFile.getOriginalFilename();
		log.debug("업로드 파일: " + photoFile.getName() + "=" + 
				originFilename);

		String ext = originFilename.substring(originFilename.lastIndexOf(".")); 

		String newFilename = System.currentTimeMillis() + "_"	+ 
				this.getFileCount() + ext;

		photoFile.transferTo(new File(servletContext.getRealPath(
				"/files/" + newFilename)));
		return newFilename;
	}

	int fileCount = 0;
	synchronized private int getFileCount() {
		if (fileCount > 1000) {
			fileCount = 0;
		}
		return ++fileCount;
	}

	// void를 하면 기본적으로 url을 따라서 들어감
	@RequestMapping("/projectMembers")
	public void projectMembers(int no, Model model) throws Exception {
		model.addAttribute("members", memberDao.selectListByProject(no));
	}

	@RequestMapping(value="/ajax/update", method=RequestMethod.POST,
			produces="application/json") 
	public Object ajaxUpdate(Member member) throws Exception {
		try {
			memberDao.update(member);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS);
			
		} catch (Throwable ex) {
			return new JsonResult().setResultStatus(JsonResult.FAILURE)
					.setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/ajax/add", method=RequestMethod.POST,
			produces="application/json") 
	public Object ajaxAdd(Member member) throws Exception {
		try {
			memberDao.insert(member);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS);

		} catch (Throwable ex) {
			return new JsonResult().setResultStatus(JsonResult.FAILURE)
					.setError(ex.getMessage());
		}
	}

	@RequestMapping(value="/ajax/delete", produces="application/json")
	public Object ajaxDelete(int no) throws Exception {
		try {
			memberDao.delete(no);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS);

		} catch (Throwable ex) {
			return new JsonResult().setResultStatus(JsonResult.FAILURE)
					.setError(ex.getMessage());
		}
	}

	@RequestMapping(value="/ajax/read", produces="application/json")
	public Object ajaxRead(int no) throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(memberDao.selectOne(no));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}

	@RequestMapping(value="/ajax/list", produces="application/json")
	public Object ajaxList() throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(memberDao.selectList());
			// error까지 처리하기 위해서 Exception의 상위인 Throwable을 쓴다.
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
}
