package com.vsp.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * <p>
 * This the bean class for user information
 *
 * </p>
 * 
 * @author Amrita Sahu (amritasahu@in.ibm.com)
 * @version 1.0
 * @Date 29/Nov/2017
 */
@ManagedBean(name = "userInfo")
@SessionScoped
public class UserInfo implements Serializable {
	private static final long serialVersionUID = -7193581484504873684L;
	private int user_Id;
	private int serialNo;
	private String user_Name;
	private String email;
	private String role_Id;
	private String imt_Id;
	private boolean active;
	private String created_By;
	private String modified_By;
	private boolean is_Delete;
	private boolean checkboxClickedFlag;
	
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole_Id() {
		return role_Id;
	}
	public void setRole_Id(String role_Id) {
		this.role_Id = role_Id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCreated_By() {
		return created_By;
	}
	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}
	public String getModified_By() {
		return modified_By;
	}
	public void setModified_By(String modified_By) {
		this.modified_By = modified_By;
	}
	public boolean isIs_Delete() {
		return is_Delete;
	}
	public void setIs_Delete(boolean is_Delete) {
		this.is_Delete = is_Delete;
	}
	public String getImt_Id() {
		return imt_Id;
	}
	public void setImt_Id(String imt_Id) {
		this.imt_Id = imt_Id;
	}
	public boolean isCheckboxClickedFlag() {
		return checkboxClickedFlag;
	}
	public void setCheckboxClickedFlag(boolean checkboxClickedFlag) {
		this.checkboxClickedFlag = checkboxClickedFlag;
	}


}
