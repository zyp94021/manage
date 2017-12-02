package me.acgee.manage.secruity;

import com.alibaba.fastjson.annotation.JSONField;
import me.acgee.manage.module.permission.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

public class JwtUser implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3840534968070077910L;
	private  Integer id;
    private  String username;
    private  String password;
    private  List<GrantedAuthority> authorities;
    private  Date lastPasswordResetDate;
    private Admin admin;

	public JwtUser(
    		Integer id,
            String username,
            String password,
            List<GrantedAuthority> authorities,
            Date lastPasswordResetDate,
            Admin admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.admin=admin;
    }
	
	public JwtUser() {
		super();
	}
    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JSONField(serialize=false)
    public Integer getId() {
        return id;
    }
    
    @JSONField(serialize=false)
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JSONField(serialize=false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize=false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize=false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JSONField(serialize=false)
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @JSONField(serialize=false)
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
    
    @JSONField(serialize=false)
	public Admin getAdmin() {
		return admin;
	}

}
