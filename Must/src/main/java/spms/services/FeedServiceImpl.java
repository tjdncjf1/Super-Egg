package spms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spms.dao.FeedDao;
import spms.vo.AttachFile;
import spms.vo.Feed;

// 애는 서비스 객체다. @Component라고 해두 되는데 가독성을 위해서 @Service라고 하자.
@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	FeedDao feedDao;

	// 모두가 성공했을 때만 승인을 한다.
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void addFeed(Feed feed) throws Exception {
		feedDao.insert(feed);

		if (feed.getFiles() != null) {
			for (AttachFile file : feed.getFiles()) {
				file.setFeedNo(feed.getNo());
				feedDao.insertFile(file);
			}
		}
	}

}
