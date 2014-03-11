package must.dao;

import java.util.List;

import must.vo.Chart;

public interface HourChartDao {
	
	public void insert(Chart cp) throws Exception;
	public void delete(String pId) throws Exception;
	public List<Chart> select(String pId) throws Exception;
	
}

