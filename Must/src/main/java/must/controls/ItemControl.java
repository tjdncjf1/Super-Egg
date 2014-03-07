package must.controls;

import java.util.HashMap;

import must.dao.ItemDao;
import must.vo.Item;
import must.vo.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item") 
public class ItemControl {

	@Autowired(required=false)
	ItemDao itemDao;

	@RequestMapping("/update") 
	public void update(String pId, int wish_price) throws Exception {
		try {
			HashMap<String, Object> sqlparamMap = new HashMap<String, Object>();
			sqlparamMap.put("pId", pId);
			sqlparamMap.put("wish_price", wish_price);
			itemDao.update(sqlparamMap);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping("/addItem") 
	public void insert(Item item) throws Exception {
		try {
//			String cTitle = item.getTitle();
			
			
			itemDao.insert(item);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping("/delete") 
	public void delete(String pId) throws Exception {
		try {
			itemDao.delete(pId);
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
