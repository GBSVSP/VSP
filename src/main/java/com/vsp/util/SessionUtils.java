package com.vsp.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		 response.setContentType("application/vnd.ms-excel");
		 return response;
	}
	public static String getServletContext() {
		return (String) FacesContext
			    .getCurrentInstance().getExternalContext().getRealPath("//");
		
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}
	public static void setUserName(String userName) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.setAttribute("username",userName);
	}
	
	public static int getRoleId() {
		HttpSession session = getSession();
		if (session != null)
			return (Integer.parseInt((String)session.getAttribute("roleid")));
		else
			return 0;
	}
	
	
	
}
