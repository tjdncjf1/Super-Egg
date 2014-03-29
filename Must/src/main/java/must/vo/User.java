package must.vo;

import java.io.Serializable;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;



	protected int no;
  protected String email;
  protected String password;
  protected UserItem itemList;
  protected String regId;
  
	public int getNo() {
		return no;
	}
	
	public User setNo(int no) {
		this.no = no;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserItem getItemList() {
		return itemList;
	}

	public User setItemList(UserItem itemList) {
		this.itemList = itemList;
		return this;
	}
	
  public String getRegId() {
		return regId;
	}

	public User setRegId(String regId) {
		this.regId = regId;
		return this;
	}
  
  
  
}
