package me.acgee.manage.module.permission.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name="role")
public class Role{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleId",length=11)
	private Integer roleId;
	@Column(name="roleName",length=30)
	private String roleName;
	@Column(name="roleDesc",length=20)
	private String roleDesc;
	@ManyToMany(mappedBy="role")
	@JSONField(serialize=false)
	private Set<Admin> admin;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Role(Integer roleId) {
		super();
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<Admin> getAdmin() {
		return admin;
	}
	public void setAdmin(Set<Admin> admin) {
		this.admin = admin;
	}
	
}
