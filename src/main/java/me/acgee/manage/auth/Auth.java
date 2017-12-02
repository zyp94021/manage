package me.acgee.manage.auth;

import me.acgee.manage.module.permission.entity.Admin;

import java.io.Serializable;



public class Auth implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8134871253004880270L;
	private String token;
    private Admin admin;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Auth(String token, Admin admin) {
		super();
		this.token = token;
		this.admin = admin;
	}
    
}
