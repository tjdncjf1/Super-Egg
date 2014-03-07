package must.dao;

import java.util.List;
import java.util.Map;

import must.vo.Item;

public interface ItemDao {
	
	public List<Item> selectList() throws Exception;
	public void insert(Item item) throws Exception;
	public void delete(String pId) throws Exception;
	public void update(Map<String, Object> sqlparamMap) throws Exception;

}

