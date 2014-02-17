package spms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import spms.vo.Member;

public class Feed implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int								no;
	protected String 						contents;
	protected Date							createdDate;
	protected	int								writerNo;
	protected	int								projectNo;
	protected	Member						writer;
	protected List<AttachFile> 	files;
	
	public int getNo() {
		return no;
	}
	
	public Feed setNo(int no) {
		this.no = no;
		return this;
	}
	
	public String getContents() {
		return contents;
	}
	
	public Feed setContents(String contents) {
		this.contents = contents;
		return this;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public Feed setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	
	public int getProjectNo() {
		return projectNo;
	}
	
	public Feed setProjectNo(int projectNo) {
		this.projectNo = projectNo;
		return this;
	}
	
	public Member getWriter() {
		return writer;
	}
	
	public Feed setWriter(Member writer) {
		this.writer = writer;
		return this;
	}

	public int getWriterNo() {
		return writerNo;
	}

	public Feed setWriterNo(int writerNo) {
		this.writerNo = writerNo;
		return this;
	}

	public List<AttachFile> getFiles() {
		return files;
	}

	public Feed setFiles(List<AttachFile> files) {
		this.files = files;
		return this;
	}
	
	
}
