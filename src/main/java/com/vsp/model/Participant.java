package com.vsp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.vsp.dao.ParticipantDAO;
import com.vsp.util.CommonUtils;
import com.vsp.util.Constants;
import com.vsp.util.QueryBuilder;

/**
 * <p>
 * This the bean class for participant master.
 *
 * </p>
 * 
 * @author Amrita Sahu (amrita.sahu@in.ibm.com)
 * @version 1.0
 * @Date 12/Dec/2017
 */
@ManagedBean(name = "participant")
@ViewScoped
public class Participant implements Serializable {

	private static final long serialVersionUID = 5199551491746665622L;

	// Specify the property file name for resource bundle
	public final static ResourceBundle bundle = ResourceBundle.getBundle("com.vsp.util.vspMessages");
	
	private static ArrayList<ParticipantInfo> partInfoList;

	private int totalSize = 0;
	private boolean toggleButton = false;
	private boolean checkboxAllFlag =false;
	private ParticipantInfo partInfo;
	private String whereClause = null;
	private String orderBy = null;
	private static String sql = null;
	public String toggleBtnChgValue = Constants.NEW_PARTICIPANT_BUTTON;
	private String jscript = "";
	private String msgAppend = null;
	private String searchFilter;
	private boolean sortAscending = false;
	private String sortOrder = "";
	private String addEmail;
	private String addFirstName;
	private String addLastName;
    
	// Populating Sentiment drop down
	public ArrayList<SelectItem> getSentimentList() {
		ArrayList<SelectItem> sentimentList = CommonUtils.getSentimentList();
		return sentimentList;
	}

	// Populating IMT drop down
	public ArrayList<SelectItem> getImtList() {
		ArrayList<SelectItem> imtList = CommonUtils.getImtList();
		return imtList;
	}

	
	/**
	 * Method to add a new participant row
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void addNewRow() throws Exception{

		System.out.println("In Participant: Entering addNewRow()...");
		System.out.println("AddEmail:" +getAddEmail()+" "+" AddFirstName:"+getAddFirstName()+" AddLastName:"+getAddLastName());
		
		if(getToggleBtnChgValue().equals(Constants.SAVE_PARTICIPANT_BUTTON)) {
			for(ParticipantInfo partInfo: partInfoList) {
				partInfo.setCheckboxClickedFlag(false);
			}
			partInfo = new ParticipantInfo();
			partInfo.setCheckboxClickedFlag(true);
			partInfo.setEmail(getAddEmail());
			partInfo.setUser_Name(getAddFirstName()+" "+getAddLastName());
			partInfoList.add(partInfo);
			
			System.out.println("size of partInfoList in addNewRow: " + partInfoList.size());

			setToggleButton(true);
			
		}else if(getToggleBtnChgValue().equals(Constants.NEW_PARTICIPANT_BUTTON)){
			//save clicked
			insertParticipant();	
			setToggleButton(false);
		}

		System.out.println("In Participant: Exiting addNewRow()...");
	}

	/**
	 * Method to retrieve entire participant list from db2 table
	 * 
	 * @return ArrayList<ParticipantInfo>
	 * @throws Exception
	 */

	public ArrayList<ParticipantInfo> getAllParticipantList() throws Exception {
		System.out.println("In Participant: Entering getAllParticipantList()...");
		try {
			System.out.println("toggleButton :" + toggleButton);
            System.out.println("getSortOrder().isEmpty():" +getSortOrder().isEmpty());
            System.out.println("getToggleBtnChgValue :" + getToggleBtnChgValue());
            
			//if (isToggleButton() == false) {
			if(!getToggleBtnChgValue().equals(Constants.SAVE_PARTICIPANT_BUTTON) && getSortOrder().isEmpty()){
				orderBy = "order by "+ Constants.USER_NAME;
				whereClause = Constants.DELETE_CONDITION;
				sql = QueryBuilder.SELECT_PARTICIPANT_INFO + " " + whereClause +" "+orderBy;

				partInfoList = ParticipantDAO.getAllParticipantList(sql);
				totalSize = partInfoList.size();
				setToggleButton(false);
				System.out.println("Size of partInfoList : " + partInfoList.size());
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("In Participant: Exiting getAllParticipantList()...");
		return partInfoList;
	}

	
	/**
	 * Method to add a new participant to db2 table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void insertParticipant() throws Exception {

		System.out.println("In Participant: Entering insertParticipant()... ");
		setSortOrder("");
		
		for (ParticipantInfo partInfoTemp : partInfoList) {
			if (partInfoTemp.isCheckboxClickedFlag() == true) {
				System.out.println("trying to insert");
				partInfoTemp.setCheckboxClickedFlag(false);

				partInfo = partInfoTemp;
				break;
			}
		}

		// Check if participant already exist
		boolean existFlag = checkParticipantExist(partInfo.getEmail());

		if (existFlag == false) {

			try {
				sql = QueryBuilder.INSERT_PARTICIPANT_INFO;

				int insertFlag = ParticipantDAO.insertParticipant(sql, partInfo);

				if (insertFlag > 0) {
					System.out.println("Participant insert successful.");
					setMsgAppend(partInfo.getUser_Name());
					jscript= "addNewInfo";
				} else {
					System.out.println("Participant insert unsuccessful.");
					jscript ="error";
				}

			} catch (Exception e) {
				System.out.println("Error in insertParticipant():" + e);
				e.printStackTrace();
			}

		} else {
			// error msg on UI	
			setMsgAppend(partInfo.getEmail());
			jscript= "addExistInfo";
		}

		System.out.println("In Participant: Exiting insertParticipant()...");

	}

	/**
	 * Method to check an existing participant in db2 table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public boolean checkParticipantExist(String email) throws Exception {
		System.out.println("In Participant: Entering checkParticipantExist()...");
		boolean existDealFlag = false;
		try {

			whereClause = Constants.EMAIL_ADDRESS;
			String sql = QueryBuilder.COUNT_PARTICIPANT + " " + whereClause;

			existDealFlag = ParticipantDAO.checkParticipantExist(sql, email);
		} catch (Exception e) {
			System.out.println("Error in checkParticipantExist():" + e);
			e.printStackTrace();
		}
		System.out.println("In Participant: Exiting checkParticipantExist()...");

		return existDealFlag;
	}

	/**
	 * Method to update an existing participant into db2 table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void updateParticipant() throws Exception {

		System.out.println("In Participant: Entering updateParticipant()...");
		List<ParticipantInfo> updateList = new ArrayList<ParticipantInfo>();
		setSortOrder("");
		try {

			for (ParticipantInfo partInfo : partInfoList) {
				if (partInfo.isCheckboxClickedFlag() == true) {
					updateList.add(partInfo);
				}
			}
			whereClause = Constants.PARTICIPANT_ID;
			sql = QueryBuilder.UPDATE_PARTICIPANT_INFO + " " + whereClause;

			int updateFlag = ParticipantDAO.updateParticipant(sql, updateList);

			if (updateFlag > 0) {
				System.out.println("Participant update successful.");
				setMsgAppend(String.valueOf(updateList.size()));
				jscript= "updateInfo";
				
			} else {
				System.out.println("Participant update unsuccessful.");
				jscript= "error";
			}

		} catch (Exception e) {
			System.out.println("Error in updateParticipant():" + e);
			e.printStackTrace();
		}

		System.out.println("In Participant: Exiting updateParticipant()...");
	}

	/**
	 * Method to delete an existing participant from db2 table
	 * 
	 * @param
	 * @return void
	 * @throws Exception
	 */
	public void deleteParticipant() throws Exception {

		System.out.println("In Participant: Entering deleteParticipant()...");
		List<ParticipantInfo> deleteList = new ArrayList<ParticipantInfo>();
		setSortOrder("");
		try {

			for (ParticipantInfo partInfo : partInfoList) {
				if (partInfo.isCheckboxClickedFlag() == true) {
					deleteList.add(partInfo);
				}
			}
			whereClause = Constants.PARTICIPANT_ID;
			sql = QueryBuilder.DELETE_PARTICIPANT_INFO + " " + whereClause;

			int deleteFlag = ParticipantDAO.deleteParticipant(sql, deleteList);

			if (deleteFlag > 0) {
				System.out.println("Participant delete successful.");
				setMsgAppend(String.valueOf(deleteList.size()));
				jscript= "deleteInfo";
				
			} else {
				System.out.println("Participant delete unsuccessful.");
				jscript = "error";
			}

		} catch (Exception e) {
			System.out.println("Error in deleteParticipant():" + e);
			e.printStackTrace();
		}

		System.out.println("In Participant: Exiting deleteParticipant()...");
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
		return "participantMaster";
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
		System.out.println("Actual buttonId clicked:" +buttonId);
				
		if(toggleBtnChgValue.equals(Constants.NEW_PARTICIPANT_BUTTON)||toggleBtnChgValue.equals(Constants.ADD_USER_BUTTON)) {
			setToggleBtnChgValue(Constants.SAVE_PARTICIPANT_BUTTON);
			setCheckboxAllFlag(true);
			System.out.println("CheckboxAllFlag: "+isCheckboxAllFlag());
		}else if(toggleBtnChgValue.equals(Constants.SAVE_PARTICIPANT_BUTTON)){
			setToggleBtnChgValue(Constants.NEW_PARTICIPANT_BUTTON);
			System.out.println("CheckboxAllFlag: "+isCheckboxAllFlag());
			setCheckboxAllFlag(false);
		}
		jscript = null;
        System.out.println("TogglebuttonId clicked: "+ getToggleBtnChgValue());
	}
	
	
	public void sortBy(String value) {
		System.out.println("In sortby...............................");
		System.out.println("sortOrder :" + value);
		setSortOrder(value);
		jscript = null;
		
		switch (value) {

			case "user":
	
				Collections.sort(partInfoList, new Comparator<ParticipantInfo>() {
	
					@Override
					public int compare(ParticipantInfo o1, ParticipantInfo o2) {
						if (sortAscending == true) {
							System.out.println("asc........");
							// ascending order
							return o1.getUser_Name().compareTo(o2.getUser_Name());
						} else {
							// descending order
							System.out.println("desc........");
							return o2.getUser_Name().compareTo(o1.getUser_Name());
						}
					}
	
				});
				break;
	
			case "email":
	
				Collections.sort(partInfoList, new Comparator<ParticipantInfo>() {
	
					@Override
					public int compare(ParticipantInfo o1, ParticipantInfo o2) {
						if (sortAscending == true) {
							System.out.println("asc........");
							// ascending order
							return o1.getEmail().compareTo(o2.getEmail());
						} else {
							// descending order
							System.out.println("desc........");
							return o2.getEmail().compareTo(o1.getEmail());
						}
					}
	
				});
	
				break;
	
			case "imt":
	
				Collections.sort(partInfoList, new Comparator<ParticipantInfo>() {
	
					@Override
					public int compare(ParticipantInfo o1, ParticipantInfo o2) {
						if (sortAscending == true) {
							System.out.println("asc........");
							// ascending order
							return o1.getImt_Id().compareTo(o2.getImt_Id());
						} else {
							// descending order
							System.out.println("desc........");
							return o2.getImt_Id().compareTo(o1.getImt_Id());
						}
					}
	
				});
				break;
	
			case "storm":
	
				Collections.sort(partInfoList, new Comparator<ParticipantInfo>() {
	
					@Override
					public int compare(ParticipantInfo o1, ParticipantInfo o2) {
						if (sortAscending == true) {
							System.out.println("asc........");
							// ascending order
							return Boolean.valueOf(o1.isStorm_Trained()).compareTo(Boolean.valueOf(o2.isStorm_Trained()));
						} else {
							// descending order
							System.out.println("desc........");
							return Boolean.valueOf(o2.isStorm_Trained()).compareTo(Boolean.valueOf(o1.isStorm_Trained()));
						}
					}
	
				});
				break;
	
			default:
				System.out.println("Default Sort");
				
		}

		for (ParticipantInfo info : partInfoList) {
			System.out.println("List sort change:" + info.getUser_Name()+ "" + info.isStorm_Trained());
		}

		if (sortAscending) {
			sortAscending = false;
		} else {
			sortAscending = true;
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

	public String getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public boolean isSortAscending() {
		return sortAscending;
	}

	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getAddEmail() {
		return addEmail;
	}
	public void setAddEmail(String addEmail) {
		this.addEmail = addEmail;
	}

	public String getAddFirstName() {
		return addFirstName;
	}

	public void setAddFirstName(String addFirstName) {
		this.addFirstName = addFirstName;
	}

	public String getAddLastName() {
		return addLastName;
	}

	public void setAddLastName(String addLastName) {
		this.addLastName = addLastName;
	}
	

}
