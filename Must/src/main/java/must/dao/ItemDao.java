package must.dao;

import java.util.List;
import java.util.Map;

import must.vo.Item;

public interface ItemDao {
	
	public List<Item> selectList() throws Exception;
	public void insert(Item item) throws Exception;
	public void userItemInsert(Item item) throws Exception;
	public void delete(String pId) throws Exception;
	public void minUpdate(Map<String, Object> sqlparamMap) throws Exception;
	public void wishUpdate(Map<String, Object> sqlparamMap) throws Exception;
	

}

