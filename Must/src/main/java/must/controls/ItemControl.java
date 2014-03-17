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

	@RequestMapping("/wishUpdate") 
	public void wishUpdate(String pId, int wish_price) throws Exception {
		try {
			HashMap<String, Object> sqlparamMap = new HashMap<String, Object>();
			sqlparamMap.put("pId", pId);
			sqlparamMap.put("wish_price", wish_price);
			itemDao.wishUpdate(sqlparamMap);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping("/addItem") 
	public void insert(Item item) throws Exception {
		try {
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
	
	@RequestMapping("/userItemAdd") 
	public void userItemAdd(Item item) throws Exception {
		try {
			itemDao.userItemInsert(item);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value="/userItemSelect", produces="application/json")
	public Object selectItem(String pId) throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(itemDao.selectItem(pId));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
}
