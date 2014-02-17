package spms.controls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import spms.services.FeedService;
import spms.vo.AttachFile;
import spms.vo.Feed;
import spms.vo.Member;

@Controller
@RequestMapping("/feed")
@SessionAttributes("loginUser")
public class FeedControl {

	@Autowired
	FeedService feedService;

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/add", method=RequestMethod.GET) 
	public String form(int projectNo, Model model) {
		model.addAttribute("projectNo", projectNo);
		return "feed/addForm";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST) 
	public String add(
			int projectNo, String contents, 
			@RequestParam("attFile") MultipartFile attFile,
			@ModelAttribute("loginUser") Member loginUser,
			Model model) throws Exception {

		Feed feed = new Feed()
			.setProjectNo(projectNo)
			.setContents(contents)
			.setWriterNo(loginUser.getNo());
		
		if (!attFile.isEmpty()) {
			ArrayList<AttachFile> fileList = new ArrayList<>(); 
			fileList.add(new AttachFile()
				.setFilePath(saveFile(attFile)));
			
			feed.setFiles(fileList);
		}
		
		feedService.addFeed(feed);
		
		return "redirect:../main2.jsp"; 
	}
	
//	@RequestMapping("/list")
//	public String list(int projectNo, Model model) throws Exception {
//		List<Object> elsa = feedDao.feedList(projectNo);
//		
//		model.addAttribute("feeds", elsa);
//		return "feed/list";
//	}
	
  private String saveFile(MultipartFile photoFile) throws IOException {
	  String originFilename = photoFile.getOriginalFilename();

	  String ext = originFilename.substring(originFilename.lastIndexOf(".")); 
	  
	  String newFilename = System.currentTimeMillis() + "_"	+ 
	  		this.getFileCount() + ext;
	  		
	  photoFile.transferTo(new File(servletContext.getRealPath(
	  		"/files/feed/" + newFilename)));
	  return newFilename;
  }

	int fileCount = 0;
	synchronized private int getFileCount() {
		if (fileCount > 1000) {
			fileCount = 0;
		}
		return ++fileCount;
	}
	
	
}