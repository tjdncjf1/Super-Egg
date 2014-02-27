package must.dao;

import java.util.List;

import must.vo.Item;

public interface ItemDao {
	
	public List<Item> selectList() throws Exception;
	public void insert(Item item) throws Exception;





}







