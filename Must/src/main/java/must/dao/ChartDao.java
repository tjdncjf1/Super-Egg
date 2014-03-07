package must.dao;

import java.util.List;

import must.vo.Chart;

public interface ChartDao {
	
	public void insert(Chart cp) throws Exception;
	public List<Chart> cItem(String pId) throws Exception;
	
}

