package spms.vo;

public class JsonResult {
	public static final int SUCCESS = 0;
	public static final int FAILURE = 1;

	protected int 			resultStatus;
	protected Object		data;
	protected String		error;

	public int getResultStatus() {
		return resultStatus;
	}

	public JsonResult setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
		return this;
	}

	public Object getData() {
		return data;
	}

	public JsonResult setData(Object data) {
		this.data = data;
		return this;
	}

	public String getError() {
		return error;
	}

	public JsonResult setError(String error) {
		this.error = error;
		return this;
	}

}
