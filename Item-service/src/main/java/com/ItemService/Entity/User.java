package com.ItemService.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String email;
  private String password;
  private String Roles;
  
  
  public User() {
	super();
}

public User(Long id, String username, String email, String password, String roles) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password=password;
//	 PasswordEncoder encoder = new BCryptPasswordEncoder();
//	 String encodedStr = password = encoder.encode(password);
	Roles = roles;
  }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRoles() {
	return Roles;
}

public void setRoles(String roles) {
	Roles = roles;
}

@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", Roles="
			+ Roles + "]";
}
  

  

  
}