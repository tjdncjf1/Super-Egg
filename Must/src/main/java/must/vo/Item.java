package must.vo;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable { 
  private static final long serialVersionUID = 1L; 

  protected String  pId;
  protected String  title;
	protected String  image;
	protected int 		min_price;
	protected int 		wish_price;
	protected String  link; 
	protected Date    reg_date;
	
	public String getpId() {
		return pId;
	}
	
	public Item setpId(String pId) {
		this.pId = pId;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Item setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getImage() {
		return image;
	}
	
	public Item setImage(String image) {
		this.image = image;
		return this;
	}
	
	public int getMin_price() {
		return min_price;
	}
	
	public Item setMin_price(int min_price) {
		this.min_price = min_price;
		return this;
	}
	
	public int getWish_price() {
		return wish_price;
	}
	
	public Item setWish_price(int wish_price) {
		this.wish_price = wish_price;
		return this;
	}
	
	public String getLink() {
		return link;
	}
	
	public Item setLink(String link) {
		this.link = link;
		return this;
	}
	
	public Date getReg_date() {
		return reg_date;
	}
	
	public Item setReg_date(Date reg_date) {
		this.reg_date = reg_date;
		return this;
	}
	
	
	
	
	
}
