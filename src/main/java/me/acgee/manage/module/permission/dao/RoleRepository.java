package me.acgee.manage.module.permission.dao;

import me.acgee.manage.module.permission.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
