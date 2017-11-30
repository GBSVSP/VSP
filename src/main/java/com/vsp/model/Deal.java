package com.vsp.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import com.vsp.dao.DealDAO;
import com.vsp.dao.FormDAO;
import com.vsp.util.Constants;
import com.vsp.util.QueryBuilder;
import com.vsp.util.SessionUtils;

/**
 * <p>
 * This the bean class for deal
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 13/Nov/2017
 */
@ManagedBean
@ViewScoped
public class Deal implements Serializable {
	private static final long serialVersionUID = 1094801825228386363L;
	private static String searchType = Constants.DEFAULT_SEARCH_TYPE;
	private static  List<DealInfo> dealList;

	private static HashMap<Integer, String> optionMap;
	private static String searchValue;

	private static String sql = null;
	private static int reference_No;
	private int insertFlag = 0;
	private String whereClause = null;
	private boolean show = false;
	private boolean a1DBStatus = false;
	private static int count;
	private int a1Count;
	private DealInfo dealInfo;
	private boolean a1ButtonClick;
	private static String a1StatusMessage = null;
	
	private static List<A1Form> a1FormList;
	private static boolean a1statusFlag ;
	public static String a1RefNo;
	
	public boolean getA1statusFlag() {
		return a1statusFlag;
	}
	public String getA1RefNo() {
		return a1RefNo;
	}
	
	public  String getA1StatusMessage() {
		return a1StatusMessage;
	}
	
	public Deal() throws Exception {
		//a1FormList = new ArrayList();

		//a1FormList.add(new A1Form());
		
	}
	public  List<A1Form> getA1FormList() {
	return a1FormList;
	}

	@ManagedProperty(value = "#{dealInfo}")
	private DealInfo dealInfoObj  = new DealInfo();

	public void setDealInfoObj(DealInfo dealInfo) {
		this.dealInfoObj = dealInfo;
	}
	@ManagedProperty(value = "#{a1Form}")
	private A1Form a1FormObj = new A1Form();

	public void setA1FormObj(A1Form a1Form) {
		this.a1FormObj = a1Form;
	}
	// generating the reference number in YYYY+4 digit serial number format
	public int getReference_No() {
		
		return reference_No;
	}

	public int getInsertFlag() {
		return insertFlag;
	}

	public void setInsertFlag(int insertFlag) {
		this.insertFlag = insertFlag;
	}

	public void setReference_No(int reference_No) {
		this.reference_No = reference_No;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public List<DealInfo> getDealList() {

		return dealList;
	}

	
	// searching deals
	public String searchDeal() throws Exception {
		show = false;
		sql = QueryBuilder.SEARCH_DEAL + "" + whereClause +"" +QueryBuilder.SEARCH_DEAL_ORDER_BY;
		dealList = DealDAO.getDealList(sql, searchValue);
		
		if (dealList.isEmpty()) {
			show = true;
		} else {
			count = dealList.size();
		}
		return "dealSearch";
	}

	public List<String> getDeal(String value) throws Exception {

		List<String> tempList = new ArrayList<String>();

		try {

			if (searchType.equals(Constants.VSP_OPP_NO)) {
				sql = QueryBuilder.SELECT_VSP_REF_NO;
				whereClause = Constants.VSP_OPP_NO_SQL;

			}
			if (searchType.equals(Constants.CLIENT)) {
				sql = QueryBuilder.SELECT_CUSTOMER_NAME;
				whereClause = Constants.CLIENT_SQL;
			}
			if (searchType.equals(Constants.DEAL)) {
				sql = QueryBuilder.SELECT_DEAL_ID;
				whereClause = Constants.CLIENT_SQL;
			}
			if (searchType.equals(Constants.SALES_CONNECT_NO)) {
				sql = QueryBuilder.SELECT_SALES_CONNECT_OPP_NO;
				whereClause = Constants.SALES_CONNECT_NO_SQL;
			}

			ResultSet dealResult = DealDAO.getDeal(sql);

			if (dealResult.next()) {
				do {
					String data = dealResult.getString(1);
					if (data.toLowerCase().startsWith(value.toLowerCase())) {
						tempList.add(data);
					}

				} while (dealResult.next());
			}
		} catch (Exception e) {
			System.out.println("Error in getDeal();:" + e);
			e.printStackTrace();
		}

		return tempList;
	}

	public void typeChanged(AjaxBehaviorEvent e) throws Exception {

		UIComponent component = (UIComponent) e.getSource();
		searchType = (String) component.findComponent("searchType").getAttributes().get("value");

		System.out.println("type:" + searchType);

	}

	// Invoked from VIEW DEAL DETAILS button
	public String openDeal(int referenceNo) throws Exception {
		System.out.println("In Deal: Entering openDeal()...");
		dealList.clear();
		reference_No = referenceNo;
		sql = QueryBuilder.GET_DEAL;
		dealList.add(DealDAO.getDealInfo(sql, referenceNo));
		a1DBStatus = hasA1Exist(referenceNo);
		if(a1DBStatus == true) {
			a1statusFlag = true;
			sql = QueryBuilder.GET_A1_FORMS;
			a1FormList = DealDAO.getA1List (sql,referenceNo);
			a1RefNo = a1FormList.get(0).getA1_RefNo();
		}
		else {
			
			a1FormList = new ArrayList<A1Form> ();
			a1FormList.add(new A1Form());
			a1statusFlag = false;
			
		}
		System.out.println("status::" + dealList.get(0).getSc_Imt_Id());
		System.out.println("In Deal: Exiting openDeal()...");
		return "dealForm";
	
	}

	// Invoked from NEW DEAL FORM button
	public String newDeal() throws Exception {
		System.out.println("inside newDeal");
		searchValue = null;
		searchType = Constants.DEFAULT_SEARCH_TYPE;
		if (dealList != null) {
		
			dealList.clear();
		
		}
		if(a1FormList!=null) {
			a1FormList.clear();
		}
		a1FormList = new ArrayList<A1Form> ();
		a1FormList.add(new A1Form());
		a1RefNo = null;
		generateReferenceNo();
		a1statusFlag = false;
		
		return "newDeal";

	}
	//generating deal reference number
	public void  generateReferenceNo() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		sql = QueryBuilder.SELECT_MAX_VSP_REF_NO;
		int currentRefNo = DealDAO.getReferenceNumber(sql);
		System.out.println("currentRefNo:"+currentRefNo);
		if(currentRefNo > 0) {
		if (Integer.parseInt(Integer.toString(currentRefNo).substring(0, 4)) == year) {
			currentRefNo++;
			reference_No = currentRefNo;
		} else {
			reference_No = Integer.parseInt(String.valueOf(year) + Constants.VSP_REF_NO_START);
		}
		dealInfoObj.setReference_No(reference_No);
		}
		else {
			reference_No = Integer.parseInt(String.valueOf(year) + Constants.VSP_REF_NO_START);
		}
		a1statusFlag = true; 
		dealInfoObj.setReference_No(reference_No);
		System.out.println("New reference_No:"+reference_No);
		
	}
	// Populating Led By drop down
	public ArrayList<SelectItem> getLedByList() {
		whereClause = Constants.LED_BY_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getValue(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating Potential TCV drop down
	public ArrayList<SelectItem> getPotentialTCVList() {
		whereClause = Constants.PPOTENTIAL_TCV_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating A1 Status drop down
	public ArrayList<SelectItem> getA1StatusList() {
		whereClause = Constants.A1STATUS_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getValue(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating Next Step drop down
	public ArrayList<SelectItem> getNextStepList() {
		whereClause = Constants.NEXT_STEP_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating Should we sell drop down
	public ArrayList<SelectItem> getShouldweSellList() {
		whereClause = Constants.SHOULD_WE_SELL_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating can we sell drop down
	public ArrayList<SelectItem> getCanweSellList() {
		whereClause = Constants.CAN_WE_SELL_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating Evaluation drop down
	public ArrayList<SelectItem> getA1EvaluationList() {
		whereClause = Constants.A1EVALUATION_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating IMT drop down
	public List<SelectItem> getIMTList() {
		sql = QueryBuilder.SELECT_IMT;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating SCIMT drop down
	public List<SelectItem> getSCIMTList() {
		sql = QueryBuilder.SELECT_IMT;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating Sector drop down
	public ArrayList<SelectItem> getSectorList() {
		sql = QueryBuilder.SELECT_SECTOR;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	/**
	 * Method to add a new deal to database
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void insertDeal() throws Exception {
		System.out.println("In Deal: Entering insertDeal()...");
		System.out.println("name..."+dealInfoObj.getCustomer_Name());
		try {

			String dealSQL = QueryBuilder.INSERT_DEAL;
			String a1SQL = QueryBuilder.INSERT_A1_WORKSHOP;
			if(a1ButtonClick == true) {
			insertFlag = DealDAO.insertDeal_A1(dealSQL, a1SQL, dealInfoObj, a1FormObj, a1statusFlag);
			}
			else {
				insertFlag = DealDAO.insertDealOnly(dealSQL, a1SQL, dealInfoObj);
			}
			if (insertFlag > 0) {
				System.out.println("Deal insert successful.");
			} else {
				System.out.println("Deal insert unsuccessful.");
			}

		} catch (Exception e) {
			System.out.println("Error in insertDeal();:" + e);
			e.printStackTrace();
		}
		System.out.println("In Deal: Exiting insertDeal()...");
	}

	/**
	 * Method to update an existing deal into database
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void updateDeal() throws Exception {

		System.out.println("In Deal: Entering updateDeal()...");
		String a1SQL = null;
		int updateFlag = 0;
		try {
			
			whereClause = Constants.VSP_REF_NO + "and" +  Constants.A1_REF_NO;
			String dealSQL = QueryBuilder.UPDATE_DEAL + " " + Constants.VSP_REF_NO;
			System.out.println("manager name:"+a1FormObj.getA1_Manager());
				if (isA1Exist() == true) {
					a1SQL = QueryBuilder.UPDATE_A1_WORKSHOP + " " +whereClause;
					updateFlag = DealDAO.updateDeal(dealSQL,a1SQL,dealInfoObj, a1FormObj);
					a1statusFlag = true;
				}
				else {
					a1statusFlag = a1ButtonClick ;
						
					a1SQL = QueryBuilder.INSERT_A1_WORKSHOP;
					updateFlag = DealDAO.updateDeal_A1Insert(dealSQL,a1SQL,dealInfoObj,a1FormObj,a1statusFlag);
					}
		

			if (updateFlag > 0) {
				System.out.println("Deal update successful.");
			} else {
				System.out.println("Deal update unsuccessful.");
			}

		} catch (Exception e) {
			System.out.println("Error in updateDeal();:" + e);
			e.printStackTrace();
		}

		System.out.println("In Deal: Exiting updateDeal()...");
	}

	/**
	 * Method to check an existing deal in DEAL_INFO table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void isDealExist() throws Exception {
		System.out.println("In Deal: Entering isDealExist()...");
		try {

			whereClause = Constants.VSP_REF_NO;
			String sql = QueryBuilder.COUNT_DEAL + " " + whereClause;
			if(dealList!=null) {
				if(dealList.size() > 0) {
				dealInfoObj = dealList.get(0);
				}
			}
			if(a1FormList!=null) {
				if(a1FormList.size() > 0) {
				a1FormObj = a1FormList.get(0);
				}
			}
			System.out.println("reference no:"+reference_No+"customer name:"+dealInfoObj.getCustomer_Name());	
			if(reference_No > 0) {
				dealInfoObj.setReference_No(reference_No);
					boolean existDealFlag = DealDAO.isDealExist(sql,reference_No);
		           
					if(existDealFlag == false) {
						//Insert new deal
						
						insertDeal();
					}else {
						//update existing deal
						updateDeal();
					}
			}
			
					
		} catch (Exception e) {
			System.out.println("Error in isDealExist():" + e);
			e.printStackTrace();
		}
		
		//dealList.add(dealInfoObj);
		//a1FormList.add(a1FormObj);
		//return "dealForm";
	}

	/**
	 * Method to check an existing A1 workshop in a1_workshop_info table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public boolean isA1Exist() throws Exception {
		 boolean a1ExistFlag = false;
		try {

			whereClause = Constants.VSP_REF_NO +" and " + Constants.A1_REF_NO;
			String sql = QueryBuilder.COUNT_A1 + " " + whereClause;

			int count =  DealDAO.isA1Exist(sql, dealInfoObj.getReference_No(),a1FormObj.getA1_RefNo());
			if (count > 0) {
				a1ExistFlag =true;
			}
			else {
				a1ExistFlag = false;
			}
           System.out.println("a1ExitFlag:"+a1ExistFlag+dealInfoObj.getReference_No()+a1FormObj.getA1_RefNo());
			
			
		} catch (Exception e) {
			System.out.println("Error in isA1Exist():" + e);
			e.printStackTrace();
		}
		return a1ExistFlag;
	}
	/**
	 * Method to validate A1 form status
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public int validateA1Status() throws Exception {
		
		try {

			whereClause = Constants.VSP_REF_NO +" and " + Constants.A1_REF_NO + "and " +Constants.A1_STATUS ;
			String sql = QueryBuilder.COUNT_A1 + " " + whereClause;

			int count = DealDAO.isA1Exist(sql,  dealInfoObj.getReference_No(),a1FormObj.getA1_RefNo());
            System.out.println("count:"+count);
			
			
		} catch (Exception e) {
			System.out.println("Error in validateA1Status():" + e);
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * Method to check A1 status of a deal
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public boolean hasA1Exist(int referenceNo) throws Exception {
		boolean status = false;
		a1Count = 0;
		try {

			whereClause = Constants.VSP_REF_NO ;
			String sql = QueryBuilder.COUNT_A1 + " " + whereClause;
		
			a1Count = DealDAO.hasA1Exist(sql, referenceNo);
           if(a1Count> 0 ) {
        	  status = true; 
           }
           else {
        	   status = false; 
           }
			
			System.out.println("count:"+a1Count+":"+status);
			
		} catch (Exception e) {
			System.out.println("Error in hasA1Exist():" + e);
			e.printStackTrace();
		}
		return status;
	}
	/**
	 * Method to handle ADD A1 button click event
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void addA1Form(ActionEvent e) throws Exception {
		a1ButtonClick = true;
		generateA1RefNo(false,0);
		System.out.println("New A1 Ref No:"+a1RefNo);
	
	}
	/**
	 * Method to handle NEW A1 FORM button click event
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void addNewA1Form(ActionEvent e) throws Exception {
		a1ButtonClick = true;
		int count = validateA1Status();
		
		if(count > 0) {
			a1StatusMessage = null;
			generateA1RefNo(true,count);
			
		}
		else {
			a1StatusMessage = Constants.A1_STATUS_MSG;
		}
		
		System.out.println("New A1 Ref No:"+a1RefNo);
	
	}
	public void generateA1RefNo (boolean a1Status,int count) {
		
		if (a1Status == true) {
			
			a1RefNo = Constants.A1_REF_NO_BASE+ String.format("%02d", count+1);
			
		}
		else {
			a1RefNo = Constants.A1_REF_NO_START;
			System.out.println("a1ButtonClick:"+a1ButtonClick);
			if(a1ButtonClick == true) {
			a1statusFlag = true;
			}
			else {
				a1statusFlag = true;
			}
			
		}
		a1FormObj.setA1_RefNo(a1RefNo);
		
}
	
}
