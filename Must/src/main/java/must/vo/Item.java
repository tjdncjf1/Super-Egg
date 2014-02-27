package must.vo;

import java.io.Serializable;

public class Item implements Serializable { 
  private static final long serialVersionUID = 1L; 

  protected String  title;
	protected String  image;
	protected int 		min_price;
	protected String  link; 
	protected int     pId;
	
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
	
	public String getLink() {
		return link;
	}
	
	public Item setLink(String link) {
		this.link = link;
		return this;
	}
	
	public int getpId() {
		return pId;
	}
	
	public Item setpId(int pId) {
		this.pId = pId;
		return this;
	}
	
	
	
	
}
