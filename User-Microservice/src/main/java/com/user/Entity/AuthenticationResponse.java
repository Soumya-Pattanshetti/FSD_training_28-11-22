package com.user.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AuthenticationResponse {
    private String token;
    private Long DealerId;
    private String firstName;
    private String lastName;
    private String emailId;
    private Role role;
	public AuthenticationResponse(String token, Long dealerId, String firstName, String lastName, String emailId,
			Role role) {
		super();
		this.token = token;
		DealerId = dealerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getDealerId() {
		return DealerId;
	}
	public void setDealerId(Long dealerId) {
		DealerId = dealerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
	
}

