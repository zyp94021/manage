package me.acgee.manage.module.permission.dao;

import me.acgee.manage.module.permission.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>,JpaSpecificationExecutor<Admin>{
	public Admin findByAdminName(String adminName);
	
}
