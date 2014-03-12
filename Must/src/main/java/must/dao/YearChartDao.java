package must.dao;

import java.util.List;

import must.vo.Chart;

public interface YearChartDao {
	
	public void insert(Chart cp) throws Exception;
	public List<Chart> selectList(String pId) throws Exception;
	
}

