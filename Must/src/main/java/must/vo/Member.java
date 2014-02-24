package must.vo;

import java.io.Serializable;

public class Member implements Serializable { 
  private static final long serialVersionUID = 1L; 

  protected int 		no;
	protected String  name;
	protected int 		age;
	protected String  email; 
	protected String  tel;
	protected String  password;
	protected String 	photo;	
	
	public String getName() {
		return name;
	}
	
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	
	public int getAge() {
		return age;
	}
	
	public Member setAge(int age) {
		if (age > 7 && age < 101) {
			this.age = age;
		} else {
			this.age = 20;
		}
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getTel() {
		return tel;
	}
	
	public Member setTel(String tel) {
		this.tel = tel;
		return this;
	}
	public int getNo() {
		return no;
	}
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getPhoto() {
		return photo;
	}

	public Member setPhoto(String photo) {
		this.photo = photo;
		return this;
	}
	
	
	
}
