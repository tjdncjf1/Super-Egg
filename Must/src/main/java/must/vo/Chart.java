package must.vo;

import java.io.Serializable;
import java.util.Date;

public class Chart implements Serializable{
  private static final long serialVersionUID = 1L;

  protected String pId;
  protected int price;
  protected Date time;
  
	public String getpId() {
		return pId;
	}
	
	public Chart setpId(String pId) {
		this.pId = pId;
		return this;
	}
	
	public int getPrice() {
		return price;
	}
	
	public Chart setPrice(int price) {
		this.price = price;
		return this;
	}
	
	public Date getTime() {
		return time;
	}
	
	public Chart setTime(Date time) {
		this.time = time;
		return this;
	}
   
  
  
  
}
