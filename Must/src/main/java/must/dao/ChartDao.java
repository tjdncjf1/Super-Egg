package must.dao;

import java.util.List;

import must.vo.Chart;

public interface ChartDao {
	
	public void hour_insert(Chart cp) throws Exception;
	public void hour_delete(Chart cp) throws Exception;
	public void day_insert(Chart cp) throws Exception;
	public List<Chart> hour_select(String pId) throws Exception;
	
}

