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
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
@SessionScoped
public class User implements Serializable {

	private static final long serialVersionUID = 5199551491746665622L;

	// Specify the property file name for resource bundle
	public final static ResourceBundle bundle = ResourceBundle.getBundle("com.vsp.util.vspMessages");

	private static ArrayList<UserInfo> userInfoList;

	private int totalSize = 0;

	private boolean toggleButton = false;

	private boolean checkboxAllFlag;

	private UserInfo userInfo;

	private String whereClause = null;
	private static String sql = null;
	private static HashMap<Integer, String> optionMap;

	private boolean selected;

	// Populating Role drop down
	public ArrayList<SelectItem> getRoleList() {
		whereClause = Constants.IS_ACTIVE_CONDITION;
		sql = QueryBuilder.SELECT_USER_ROLE + " " + whereClause;
		ArrayList<SelectItem> roleList = new ArrayList<SelectItem>();
		try {
			optionMap = UserMgmtDAO.getRoleList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				roleList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return roleList;
	}

	// Populating IMT drop down
	public ArrayList<SelectItem> getIMTList() {
		sql = QueryBuilder.SELECT_IMT;
		ArrayList<SelectItem> imtList = new ArrayList<SelectItem>();
		try {
			optionMap = UserMgmtDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				imtList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
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
	public void addNewRow() {

		System.out.println("In User: Entering addNewRow()...");

		userInfo = new UserInfo();
		userInfo.setCheckboxClickedFlag(true);
		userInfoList.add(userInfo);
		System.out.println("size of userInfoList in addNewRow: " + userInfoList.size());

		setCheckboxAllFlag(false);
		setToggleButton(true);
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
		// Select USER_ID, LAST_NAME, FIRST_NAME, EMAIL, ROLE_ID, ACTIVE from USERS
		// where IS_DELETE ='N'
		try {
			System.out.println("toggleButton :" + toggleButton);

			if (isToggleButton() == false) {
				whereClause = Constants.DELETE_CONDITION;
				sql = QueryBuilder.SELECT_USER_INFO + " " + whereClause;

				userInfoList = UserMgmtDAO.getAllUserList(sql);
				setToggleButton(false);
				System.out.println("Size of userInfoList : " + userInfoList.size());
			}

			if (isSelected() == true) {
				for (UserInfo userInfo : userInfoList) {
					userInfo.setCheckboxClickedFlag(true);
				}

			} /*
				 * else { for(UserInfo userInfo : userInfoList) {
				 * userInfo.setCheckboxClickedFlag(false); } }
				 */

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
	public void valueChangeMethod(ValueChangeEvent e) {
		System.out.println("In User: Entering valueChangeMethod()...");

		String isChecked = e.getNewValue().toString();
		System.out.println("isChecked :" + isChecked);

		if (isChecked.equalsIgnoreCase("true")) {
			setSelected(true);
		} else {
			setSelected(false);
		}

		// setCheckboxAllFlag(false);
		// setToggleButton(false);
		System.out.println("In User: Exiting valueChangeMethod()...");
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
		setToggleButton(false);

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
					FacesContext.getCurrentInstance().addMessage("User:validationMsg",
							new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString(Constants.USER_ADDED_SUCCESS),
									bundle.getString(Constants.USER_ADDED_SUCCESS)));

				} else {
					System.out.println("User insert unsuccessful.");
					FacesContext.getCurrentInstance().addMessage("User:validationMsg",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(Constants.DATABASE_ERROR),
									bundle.getString(Constants.DATABASE_ERROR)));
				}

			} catch (Exception e) {
				System.out.println("Error in insertUser():" + e);
				e.printStackTrace();
			}

		} else {
			// error msg on UI
			FacesContext.getCurrentInstance().addMessage("User:validationMsg",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							userInfo.getEmail() + " " + bundle.getString(Constants.USER_ALREADY_EXIST),
							userInfo.getEmail() + " " + bundle.getString(Constants.USER_ALREADY_EXIST)));
			// setToggleButton(false);
			// page="userinfo";
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
				FacesContext.getCurrentInstance().addMessage("User:validationMsg",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								updateList.size() + " " + bundle.getString(Constants.USER_UPDATE_SUCCESS),
								updateList.size() + " " + bundle.getString(Constants.USER_UPDATE_SUCCESS)));
			} else {
				System.out.println("User update unsuccessful.");
				FacesContext.getCurrentInstance().addMessage("User:validationMsg",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(Constants.DATABASE_ERROR),
								bundle.getString(Constants.DATABASE_ERROR)));
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
				FacesContext.getCurrentInstance().addMessage("User:validationMsg",
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								deleteList.size() + " " + bundle.getString(Constants.USER_DELETE_SUCESS),
								deleteList.size() + " " + bundle.getString(Constants.USER_DELETE_SUCESS)));
			} else {
				System.out.println("User delete unsuccessful.");
				FacesContext.getCurrentInstance().addMessage("User:validationMsg",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(Constants.DATABASE_ERROR),
								bundle.getString(Constants.DATABASE_ERROR)));
			}

		} catch (Exception e) {
			System.out.println("Error in deleteUser():" + e);
			e.printStackTrace();
		}

		System.out.println("In User: Exiting deleteUser()...");
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
