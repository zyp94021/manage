package me.acgee.manage.utils;

import me.acgee.manage.module.permission.entity.Admin;
import me.acgee.manage.secruity.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class DetailUtil {
	public static Admin getAdmin(){
		JwtUser jwtUser=(JwtUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return jwtUser.getAdmin(); 
	}
	
}
