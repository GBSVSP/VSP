package com.vsp.model;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ibm.swat.password.ReturnCode;
import com.ibm.swat.password.cwa2;

import com.vsp.dao.LoginDAO;
import com.vsp.util.SessionUtils;

@ManagedBean(name="login")
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String password;
	private String userName;
	
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
				ReturnCode rc = group.authenticate(userName, password);
				//int code = rc.getCode();
				int code = 0;
				if ( code != 0) {

					message = "User Name/Password invalid. Please, try again.";
					FacesContext.getCurrentInstance().addMessage(
							"Login:validationMsg",
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									message,
									message));

				} else {
					System.out.println("Login Successfull. Thanks.");
					 int roleId = LoginDAO.validate(userName, password);
					 if (roleId > 0) {
						 SessionUtils.setUserName(userName);
					 if(roleId==1) {
						page= "admin";
					 }
					 else if(roleId==2) {
						 page= "user";
					 }
					 }
					 else {
						 message = "VSP access denied. Please contact Administrator...";
							FacesContext.getCurrentInstance().addMessage(
									"Login:validationMsg",
									new FacesMessage(FacesMessage.SEVERITY_WARN,
											message,
											message));
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
}
