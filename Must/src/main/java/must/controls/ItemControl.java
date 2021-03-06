package must.controls;

import java.util.Date;
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
	
	@RequestMapping("/userItemDelete") 
	public void delete(int userNo, String pId) throws Exception {
		try {
			HashMap<String, Object> sqlparamMap = new HashMap<>();
			sqlparamMap.put("userNo", userNo);
			sqlparamMap.put("pId", pId);
			
			itemDao.delete(sqlparamMap);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping("/userItemAdd") 
	public void userItemAdd(int no, String pId, int wish_price, Date reg_date) throws Exception {
		try {
			HashMap<String, Object> itemInfo = new HashMap<>();
 			itemInfo.put("no", no);
 			itemInfo.put("pId", pId);
 			itemInfo.put("wPrice", wish_price);
 			itemInfo.put("rDate", reg_date);
			itemDao.userItemInsert(itemInfo);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value="/userItemList", produces="application/json")
	public Object selectItems(int uNo) throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(itemDao.userItemList(uNo));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/selectList", produces="application/json")
	public Object selectList() throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(itemDao.selectList());
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/userItemCheck", produces="application/json")
	public Object itemCheck(int userNo, String buyPid) throws Exception {
		try {
			
			HashMap<String, Object> sqlparam = new HashMap<>();
			sqlparam.put("userNo", userNo);
			sqlparam.put("buyPid", buyPid);
			
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(itemDao.userItemCheck(sqlparam));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/choiceUserItem", produces="application/json")
	public Object choiceUserItem(int userNo, String prodId) throws Exception {
		try {
			HashMap<String, Object> sqlparam = new HashMap<>();
			sqlparam.put("userNo", userNo);
			sqlparam.put("prodId", prodId);
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(itemDao.choiceUserItem(sqlparam));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
}
