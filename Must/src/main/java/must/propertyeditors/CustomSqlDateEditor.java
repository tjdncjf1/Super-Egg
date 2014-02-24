package must.propertyeditors;

import java.beans.PropertyEditorSupport;

public class CustomSqlDateEditor extends PropertyEditorSupport {

	// 파라미터 문자열을 줌
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// project에서 setStartDate, setEndDate로 넘겨줄 값. 문자열을 sql.date문으로 변환
		this.setValue(java.sql.Date.valueOf(text));
	}
	
}
