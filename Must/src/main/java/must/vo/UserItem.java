package must.vo;

import java.io.Serializable;

public class UserItem implements Serializable {
  private static final long serialVersionUID = 1L;

	protected User user;
	protected Item item;
	
	public User getUser() {
		return user;
	}
	
	public UserItem setUser(User user) {
		this.user = user;
		return this;
	}
	
	public Item getItem() {
		return item;
	}
	
	public UserItem setItem(Item item) {
		this.item = item;
		return this;
	}
	
}
