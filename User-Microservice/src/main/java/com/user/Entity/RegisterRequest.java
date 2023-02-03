package com.user.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String name;
    private String password;
    private String emailId;
    private boolean authorUser;
	public RegisterRequest(String name, String password, String emailId, boolean authorUser) {
		super();
		this.name = name;
		this.password = password;
		this.emailId = emailId;
		this.authorUser = authorUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public boolean isAuthorUser() {
		return authorUser;
	}
	public void setAuthorUser(boolean authorUser) {
		this.authorUser = authorUser;
	}
	@Override
	public String toString() {
		return "RegisterRequest [name=" + name + ", password=" + password + ", emailId=" + emailId + ", authorUser="
				+ authorUser + "]";
	}
    
}