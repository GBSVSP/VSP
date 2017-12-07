package com.vsp.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.ibm.swat.password.cwa2;
import com.vsp.dao.LoginDAO;
import com.vsp.util.Constants;
import com.vsp.util.SessionUtils;

/**
 * <p>
 * This the bean class for login
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 10/Nov/2017
 */


@ManagedBean(name="login")
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	//Specify the property file name 
	public final static ResourceBundle bundle = ResourceBundle.getBundle("com.vsp.util.vspMessages");
	
	private String password;
	private String userName;
	private int roleId;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String validateUser() throws Exception {
		
		 String page="index";
		 String message = null;
		 if (userName!=null && password !=null) {
				cwa2 group = new cwa2();
				//ReturnCode rc = group.authenticate(userName, password);
				//int code = rc.getCode();
				int code = 0;
				if ( code != 0) {

					FacesContext.getCurrentInstance().addMessage("Login:validationMsg", 
							new FacesMessage(FacesMessage.SEVERITY_WARN,bundle.getString(Constants.LOGIN_INVALID),
									bundle.getString(Constants.LOGIN_INVALID)));

				} else {
					 System.out.println("Login Successfull. Thanks.");
					 int roleId = LoginDAO.validate(userName, password);
					 System.out.println("RoleID of session user: "+roleId);
					 if (roleId > 0) {
						 SessionUtils.setUserName(userName);
						 SessionUtils.setRoleId(String.valueOf(roleId));
						 setRoleId(roleId);
					/* if(roleId==1) {
						page= "admin";
					 }
					 else if(roleId==2) {
						 page= "user";
					 }
					 */
						 page ="admin";
					 }
					 else {

							FacesContext.getCurrentInstance().addMessage("Login:validationMsg", 
									new FacesMessage(FacesMessage.SEVERITY_WARN,bundle.getString(Constants.LOGIN_ACCESS_DENIED),
											bundle.getString(Constants.LOGIN_ACCESS_DENIED)));

							page="index";
						}
					
				} 
			}
		 
		
		 return page;
		
	}

	//logout event, invalidate session
	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
     	context.getExternalContext().invalidateSession();
         try {
 context.getExternalContext().redirect("index.xhtml");
 } catch (IOException e) {
 e.printStackTrace();
 }
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
