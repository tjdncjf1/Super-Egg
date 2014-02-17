package spms.vo;

public class AttachFile {

	protected int 			no;
	protected int				feedNo;
	protected	String		filePath;
	
	public int getNo() {
		return no;
	}
	
	public AttachFile setNo(int no) {
		this.no = no;
		return this;
	}
	
	public int getFeedNo() {
		return feedNo;
	}
	
	public AttachFile setFeedNo(int feedNo) {
		this.feedNo = feedNo;
		return this;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public AttachFile setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}
	
}
