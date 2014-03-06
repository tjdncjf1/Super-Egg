package must.search;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import must.dao.ItemDao;
import must.vo.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class SearchBot {

	@Autowired(required=false)
	ItemDao itemDao;
	// 3600000
	@Scheduled(fixedDelay=1000)
	public void doSchedule() throws Exception {
		
		try {
			ArrayList<Item> sItem = (ArrayList<Item>)itemDao.selectList();
			for (int i = 0; i < sItem.size(); i++){
				String requestUrl = "http://openapi.naver.com/search?";
						requestUrl += "key=be6c30428660950b9ece4f651a0d2dba"; 
						requestUrl += "&target=shop";
						requestUrl += "&display=10";
						requestUrl += "&query=" + sItem.get(i).getTitle();
				URL url = new URL(requestUrl);
			
				//API 요청 및 반환
				URLConnection conn = url.openConnection();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(conn.getInputStream());
				
				Element root = doc.getDocumentElement();
				NodeList items = root.getElementsByTagName("item");
				String result = "";
				for (int j = 0; j < items.getLength(); j++) {
	        Node item = items.item(j);
	        Node lPrice = item.getFirstChild();
	        
	        
        }
				
				
			}
			
    } catch (Exception e) {
    	e.printStackTrace();
    }
		
	}
	
	
	
	
}
