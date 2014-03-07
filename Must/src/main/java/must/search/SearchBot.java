package must.search;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import must.dao.ItemDao;
import must.vo.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

@Service
public class SearchBot {

	@Autowired(required=false)
	ItemDao itemDao;

	@Scheduled(fixedDelay=3600000)
	public void doSchedule() throws ParserConfigurationException, SAXException, IOException {

		try {
			ArrayList<Item> sItem = (ArrayList<Item>)itemDao.selectList();
			for (int i = 0; i < sItem.size(); i++){
				String requestUrl = "";
				requestUrl += "http://openapi.naver.com/search?";
				requestUrl += "key=be6c30428660950b9ece4f651a0d2dba"; 
				requestUrl += "&target=shop";
				requestUrl += "&display=10";
				requestUrl += "&query=" + URLEncoder.encode(sItem.get(i).getTitle(),"UTF-8");
				URL url = new URL(requestUrl);
				System.out.println(requestUrl);
				
				//API 요청 및 반환
				URLConnection conn = url.openConnection();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(conn.getInputStream());
				
				//channel노드를 객체화 하기
				Node node = doc.getElementsByTagName("channel").item(0);
				System.out.println("1" + node);
				System.out.println("2" + node.getChildNodes().getLength());
				for (int j = 0; j < node.getChildNodes().getLength(); j++) {
	        Node channelNode = node.getChildNodes().item(j);
	        String nodeName = channelNode.getNodeName();
	        
	        // item 노드들일 경우
	        if ("item".equals(nodeName)) {
	        	int lowPrice = 0;
	        	String productId = null;
	        	HashMap<String, Object> uItem = new HashMap<>();
	        	
	        	// item 노드의 자식노드를 검색
	        	for (int k = 0; k < channelNode.getChildNodes().getLength(); k++) {
	            Node itemNode = channelNode.getChildNodes().item(k);
	            
	            if ("lprice".equals(itemNode.getNodeName())) {
	            	lowPrice = Integer.parseInt(itemNode.getTextContent());
	            	uItem.put("lPrice", lowPrice);
	            }
	            
	            if ("productId".equals(itemNode.getNodeName())) {
	            	productId = itemNode.getTextContent();
	            	uItem.put("pId", productId);
	            }
	            
	            if (uItem.size() == 2) {
		            itemDao.update(uItem);
		            uItem.remove("lPrice");
		            uItem.remove("pId");
	            }
	            
	            
	            
	            
	        	}
	        }
        }
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}




}