package com.vsp.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.vsp.dao.DealDAO;
import com.vsp.dao.FormDAO;
import com.vsp.util.Constants;
import com.vsp.util.QueryBuilder;

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
	//Specify the property file name 
	public final static ResourceBundle bundle = ResourceBundle.getBundle("com.vsp.util.vspMessages");
	private static final long serialVersionUID = 1094801825228386363L;
	private static String searchType = Constants.DEFAULT_SEARCH_TYPE;
	private static  List<DealInfo> dealList = new ArrayList<DealInfo>();
	private static  List<DealInfo> searchList = new ArrayList<DealInfo>();
	private static List<A1Form> a1FormList = new ArrayList<A1Form>();
	private static ArrayList<String> a1History;
	private static HashMap<Integer, String> optionMap;
	private static String searchValue;

	private static int reference_No;

	private static String whereClause = null;
	private boolean show = false;
	private boolean a1DBStatus = false;
	private int a1Count;
	private boolean a1ButtonClick;
	private static String a1StatusMessage = null;
	private static String searchMsg = null;
	

	private static boolean a1statusFlag ;
	public static String a1RefNo;
	public static String activeA1RefNo;
	
	
	public  String getSearchMsg() {
		return searchMsg;
	}
	public String getActiveA1RefNo() {
		return activeA1RefNo;
	}
	public boolean getA1statusFlag() {
		return a1statusFlag;
	}
	public String getA1RefNo() {
		return a1RefNo;
	}
	
	public  String getA1StatusMessage() {
		return a1StatusMessage;
	}
	
	public static void setA1History(ArrayList<String> a1History) {
		Deal.a1History = a1History;
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
	public List<DealInfo> getSearchList() {

		return searchList;
	}
	
	// searching deals
	public String searchDeal() throws Exception {
		show = false;
		
		if (searchType.equals(Constants.VSP_OPP_NO)) {
			whereClause = Constants.REFERENCE_NO_LIKE;
		}
		if (searchType.equals(Constants.CLIENT)) {			
			whereClause = Constants.CLIENT_LIKE;		
			}
		
		if (searchType.equals(Constants.SALES_CONNECT_NO)) {
			whereClause = Constants.SALES_CONNECT_NO_LIKE;
		}
		String sql = QueryBuilder.SEARCH_DEAL + "" + whereClause +"" +QueryBuilder.SEARCH_DEAL_ORDER_BY;
		searchList = DealDAO.getDealList(sql, searchValue+"%");
		
		if (searchList.isEmpty()) {
			searchMsg = bundle.getString(Constants.SEARCH_MESSAGE);
		} 
		else {
			searchMsg = null; 
		}
		return "dealSearch";
	}

	public List<String> getDeal(String value) throws Exception {

		List<String> tempList = new ArrayList<String>();

		try {
			String sql = null;
			/*if (searchType.equals(Constants.VSP_OPP_NO)) {
				sql = QueryBuilder.SELECT_VSP_REF_NO;
				whereClause = Constants.REFERENCE_NO_SQL;

			}*/
			if (searchType.equals(Constants.CLIENT)) {
				sql = QueryBuilder.SELECT_CUSTOMER_NAME;
				whereClause = Constants.CLIENT_SQL;
			}
		
			if (searchType.equals(Constants.SALES_CONNECT_NO)) {
				sql = QueryBuilder.SELECT_SALES_CONNECT_NO;
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
	//on change event of search value - auto complete
	public void valueChanged(SelectEvent event) throws Exception {
		System.out.println("In Deal: Entering valueChanged()...");
		String selectedValue = (String) event.getObject();
		System.out.println("selectedValue:" + selectedValue);
		System.out.println("searchType:" + searchType);
	
		if (searchType.equals(Constants.CLIENT)) {			
			whereClause = Constants.CLIENT_SQL;		
			}
		
		if (searchType.equals(Constants.SALES_CONNECT_NO)) {
			whereClause = Constants.SALES_CONNECT_NO_SQL;
		}
		String sql = QueryBuilder.SEARCH_DEAL + "" + whereClause +"" +QueryBuilder.SEARCH_DEAL_ORDER_BY;
		searchList = DealDAO.getDealList(sql, selectedValue);
		System.out.println("size::" + searchList.size());
		if (searchList.isEmpty()) {
			searchMsg = bundle.getString(Constants.SEARCH_MESSAGE);
			
		} 
		else {
			searchMsg = null;
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("dealSearch.xhtml");
	    FacesContext.getCurrentInstance().responseComplete();
	
		
	}
// deal management hyperlink clicked
	public String openDealManagement() {
		System.out.println("In Header: Entering openDealManagement()...");
		dealList.clear();
		searchList.clear();
		searchValue = null;
		return "dealSearch";
	}
	// Invoked from VIEW DEAL DETAILS button
	public String openDeal(int referenceNo) throws Exception {
		System.out.println("In Deal: Entering openDeal()...");
		dealList.clear();
		activeA1RefNo = null;
		reference_No = referenceNo;
		String sql = QueryBuilder.GET_DEAL;
		//dealList.add(DealDAO.getDealInfo(sql, referenceNo));
		for(DealInfo currentDeal: searchList) {
			if(currentDeal.getReference_No() == referenceNo ) {
				dealList.add(currentDeal);
			}
		}
		a1DBStatus = hasA1Exist(referenceNo);
		if(a1DBStatus == true) {
			a1statusFlag = true;
			sql = QueryBuilder.GET_A1_FORMS;
			a1FormList = DealDAO.getA1List (sql,referenceNo);
			a1RefNo = a1FormList.get(0).getA1_RefNo();
			activeA1RefNo = a1FormList.get(0).getA1_RefNo();
			whereClause = Constants.A1_STATUS ;
			sql = QueryBuilder.A1_HISTORY + " " + whereClause;
			//System.out.println("reference_No:"+reference_No);
			a1History = DealDAO.getA1History(sql,reference_No);
		}
		else {
			
			a1FormList = new ArrayList<A1Form> ();
			a1FormList.add(new A1Form());
			a1statusFlag = false;
			
		}
	
		System.out.println("In Deal: Exiting openDeal()...");
		return "dealForm";
	
	}

	// Invoked from NEW DEAL FORM button
	public String newDeal() throws Exception {
		System.out.println("inside newDeal");
		//searchValue = null;
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
		String sql = QueryBuilder.SELECT_MAX_VSP_REF_NO;
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
		String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
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
		String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
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
		String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
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
		String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
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
		String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
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
		String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

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
		String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
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
		String sql = QueryBuilder.SELECT_IMT;
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
		String sql = QueryBuilder.SELECT_IMT;
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
		String sql = QueryBuilder.SELECT_SECTOR;
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
	public int insertDeal() throws Exception {
		System.out.println("In Deal: Entering insertDeal()...");
		System.out.println("name..."+dealInfoObj.getCustomer_Name());
		
		int insertFlag = 0;
		try {

			String dealSQL = QueryBuilder.INSERT_DEAL;
			String a1SQL = QueryBuilder.INSERT_A1_WORKSHOP;
			if(a1ButtonClick == true) {
				a1FormObj = a1FormList.get(0);
				a1FormObj.setA1_RefNo(a1RefNo);
			insertFlag = DealDAO.insertDeal_A1(dealSQL, a1SQL, dealInfoObj, a1FormObj, a1statusFlag);
			}
			else {
				insertFlag = DealDAO.insertDealOnly(dealSQL, a1SQL, dealInfoObj);
			}
			if (insertFlag > 0) {
				activeA1RefNo = a1FormObj.getA1_RefNo();
				System.out.println("Deal insert successful.");
			} else {
				System.out.println("Deal insert failed.");
			}

		} catch (Exception e) {
			System.out.println("Error in insertDeal();:" + e);
			e.printStackTrace();
		}
		System.out.println("In Deal: Exiting insertDeal()...");
		return insertFlag;
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
			a1StatusMessage = null;
			whereClause = Constants.VSP_REF_NO + "and" +  Constants.A1_REF_NO;
			String dealSQL = QueryBuilder.UPDATE_DEAL + " " + Constants.VSP_REF_NO;
			a1FormObj.setA1_RefNo(a1RefNo);
			
			System.out.println("getReferenceNo:"+a1FormObj.getReferenceNo()+":"+a1RefNo);
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
	//cancel deal
	public String cancelDeal() {
		System.out.println("In Deal: Entering cancelDeal()...");
		return "dealSearch";
	}
	/**
	 * Method to check an existing deal in DEAL_INFO table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public String isDealExist() throws Exception {
		System.out.println("In Deal: Entering isDealExist()...");
		String page = null;
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
			
			if(reference_No > 0) {
				dealInfoObj.setReference_No(reference_No);
					boolean existDealFlag = DealDAO.isDealExist(sql,reference_No);
		           
					if(existDealFlag == false) {
						//Insert new deal
						
						int insertStatus = insertDeal();
						 insertStatus = 1;
						if(insertStatus > 0) {
						
							dealList.clear();
							a1FormList.clear();
							dealList.add(dealInfoObj);
							System.out.println("a1statusFlag:"+a1statusFlag);
							if(a1statusFlag == true) {
								System.out.println("A1 status:"+a1FormObj.getA1_Status());
								a1FormList.add(a1FormObj);
							}
							else {
								
								a1FormList.add(new A1Form());
								a1statusFlag = false;
							}
							
							page = "dealForm";
						
						}
						System.out.println("Page name after insert:"+page);
					}else {
						//update existing deal
						updateDeal();
					}
			}
			
					
		} catch (Exception e) {
			System.out.println("Error in isDealExist():" + e);
			e.printStackTrace();
		}
		
	
		return page;
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
	public int validateA1Status(int reference_No,String a1_RefNo) throws Exception {
		int a1Count = 0;
		try {

			whereClause = Constants.VSP_REF_NO +" and " + Constants.A1_REF_NO + "and " +Constants.A1_STATUS ;
			String sql = QueryBuilder.COUNT_A1 + " " + whereClause;

			a1Count = DealDAO.isA1Exist(sql,  reference_No, a1_RefNo);
            			
			
		} catch (Exception e) {
			System.out.println("Error in validateA1Status():" + e);
			e.printStackTrace();
		}
		return a1Count;
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
		
		System.out.println("A1 Ref No:"+a1RefNo);
		System.out.println("Ref No:"+reference_No);
		int a1Count = validateA1Status(reference_No,a1RefNo);
		System.out.println("a1Count:"+a1Count);
		if(a1Count > 0) {
			a1StatusMessage = null;
			a1FormList = new ArrayList<A1Form> ();
			a1FormList.add(new A1Form());
			generateA1RefNo(true,a1Count);
			
		}
		else {
			a1StatusMessage = Constants.A1_STATUS_MSG;
		}
		
	
	
	}
	/**
	 * Method to generate new A1 reference number
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
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
		System.out.println("Inside generateA1RefNo:"+a1RefNo);
	
		
}
	/**
	 * Method to get A1 history
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public ArrayList<String> getA1History(){
		
		return a1History;
	}
	/**
	 * Method to handle A1 history button listener
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void historyListener(ActionEvent e) throws Exception {
		String buttonValue = (String)e.getComponent().getAttributes().get("value");
		System.out.println("buttonValue:"+buttonValue);
		if(a1FormList!=null) {
		for(A1Form a1form:a1FormList)
		{
			if(a1form.getA1_RefNo().equals(buttonValue)) {
				int index = a1FormList.indexOf(a1form);
				System.out.println("index:"+index);
				a1FormList.remove(a1form);
				a1FormList.add(0,a1form);
				a1RefNo = a1form.getA1_RefNo();
		}
			}
		}
			
	}
}
