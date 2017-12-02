package me.acgee.manage.module.permission.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
@Entity
@Table(name="admin")
public class Admin{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="adminId",length=11)
	private Integer adminId;
	@Column(name="adminName",length=20)
	private String adminName;
	@Column(name="name",length=20)
	private String name;
	@Column(name="password",length=100)
	private String password;
	@Column(name="mobileNum",length=11)
	private String mobileNum;
	@Column(name="lastPasswordResetDate")
	private Date lastPasswordResetDate;
	@Column(name="createdDate")
	private Date createdDate;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="admin_role",
			joinColumns=@JoinColumn(name="adminId"),
			inverseJoinColumns=@JoinColumn(name="roleId"))
    private Set<Role> role;


	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}
	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	public Set<Role> getRole() {
		return role;
	}
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
}
