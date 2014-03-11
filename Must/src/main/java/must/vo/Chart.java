package must.vo;

import java.io.Serializable;
import java.util.Date;

public class Chart implements Serializable{
  private static final long serialVersionUID = 1L;

  protected String pId;
  protected int hPrice;
  protected Date hTime;
  
	public String getpId() {
		return pId;
	}
	
	public Chart setpId(String pId) {
		this.pId = pId;
		return this;
	}
	
	public int getHprice() {
		return hPrice;
	}
	
	public Chart setHprice(int hPrice) {
		this.hPrice = hPrice;
		return this;
	}
	
	public Date getHtime() {
		return hTime;
	}
	
	public Chart setHtime(Date hTime) {
		this.hTime = hTime;
		return this;
	}
   
  
  
  
}
