package com.vsp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import com.vsp.dao.UserMgmtDAO;
import com.vsp.util.Constants;
import com.vsp.util.QueryBuilder;

/**
 * <p>
 * This the bean class for user management
 *
 * </p>
 * 
 * @author Amrita Sahu (amrita.sahu@in.ibm.com)
 * @version 1.0
 * @Date 29/Nov/2017
 */
@ManagedBean(name = "user")
//@SessionScoped
@ViewScoped
public class User implements Serializable {

	private static final long serialVersionUID = 5199551491746665622L;

	// Specify the property file name for resource bundle
	public final static ResourceBundle bundle = ResourceBundle.getBundle("com.vsp.util.vspMessages");
	private Map<String, Object> sessionMap;
	
	private static ArrayList<UserInfo> userInfoList;

	private int totalSize = 0;
	private boolean toggleButton = false;
	private boolean checkboxAllFlag =false;
	private UserInfo userInfo;
	private String whereClause = null;
	private String orderBy = null;
	private static String sql = null;
	private static HashMap<Integer, String> optionMap;
	public String toggleBtnChgValue = Constants.NEW_USER_BUTTON;
	private String jscript = "";
	private String msgAppend = null;
    
	// Populating Role drop down
	public ArrayList<SelectItem> getRoleList() {
		FacesContext context  = FacesContext.getCurrentInstance();
		sessionMap = context.getExternalContext().getSessionMap();
		ArrayList<SelectItem> roleList = new ArrayList<SelectItem>();
		
		if(sessionMap.containsKey("roleListInSession")) {
			roleList = (ArrayList<SelectItem>) sessionMap.get("roleListInSession");
			
		}else {	
		whereClause = Constants.IS_ACTIVE_CONDITION;
		sql = QueryBuilder.SELECT_USER_ROLE + " " + whereClause;
		
		try {
			optionMap = UserMgmtDAO.getRoleList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				roleList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
			sessionMap.put("roleListInSession", roleList);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		}
		return roleList;
	}

	// Populating IMT drop down
	public ArrayList<SelectItem> getImtList() {
		FacesContext context  = FacesContext.getCurrentInstance();
		sessionMap = context.getExternalContext().getSessionMap();
		ArrayList<SelectItem> imtList = new ArrayList<SelectItem>();
		
		if(sessionMap.containsKey("imtListInSession")) {
			imtList = (ArrayList<SelectItem>) sessionMap.get("imtListInSession");
		}else {	
		   sql = QueryBuilder.SELECT_IMT;
	
	 	   try {
			optionMap = UserMgmtDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				imtList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
			
			sessionMap.put("imtListInSession", imtList);
			
		  } catch (Exception ex) {
			System.out.println(ex.getMessage());
		  }
		
		}
		return imtList;
	}

	
	/**
	 * Method to add a new row
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void addNewRow() throws Exception {

		System.out.println("In User: Entering addNewRow()...");
		
		if(getToggleBtnChgValue().equals(Constants.SAVE_USER_BUTTON)) {
			for(UserInfo userInfo: userInfoList) {
				userInfo.setCheckboxClickedFlag(false);
			}
			userInfo = new UserInfo();
			userInfo.setCheckboxClickedFlag(true);
			userInfo.setActive(true);
			userInfoList.add(userInfo);
			System.out.println("size of userInfoList in addNewRow: " + userInfoList.size());

			setToggleButton(true);
			
		}else if(getToggleBtnChgValue().equals(Constants.NEW_USER_BUTTON)){
			//save clicked
			insertUser();
			setToggleButton(false);
		}

		System.out.println("In User: Exiting addNewRow()...");
	}

	/**
	 * Method to retrieve entire user list from db2 table
	 * 
	 * @return ArrayList<UserInfo>
	 * @throws Exception
	 */

	public ArrayList<UserInfo> getAllUserList() throws Exception {
		System.out.println("In User: Entering getAllUserList()...");
		try {
			System.out.println("toggleButton :" + toggleButton);

			//if (isToggleButton() == false) {
			if(!getToggleBtnChgValue().equals(Constants.SAVE_USER_BUTTON)){
				orderBy = "order by "+ Constants.USER_NAME;
				whereClause = Constants.DELETE_CONDITION;
				sql = QueryBuilder.SELECT_USER_INFO + " " + whereClause +" "+orderBy;

				userInfoList = UserMgmtDAO.getAllUserList(sql);
				totalSize = userInfoList.size();
				setToggleButton(false);
				System.out.println("Size of userInfoList : " + userInfoList.size());
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("In User: Exiting getAllUserList()...");
		return userInfoList;
	}

	
	/**
	 * Method to add a new user to db2 table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void insertUser() throws Exception {

		System.out.println("In User: Entering insertUser()... ");

		for (UserInfo userInfoTemp : userInfoList) {
			if (userInfoTemp.isCheckboxClickedFlag() == true) {
				System.out.println("trying to insert");
				userInfoTemp.setCheckboxClickedFlag(false);

				userInfo = userInfoTemp;
				break;
			}
		}

		// Check if user already exist
		boolean existFlag = checkUserExist(userInfo.getEmail());

		if (existFlag == false) {

			try {
				sql = QueryBuilder.INSERT_USER_INFO;

				int insertFlag = UserMgmtDAO.insertUser(sql, userInfo);

				if (insertFlag > 0) {
					System.out.println("User insert successful.");
					/*FacesContext.getCurrentInstance().addMessage("validationMsg",
							new FacesMessage(FacesMessage.SEVERITY_INFO, userInfo.getUser_Name() +" "+ bundle.getString(Constants.USER_ADDED_SUCCESS),
									userInfo.getUser_Name()+" "+ bundle.getString(Constants.USER_ADDED_SUCCESS)));*/
					
					setMsgAppend(userInfo.getUser_Name());
					jscript= "addNewInfo";
	
				} else {
					System.out.println("User insert unsuccessful.");
					/*FacesContext.getCurrentInstance().addMessage("validationMsg",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(Constants.DATABASE_ERROR),
									bundle.getString(Constants.DATABASE_ERROR)));*/
					jscript ="error";
				}

			} catch (Exception e) {
				System.out.println("Error in insertUser():" + e);
				e.printStackTrace();
			}

		} else {
			// error msg on UI
			/*FacesContext.getCurrentInstance().addMessage("validationMsg",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							userInfo.getEmail() + " " + bundle.getString(Constants.USER_ALREADY_EXIST),
							userInfo.getEmail() + " " + bundle.getString(Constants.USER_ALREADY_EXIST)));*/
			
			setMsgAppend(userInfo.getEmail());
			jscript= "addExistInfo";
		}

		System.out.println("In User: Exiting insertUser()...");

	}

	/**
	 * Method to check an existing user in USERS table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public boolean checkUserExist(String email) throws Exception {
		System.out.println("In User: Entering checkUserExist()...");
		boolean existDealFlag = false;
		try {

			whereClause = Constants.EMAIL_ADDRESS;
			String sql = QueryBuilder.COUNT_USER + " " + whereClause;

			existDealFlag = UserMgmtDAO.checkUserExist(sql, email);
		} catch (Exception e) {
			System.out.println("Error in checkUserExist():" + e);
			e.printStackTrace();
		}
		System.out.println("In User: Exiting checkUserExist()...");

		return existDealFlag;
	}

	/**
	 * Method to update an existing user into db2 table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void updateUser() throws Exception {

		System.out.println("In User: Entering updateUser()...");
		List<UserInfo> updateList = new ArrayList<UserInfo>();

		try {

			for (UserInfo userInfo : userInfoList) {
				if (userInfo.isCheckboxClickedFlag() == true) {
					updateList.add(userInfo);
				}
			}
			whereClause = Constants.USER_ID;
			sql = QueryBuilder.UPDATE_USER_INFO + " " + whereClause;

			int updateFlag = UserMgmtDAO.updateUser(sql, updateList);

			if (updateFlag > 0) {
				System.out.println("User update successful.");
				/*FacesContext.getCurrentInstance().addMessage("validationMsg",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								updateList.size() + " " + bundle.getString(Constants.USER_UPDATE_SUCCESS),
								updateList.size() + " " + bundle.getString(Constants.USER_UPDATE_SUCCESS)));*/
				
				setMsgAppend(String.valueOf(updateList.size()));
				jscript= "updateInfo";
				
			} else {
				System.out.println("User update unsuccessful.");
				/*FacesContext.getCurrentInstance().addMessage("validationMsg",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(Constants.DATABASE_ERROR),
								bundle.getString(Constants.DATABASE_ERROR)));*/
				jscript= "error";
			}

		} catch (Exception e) {
			System.out.println("Error in updateUser():" + e);
			e.printStackTrace();
		}

		System.out.println("In User: Exiting updateUser()...");
	}

	/**
	 * Method to delete an existing user from db2 table
	 * 
	 * @param
	 * @return void
	 * @throws Exception
	 */
	public void deleteUser() throws Exception {

		System.out.println("In User: Entering deleteUser()...");
		List<UserInfo> deleteList = new ArrayList<UserInfo>();

		try {

			for (UserInfo userInfo : userInfoList) {
				if (userInfo.isCheckboxClickedFlag() == true) {
					deleteList.add(userInfo);
				}
			}
			whereClause = Constants.USER_ID;
			sql = QueryBuilder.DELETE_USER_INFO + " " + whereClause;

			int deleteFlag = UserMgmtDAO.deleteUser(sql, deleteList);

			if (deleteFlag > 0) {
				System.out.println("User delete successful.");
				/* FacesContext.getCurrentInstance().addMessage("validationMsg",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								deleteList.size() + " " + bundle.getString(Constants.USER_DELETE_SUCESS),
								deleteList.size() + " " + bundle.getString(Constants.USER_DELETE_SUCESS)));*/
				setMsgAppend(String.valueOf(deleteList.size()));
				jscript= "deleteInfo";
				
			} else {
				System.out.println("User delete unsuccessful.");
				/*FacesContext.getCurrentInstance().addMessage("validationMsg",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(Constants.DATABASE_ERROR),
								bundle.getString(Constants.DATABASE_ERROR)));*/
				jscript = "error";
			}

		} catch (Exception e) {
			System.out.println("Error in deleteUser():" + e);
			e.printStackTrace();
		}

		System.out.println("In User: Exiting deleteUser()...");
	}


	/**
	 * Method called on cancel action
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public String cancelSave(){
		System.out.println("Entering cancelSave()...");
		//FacesContext context  = FacesContext.getCurrentInstance();
		//context.getExternalContext().getSessionMap().remove("user");
		return "userInfo";
	}
	
	/**
	 * Method called on cancel action listener
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void cancelAction(ActionEvent event){
		//Get submit button id
		System.out.println("In cancelAction..");
		String buttonId = event.getComponent().getId();
        System.out.println("buttonId clicked: " +buttonId);
	}
	
	/**
	 * Method to toggleBtnAction
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void toggleBtnAction(ActionEvent event){
		//Get toggle button id
		System.out.println("In toggleBtnAction.. ");
		String buttonId = event.getComponent().getId();
		System.out.println("buttonId clicked:" +buttonId);
		
		if(toggleBtnChgValue.equals(Constants.NEW_USER_BUTTON)) {
			setToggleBtnChgValue("Save User");
			setCheckboxAllFlag(true);
			System.out.println("CheckboxAllFlag: "+isCheckboxAllFlag());
		}else if(toggleBtnChgValue.equals(Constants.SAVE_USER_BUTTON)){
			setToggleBtnChgValue("+ New User");
			System.out.println("CheckboxAllFlag: "+isCheckboxAllFlag());
			setCheckboxAllFlag(false);
		}
		jscript = null;
        System.out.println("TogglebuttonId clicked: "+ getToggleBtnChgValue());
	}
	
	
	/**
	 *  Ajax listener for email address
	 * @param e
	 * @throws Exception
	 */

	public void onChangeAjaxListener(AjaxBehaviorEvent e) throws Exception {

		UIComponent component = (UIComponent) e.getSource();
		String emailAddress = (String) component.findComponent("email").getAttributes().get("value");
		System.out.println("emailAddress:" + emailAddress);
		boolean isexist = checkUserExist(emailAddress);
		
		if(isexist) {
			System.out.println(isexist);
			setMsgAppend(emailAddress);
			jscript ="addUserExistInfo";
			
		   /*FacesContext.getCurrentInstance().addMessage("ajax_email",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(Constants.USER_ALREADY_EXIST),
						bundle.getString(Constants.USER_ALREADY_EXIST)));*/
		}else {
			jscript = null;
		}
		

	}

	
	
	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public boolean isToggleButton() {
		return toggleButton;
	}

	public void setToggleButton(boolean toggleButton) {
		this.toggleButton = toggleButton;
	}

	public boolean isCheckboxAllFlag() {
		return checkboxAllFlag;
	}

	public void setCheckboxAllFlag(boolean checkboxAllFlag) {
		this.checkboxAllFlag = checkboxAllFlag;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getToggleBtnChgValue() {
		return toggleBtnChgValue;
	}

	public void setToggleBtnChgValue(String toggleBtnChgValue) {
		this.toggleBtnChgValue = toggleBtnChgValue;
	}

	public String getJscript() {
		return jscript;
	}

	public void setJscript(String jscript) {
		this.jscript = jscript;
	}

	public String getMsgAppend() {
		return msgAppend;
	}

	public void setMsgAppend(String msgAppend) {
		this.msgAppend = msgAppend;
	}



}
