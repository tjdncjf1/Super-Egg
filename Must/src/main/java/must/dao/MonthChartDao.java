package must.dao;

import java.util.List;

import must.vo.Chart;

public interface MonthChartDao {
	
	public void insert(Chart cp) throws Exception;
	public void delete(int no) throws Exception;
	public List<Chart> selectList(String pId) throws Exception;
	
}

