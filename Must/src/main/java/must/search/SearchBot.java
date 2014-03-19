package must.search;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import must.dao.DayChartDao;
import must.dao.HourChartDao;
import must.dao.ItemDao;
import must.dao.MonthChartDao;
import must.dao.WeekChartDao;
import must.dao.YearChartDao;
import must.vo.Chart;
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
	
	@Autowired(required=false)
	HourChartDao hourChartDao;
	
	@Autowired(required=false)
	DayChartDao dayChartDao;

	@Autowired(required=false)
	WeekChartDao weekChartDao;
	
	@Autowired(required=false)
	MonthChartDao monthChartDao;
	
	@Autowired(required=false)
	YearChartDao yearChartDao;
	
	@Scheduled(cron="0 59 23 28 12 ?")
	public void doYearSchedule() throws ParserConfigurationException, SAXException, IOException {
		try {
	    ArrayList<Item> sItem = (ArrayList<Item>)itemDao.selectList();
	    ArrayList<Chart> mList = null;
	    Chart c = new Chart();
	    for (int i = 0; i < sItem.size(); i++) {
	    	mList = (ArrayList<Chart>) monthChartDao.selectList(sItem.get(i).getpId());
	      int min = 0;
	      if (mList.size() != 1) {
		      min = mList.get(0).getPrice();
		      for (int j = 1; j < mList.size(); j++) {
		      	if (mList.get(j).getPrice() < min) {
		      		min = mList.get(j).getPrice();
		      	}
		      }
	      } else {
	      	min = mList.get(0).getPrice();
	      }
	      
	      yearChartDao.insert(c.setPrice(min)
	      										 .setpId(sItem.get(i).getpId())
	      										 .setTime(new Date()));
      }
    } catch (Exception e) {
    	e.printStackTrace();
    }
	}
	
	@Scheduled(cron="0 59 23 28 * ?")
	public void doMonthSchedule() throws ParserConfigurationException, SAXException, IOException {
		try {
	    ArrayList<Item> sItem = (ArrayList<Item>)itemDao.selectList();
	    ArrayList<Chart> wList = null;
	    ArrayList<Chart> mList = null;
	    Chart c = new Chart();
	    for (int i = 0; i < sItem.size(); i++) {
	      wList = (ArrayList<Chart>) weekChartDao.selectList(sItem.get(i).getpId());
	      int min = 0;
	      if (wList.size() != 1) {
		      min = wList.get(0).getPrice();
		      for (int j = 1; j < wList.size(); j++) {
		      	if (wList.get(j).getPrice() < min) {
		      		min = wList.get(j).getPrice();
		      	}
		      }
	      } else {
	      	min = wList.get(0).getPrice();
	      }
	      
	      mList = (ArrayList<Chart>) monthChartDao.selectList(sItem.get(i).getpId());
	      if (mList.size() > 12) {
	      	int distinction = mList.size() - 12;
	      	for (int j = 0; j <= distinction; j++) {
	          monthChartDao.delete(mList.get(j).getNo());
          }
	      } else if (mList.size() == 12) {
	      		monthChartDao.delete(wList.get(0).getNo());
	      } else {
	      
	      	monthChartDao.insert(c.setPrice(min)
	      										 .setpId(sItem.get(i).getpId())
	      										 .setTime(new Date()));
	      }
      }
    } catch (Exception e) {
    	e.printStackTrace();
    }
	}
	
	//fixedDelay=72000
	//cron="0 59 23 * * SUN"
	@Scheduled(fixedRate=336000)
	public void doWeekSchedule() throws ParserConfigurationException, SAXException, IOException {
		try {
			ArrayList<Item> sItem = (ArrayList<Item>)itemDao.selectList();
			ArrayList<Chart> dList = null;
			ArrayList<Chart> wList = null;
			Chart c = new Chart();
			for (int i = 0; i < sItem.size(); i++) {
				dList = (ArrayList<Chart>) dayChartDao.selectList(sItem.get(i).getpId());
	      int min = 0;
	      if (dList.size() != 1) {
		      min = dList.get(0).getPrice();
		      for (int j = 1; j < dList.size(); j++) {
		      	if (dList.get(j).getPrice() < min) {
		      		min = dList.get(j).getPrice();
		      	}
		      }
	      } else {
	      	min = dList.get(0).getPrice();
	      }
	      
	      wList = (ArrayList<Chart>) weekChartDao.selectList(sItem.get(i).getpId());
	      if (wList.size() > 12) {
	      	int distinction = wList.size() - 12;
	      	for (int j = 0; j <= distinction; j++) {
	          weekChartDao.delete(wList.get(j).getNo());
          }
	      } else if (wList.size() == 12) {
	      	weekChartDao.delete(wList.get(0).getNo());
	      } else {
	      
	      	weekChartDao.insert(c.setPrice(min)
	      										 .setpId(sItem.get(i).getpId())
	      										 .setTime(new Date()));
	      }
      }
		} catch (Exception e) {
    	e.printStackTrace();
    }
	}	
	
	//fixedDelay=24000
	//cron="0 0 0 * * ?"
	@Scheduled(fixedRate=48000)
	public void doDaySchedule() throws ParserConfigurationException, SAXException, IOException {
		try {
			ArrayList<Item> sItem = (ArrayList<Item>)itemDao.selectList();
			ArrayList<Chart> hList = null;
			ArrayList<Chart> dList = null;
			Chart c = new Chart();
			for (int i = 0; i < sItem.size(); i++) {
	      hList = (ArrayList<Chart>) hourChartDao.selectList(sItem.get(i).getpId());
	      int min = 0;
	      if (hList.size() != 1) {
		      min = hList.get(0).getPrice();
		      for (int j = 1; j < hList.size(); j++) {
		      	if (hList.get(j).getPrice() < min) {
		      		min = hList.get(j).getPrice();
		      	}
		      }
	      } else {
	      	min = hList.get(0).getPrice();
	      }
								
	      // day 리스트를 검색해서 14개 이상이 될 경우 첫번째 정보를 삭제함.
	      dList = (ArrayList<Chart>) dayChartDao.selectList(sItem.get(i).getpId());
	      if (dList.size() > 14) {
	      	int distinction = dList.size() - 14;
	      	for (int j = 0; j <= distinction; j++) {
	          dayChartDao.delete(dList.get(j).getNo());
          }
	      } else if (dList.size() == 14) {
	      		dayChartDao.delete(dList.get(0).getNo());
	      } else {
						      
		      // 삭제했으므로 추가하기 전까지는 day리스트의 개수는 13개.
		      dayChartDao.insert(c.setPrice(min)
		 	       									.setpId(sItem.get(i).getpId())
		 	       									.setTime(new Date()));
		      hourChartDao.delete(sItem.get(i).getpId());
	      }
			}
    } catch (Exception e) {
    	e.printStackTrace();
    }
	}	
	
	//fixedDelay=1000
// cron="0 0 * * * *"
	@Scheduled(fixedRate=20000)
	public void doHourSchedule() throws ParserConfigurationException, SAXException, IOException {

		try {
			ArrayList<Item> sItem = (ArrayList<Item>)itemDao.selectList();
			for (int i = 0; i < sItem.size(); i++){
				String requestUrl = "";
				requestUrl += "http://openapi.naver.com/search?";
				requestUrl += "key=adb429b4dea772836424990c303a7755"; 
				requestUrl += "&target=shop";
				requestUrl += "&display=10";
				requestUrl += "&query=" + URLEncoder.encode(sItem.get(i).getTitle(),"UTF-8");
				URL url = new URL(requestUrl);
				
				//API 요청 및 반환
				URLConnection conn = url.openConnection();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(conn.getInputStream());
				
				//channel노드를 객체화 하기
				Node node = doc.getElementsByTagName("channel").item(0);
				for (int j = 0; j < node.getChildNodes().getLength(); j++) {
	        Node channelNode = node.getChildNodes().item(j);
	        String nodeName = channelNode.getNodeName();
	        
	        // item 노드들일 경우
	        if ("item".equals(nodeName)) {
	        	int lowPrice = 0;
	        	String productId = null;
	        	HashMap<String, Object> uItem = new HashMap<>();
	        	Chart cp = new Chart();
	        	
	        	// item 노드의 자식노드를 검색
	        	for (int k = 0; k < channelNode.getChildNodes().getLength(); k++) {
	            Node itemNode = channelNode.getChildNodes().item(k);
	            
	            // item 노드 안의 lprice 태그일 경우 실행
	            if ("lprice".equals(itemNode.getNodeName())) {
	            	lowPrice = Integer.parseInt(itemNode.getTextContent());
	            	uItem.put("lPrice", lowPrice);
	            	cp.setPrice(lowPrice);
	            }
	            
	            // item 노드 안의 productId 태그일 경우 실행
	            if ("productId".equals(itemNode.getNodeName())) {
	            	productId = itemNode.getTextContent();
	            	uItem.put("pId", productId);
	            	if (sItem.get(i).getpId().equals(productId)) {
	            		cp.setpId(sItem.get(i).getpId())
	            			.setTime(new Date(System.currentTimeMillis()));
	            	}
	            }
	            
	            // uItem 맵에 pid와 lprice가 다 들어가야 업뎃을 함.
	            if (uItem.size() == 2) {
		            itemDao.minUpdate(uItem);
		            uItem.remove("lPrice");
		            uItem.remove("pId");
	            }
	            
	            // 실시간으로 검색되는 시점의 시간과 해당 상품코드, 가격이 다 지정됐을 때만 hour_insert에 추가
	            if (cp.getpId() != null && cp.getPrice() != 0 
	            		&& cp.getTime() != null) {
	            	hourChartDao.insert(cp);
	            	cp.setTime(null)
	            		.setPrice(0)
	            		.setpId(null);
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
