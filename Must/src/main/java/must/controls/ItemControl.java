package must.controls;

import must.dao.ItemDao;
import must.vo.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import must.vo.JsonResult;

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
	
	@RequestMapping(value="/list", produces="application/json")
	public Object list() throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(itemDao.selectList());
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
	

}
