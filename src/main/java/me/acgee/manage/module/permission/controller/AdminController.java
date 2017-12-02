package me.acgee.manage.module.permission.controller;

import me.acgee.manage.module.permission.entity.Admin;
import me.acgee.manage.module.permission.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getInfo")
	public Admin getInfo(){
		String adminName=SecurityContextHolder.getContext().getAuthentication().getName();
		Admin admin=adminService.findByAdminName(adminName);
		return admin;
	}

}
