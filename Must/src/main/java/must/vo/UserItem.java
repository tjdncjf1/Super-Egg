package must.vo;

import java.io.Serializable;
import java.util.Date;

public class UserItem implements Serializable {
  private static final long serialVersionUID = 1L;

  protected int no;
  protected String pId;
  protected int wish_price;
  protected Date reg_date;
  
	public int getNo() {
		return no;
	}
	
	public UserItem setNo(int no) {
		this.no = no;
		return this;
	}
	
	public String getpId() {
		return pId;
	}
	
	public UserItem setpId(String pId) {
		this.pId = pId;
		return this;
	}
	
	public int getWish_price() {
		return wish_price;
	}
	
	public UserItem setWish_price(int wish_price) {
		this.wish_price = wish_price;
		return this;
	}
	
	public Date getReg_date() {
		return reg_date;
	}
	
	public UserItem setReg_date(Date reg_date) {
		this.reg_date = reg_date;
		return this;
	}
  
}
