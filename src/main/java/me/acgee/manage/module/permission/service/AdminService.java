package me.acgee.manage.module.permission.service;

import me.acgee.manage.module.permission.entity.Admin;


public interface AdminService {
	public Admin findByAdminName(String adminName);
}
