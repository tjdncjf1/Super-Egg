package must.search;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NaverParse {

	public static String getContent(Element element, String tagName) {
		NodeList list = element.getElementsByTagName(tagName);
		Element cElement = (Element)list.item(0);
		
		if(cElement.getFirstChild() != null) {
			return cElement.getFirstChild().getNodeValue();
		} else {
			return "";
		}
	}
	
	public void parse (String url) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(url);
			Element root = doc.getDocumentElement();
			NodeList list = root.getElementsByTagName("channel");
			
			for (int i = 0; i < list.getLength(); i++) {
	      Element element = (Element)list.item(i);
//	      System.out.println("아이템 이름" + getContent(element, "title"));
	      System.out.println("상품코드" + getContent(element, "productId"));
	      System.out.println("최저가" + getContent(element, "lprice"));
      }
    }
		catch (ParserConfigurationException e) {e.printStackTrace();}
		catch (SAXException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		
	}
	
	
}
