package main.java.model;

import java.util.Date;

public class User {

	private String name;
	private Date loginTime;

	public User(String name, Date loginTime) {
		super();
		this.name = name;
		this.loginTime = loginTime;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
}
