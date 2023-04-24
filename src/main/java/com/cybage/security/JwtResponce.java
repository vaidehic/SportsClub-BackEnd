package com.cybage.security;

import java.util.ArrayList;
import java.util.List;

public class JwtResponce {
    private String username;
	private List<String> roles = new ArrayList<>();
	private String token;
	public JwtResponce() {
		super();
	}
	public JwtResponce(String username, List<String> roles, String token) {
		super();
		this.username = username;
		this.roles = roles;
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "JwtResponce [username=" + username + ", roles=" + roles + ", token=" + token + "]";
	}	
}