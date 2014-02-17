package spms.dao;

import java.util.List;

import spms.vo.AttachFile;
import spms.vo.Feed;

public interface FeedDao {
	public int insert(Feed feed) throws Exception;
	public int insertFile(AttachFile attachFile) throws Exception;
	public List<Object> feedList(int projectNo) throws Exception;
}
