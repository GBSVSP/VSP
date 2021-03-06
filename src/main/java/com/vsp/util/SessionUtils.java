package com.vsp.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * <p>
 * This the session class for VSP
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 10/Nov/2017
 */
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

		return getSession().getAttribute("userName").toString();
	}
	public static void setUserName(String userName) {
		getSession().setAttribute("userName",userName);
	}
	
	public static int getRoleId() {
		HttpSession session = getSession();
		if (session != null)
			return (Integer.parseInt((String)session.getAttribute("roleId")));
		else
			return 0;
	}
	public static String getSerialNo() {
		HttpSession session = getSession();
		if (session != null)
			return (String) (session.getAttribute("serialNo"));
		else
			return null;
	}
	public static String getFirstName() {
		HttpSession session = getSession();
		if (session != null)
			return (String) (session.getAttribute("firstName"));
		else
			return null;
	}
	public static void setRoleId(String roleId) {

		getSession().setAttribute("roleId",roleId);
	}
	public static void setSerialNo(String serialNo) {

		getSession().setAttribute("serialNo",serialNo);
	}
	public static void setFirstName(String serialNo) {

		getSession().setAttribute("firstName",serialNo);
	}
	
}
