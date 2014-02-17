package spms.services;

import spms.vo.Feed;

public interface FeedService {
	// service에는 업무쪽에 관련되게 메소드명을 지어야한다. 
	void addFeed(Feed feed) throws Exception;
}
