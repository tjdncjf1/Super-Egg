package must.vo;

import java.io.Serializable;
import java.util.Date;

public class UserItem implements Serializable {
  private static final long serialVersionUID = 1L;

	protected int userNo;
	protected String pId;
	protected int wPrice;
	protected Date rDate;
	
	public int getUserNo() {
		return userNo;
	}
	
	public UserItem setUserNo(int userNo) {
		this.userNo = userNo;
		return this;
	}
	
	public String getpId() {
		return pId;
	}
	
	public UserItem setpId(String pId) {
		this.pId = pId;
		return this;
	}
	
	public int getwPrice() {
		return wPrice;
	}
	
	public UserItem setwPrice(int wPrice) {
		this.wPrice = wPrice;
		return this;
	}
	
	public Date getrDate() {
		return rDate;
	}
	
	public UserItem setrDate(Date rDate) {
		this.rDate = rDate;
		return this;
	}
	
}
