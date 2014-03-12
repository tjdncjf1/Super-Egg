package must.controls;

import must.dao.DayChartDao;
import must.vo.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart") 
public class ChartControl {

	@Autowired(required=false)
	DayChartDao dayChartDao;

	@RequestMapping(value="/selectDay", produces="application/json")
	public Object list(String pId) throws Exception {
		try {
			return new JsonResult().setResultStatus(JsonResult.SUCCESS)
					.setData(dayChartDao.selectList(pId));
		} catch (Throwable ex) {
			return new JsonResult()
			.setResultStatus(JsonResult.FAILURE)
			.setError(ex.getMessage());
		}
	}
	
	

}
