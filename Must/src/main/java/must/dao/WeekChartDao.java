package must.dao;

import java.util.List;

import must.vo.Chart;

public interface WeekChartDao {
	
	public void insert(Chart cp) throws Exception;
	public void delete(String pId) throws Exception;
	public List<Chart> selectList(String pId) throws Exception;
	
}

