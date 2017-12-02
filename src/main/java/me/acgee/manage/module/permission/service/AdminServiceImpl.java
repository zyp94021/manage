package me.acgee.manage.module.permission.service;

import me.acgee.manage.module.permission.dao.AdminRepository;
import me.acgee.manage.module.permission.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin findByAdminName(String adminName) {
		// TODO Auto-generated method stub
		return adminRepository.findByAdminName(adminName);
	}


}
