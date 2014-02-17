package spms.vo;

import java.io.Serializable;
import java.sql.Date;

public class Task implements Serializable {		
	
  private static final long serialVersionUID = 1L;
	int 		no;
	String  title;
	Date 		startDate;
	Date 		endDate;
	String  tags;
	int			state;
	int 		projectNo;
	int     memberNo;
	Member  worker;
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Member getWorker() {
		return worker;
	}

	public void setWorker(Member worker) {
		this.worker = worker;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	
	
	
}
