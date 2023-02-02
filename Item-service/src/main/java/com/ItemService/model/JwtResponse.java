package com.ItemService.model;


import java.io.Serializable;

import com.ItemService.Entity.User;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

	public void setUser(User userData) {
		// TODO Auto-generated method stub
		
	}
}
