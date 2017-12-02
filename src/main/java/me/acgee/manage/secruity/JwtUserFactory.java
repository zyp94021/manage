package me.acgee.manage.secruity;

import me.acgee.manage.module.permission.entity.Admin;
import me.acgee.manage.module.permission.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Admin admin) {
        return new JwtUser(
        		admin.getAdminId(),
                admin.getAdminName(),
                admin.getPassword(),
                mapToGrantedAuthorities(admin.getRole()),
                admin.getLastPasswordResetDate(),
                admin
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> authoritie) {
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : authoritie) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			//System.out.println(serviceAdminRole.getRoleName());
		}
		return authorities;
    }
}

