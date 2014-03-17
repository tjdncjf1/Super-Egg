package must.vo;

import java.io.Serializable;
import java.util.Date;

public class UserItem implements Serializable {
  private static final long serialVersionUID = 1L;

	protected int userNo;
	protected String productId;
	protected int wPrice;
	protected Date rDate;
	protected Item itemInfo;
	
	public int getUserNo() {
		return userNo;
	}
	
	public UserItem setUserNo(int userNo) {
		this.userNo = userNo;
		return this;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public UserItem setProductId(String productId) {
		this.productId = productId;
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
	
	public Item getItemInfo() {
		return itemInfo;
	}
	
	public UserItem setItemInfo(Item itemInfo) {
		this.itemInfo = itemInfo;
		return this;
	}
	
	
	
}
