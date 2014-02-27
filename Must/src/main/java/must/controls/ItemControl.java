package must.controls;

import must.dao.ItemDao;
import must.vo.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item") 
public class ItemControl {

	@Autowired(required=false)
	ItemDao itemDao;

	@RequestMapping("/addItem") 
	public void insert(Item item) throws Exception {
		try {
			itemDao.insert(item);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

}
