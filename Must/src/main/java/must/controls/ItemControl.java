package must.controls;

import javax.servlet.ServletContext;

import must.dao.ItemDao;
import must.vo.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/item") 
public class ItemControl {

	@Autowired
	ServletContext servletContext;

	@Autowired(required=false)
	ItemDao itemDao;

	@RequestMapping(value="addItem", method=RequestMethod.GET) 
	public void insert(Item item) throws Exception {
		itemDao.insert(item);
	}

}
