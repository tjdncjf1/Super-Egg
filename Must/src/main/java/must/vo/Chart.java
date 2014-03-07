package must.vo;

import java.io.Serializable;
import java.util.Date;

public class Chart implements Serializable{
  private static final long serialVersionUID = 1L;

  protected int no;
  protected String pId;
  protected int nprice;
  protected Date cdate;
  
	public int getNo() {
		return no;
	}
	
	public Chart setNo(int no) {
		this.no = no;
		return this;
	}
	
	public String getpId() {
		return pId;
	}
	
	public Chart setpId(String pId) {
		this.pId = pId;
		return this;
	}
	
	public int getNprice() {
		return nprice;
	}
	
	public Chart setNprice(int nprice) {
		this.nprice = nprice;
		return this;
	}
	
	public Date getCdate() {
		return cdate;
	}
	
	public Chart setCdate(Date cdate) {
		this.cdate = cdate;
		return this;
	}
   
  
  
  
}
