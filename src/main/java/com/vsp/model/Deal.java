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

import com.vsp.dao.CommentsDAO;
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
	private static  List<Comments> commentsList = new ArrayList<Comments>();
	private static List<A1Form> a1FormList = new ArrayList<A1Form>();
	private static List<A23Form> a23FormList = new ArrayList<A23Form>();
	private String jscript = "";
	private static ArrayList<String> a1History = new ArrayList<String>();
	private static ArrayList<String> a23History = new ArrayList<String>();
	private static HashMap<Integer, String> optionMap;
	private static String searchValue;

	public static int reference_No;

	private static String whereClause = null;
	private boolean show = false;
	private boolean a1DBStatus = false;
	private boolean a23DBStatus = false;
	private int a1Count;
	private boolean a1ButtonClick;
	private boolean a23ButtonClick;
	private static String a1StatusMessage = null;
	private static String a23StatusMessage = null;
	private static String searchMsg = null;
	

	private static boolean a1statusFlag ;
	private static boolean a23statusFlag ;
	public static String a1RefNo;
	public static String a23RefNo;
	public static String activeA1RefNo;
	public static String activeA23RefNo;
	
	
	public  ArrayList<String> getA23History() {
		return a23History;
	}
	public String getActiveA23RefNo() {
		return activeA23RefNo;
	}
	public  String getA23RefNo() {
		return a23RefNo;
	}
	public  String getA23StatusMessage() {
		return a23StatusMessage;
	}
	public  List<Comments> getCommentsList() {
		return commentsList;
	}
	public  String getSearchMsg() {
		return searchMsg;
	}
	public String getActiveA1RefNo() {
		return activeA1RefNo;
	}
	public boolean getA1statusFlag() {
		return a1statusFlag;
	}
	
	public  boolean getA23statusFlag() {
		return a23statusFlag;
	}
	public String getA1RefNo() {
		return a1RefNo;
	}
	
	public  void setA1RefNo(String a1RefNo) {
		this.a1RefNo = a1RefNo;
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

	public  List<A23Form> getA23FormList() {
		return a23FormList;
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
	@ManagedProperty(value = "#{comments}")
	private Comments commentsObj = new Comments();
	public void setCommentsObj(Comments comments) {
		this.commentsObj = comments;
	}
	@ManagedProperty(value = "#{a23Form}")
	private A23Form a23FormObj = new A23Form();
	public void setA23FormObj(A23Form a23Form) {
		this.a23FormObj = a23Form;
	}
	
	
	// generating the reference number in YYYY+4 digit serial number format
	public int getReference_No() {
		
		return reference_No;
	}

	public String getJscript() {
		return jscript;
	}
	public void setJscript(String jscript) {
		this.jscript = jscript;
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
		a1FormList.clear();
		a23FormList.clear();
		commentsList.clear();
		a1History.clear();
		a23History.clear();
		a1RefNo = null;
		a23RefNo = null;
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
			activeA1RefNo = a1RefNo;
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
		
		a23DBStatus = hasA23Exist(referenceNo);
		System.out.println("a23DBStatus:"+a23DBStatus);
		if(a23DBStatus == true) {
			a23statusFlag = true;
			sql = QueryBuilder.GET_A23_FORMS;
			a23FormList = DealDAO.getA23List (sql,referenceNo);
	
			a23RefNo = a23FormList.get(0).getWorkshop_Ref_Number();
			System.out.println("a23FormList size:"+a23FormList.get(0).getWorkshop_DealCoachJustification());
			activeA23RefNo = a23RefNo;
			whereClause = Constants.A23_STATUS ;
			sql = QueryBuilder.A23_HISTORY + " " + whereClause;
			
			a23History = DealDAO.getA23History(sql,reference_No);
		}
		else {
		a23FormList = new ArrayList<A23Form> ();
		a23FormList.add(new A23Form());
		a23statusFlag = false;
		}
		
		sql = QueryBuilder.SELECT_COMMENTS;
		commentsList = CommentsDAO.getComments(sql,reference_No);
		
		System.out.println("In Deal: Exiting openDeal()...");
		return "dealForm";
	
	}

	/**
	 * Method to handle ADD NEW DEAL button click 
	 * Invoked from NEW DEAL FORM button
	 * @param value
	 * @return String
	 * @throws Exception
	 */
	public String newDeal() throws Exception {
		System.out.println("In Deal: Entering newDeal()...");
		jscript = null;
		searchType = Constants.DEFAULT_SEARCH_TYPE;
		if (dealList != null) {
		
			dealList.clear();
		
		}
		if(a1FormList!=null) {
			a1FormList.clear();
		}
		if(a23FormList!=null) {
			a23FormList.clear();
		}
		//generating A1 form
		a1FormList = new ArrayList<A1Form> ();
		a1FormList.add(new A1Form());
		a1RefNo = null;
		
		//generating workshop form
		a23FormList = new ArrayList<A23Form> ();
		a23FormList.add(new A23Form());
		a23RefNo = null;
		
		//generating reference number
		generateReferenceNo();
		a1statusFlag = false;
		a23statusFlag = false;
		
		System.out.println("In Deal: Exit newDeal()...");
		
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
			String a23SQL = QueryBuilder.INSERT_A23_WORKSHOP;
			//if(a1ButtonClick == true || a23ButtonClick == true) {
				if(a1ButtonClick == true){
				a1FormObj = a1FormList.get(0);
				a1FormObj.setA1_RefNo(a1RefNo);
				a1FormObj.setReferenceNo(reference_No);
				}
				if(a23ButtonClick == true) {
					a23FormObj = a23FormList.get(0);
					a23FormObj.setWorkshop_Ref_Number(a23RefNo);;
					a23FormObj.setReference_No(reference_No);
				}
				System.out.println("a23FormObj val:"+a23FormObj.getWorkshop_Status());
			insertFlag = DealDAO.insertDeal(dealSQL, a1SQL, a23SQL, dealInfoObj, a1FormObj,a23FormObj, a1statusFlag,a23statusFlag);
			
			if (insertFlag > 0) {
				activeA1RefNo = a1FormObj.getA1_RefNo();
				activeA23RefNo = a23FormObj.getWorkshop_Ref_Number();
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
	public int updateDeal() throws Exception {

		System.out.println("In Deal: Entering updateDeal()...");
		String a1UpdateSQL = null;
		String a1InsertSQL = null;
		String a23UpdateSQL = null;
		String a23InsertSQL = null;
		String whereClause_A23 = null;
		String whereClause_A1 = null;
		int updateFlag = 0;
		try {
			a1StatusMessage = null;
			a23StatusMessage = null;
			whereClause_A1 = Constants.VSP_REF_NO + " and " +  Constants.A1_REF_NO;
			whereClause_A23 = Constants.VSP_REF_NO + " and " +  Constants.A23_REF_NO;
			String dealSQL = QueryBuilder.UPDATE_DEAL + " " + Constants.VSP_REF_NO;
			//a1FormObj.setA1_RefNo(a1RefNo);
			activeA1RefNo = a1RefNo;
			activeA23RefNo = a23RefNo;
		
			if(a1ButtonClick == true){
				
				a1FormObj.setA1_RefNo(a1RefNo);
				a1FormObj.setReferenceNo(reference_No);
				}
			if(a23ButtonClick == true) {
					
					a23FormObj.setWorkshop_Ref_Number(a23RefNo);;
					a23FormObj.setReference_No(reference_No);
			}
			boolean a1ExistFlag = isA1Exist();
			boolean a23ExistFlag = isA23Exist();
				
					a1UpdateSQL = QueryBuilder.UPDATE_A1_WORKSHOP + " " +whereClause_A1;
					a1InsertSQL = QueryBuilder.INSERT_A1_WORKSHOP;
					a23UpdateSQL = QueryBuilder.UPDATE_A23_WORKSHOP + " " +whereClause_A23;
					a23InsertSQL = QueryBuilder.INSERT_A23_WORKSHOP;
					
					updateFlag = DealDAO.updateDeal(dealSQL,a1UpdateSQL,a1InsertSQL,a23UpdateSQL,a23InsertSQL,dealInfoObj, a1FormObj, a23FormObj,a1ExistFlag,a23ExistFlag,a1ButtonClick,a23ButtonClick);
					
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
		return updateFlag;
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
			jscript = null;
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
			if(a23FormList!=null) {
				if(a23FormList.size() > 0) {
				a23FormObj = a23FormList.get(0);
				}
			}
			
			if(reference_No > 0) {
				dealInfoObj.setReference_No(reference_No);
					boolean existDealFlag = DealDAO.isDealExist(sql,reference_No);
		           
					if(existDealFlag == false) {
						//Insert new deal
						int insertStatus = insertDeal();
					    if(insertStatus == 0) {
								System.out.println("Deal insert unsuccessful.");
								jscript= "error";
					    } else {					
							dealList.clear();
							a1FormList.clear();
							a23FormList.clear();
							dealList.add(dealInfoObj);
				
							if(a1statusFlag == true) {
						
								a1FormList.add(a1FormObj);
							}
							else {
								
								a1FormList.add(new A1Form());
								a1statusFlag = false;
							}
							if(a23statusFlag == true) {
								
								a23FormList.add(a23FormObj);
							}
							else {
								
								a23FormList.add(new A23Form());
								a23statusFlag = false;
							}
							page = "dealForm";
						
						}
						System.out.println("Page name after insert:"+page);
					}else {
						//update existing deal
						int updateFlag = updateDeal();
						
						if(updateFlag == 0) {
							System.out.println("Deal update unsuccessful.");
							jscript= "error";
						}
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
	 * Method to check an existing A23 workshop in a23_workshop_info table
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public boolean isA23Exist() throws Exception {
		 boolean a23ExistFlag = false;
		try {

			whereClause = Constants.VSP_REF_NO +" and " + Constants.A23_REF_NO;
			String sql = QueryBuilder.COUNT_A23 + " " + whereClause;

			int count =  DealDAO.isA23Exist(sql, dealInfoObj.getReference_No(), a23FormObj.getWorkshop_Ref_Number());
			if (count > 0) {
				a23ExistFlag =true;
			}
			else {
				a23ExistFlag = false;
			}
          
			
			
		} catch (Exception e) {
			System.out.println("Error in isA1Exist():" + e);
			e.printStackTrace();
		}
		return a23ExistFlag;
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
	 * Method to validate A23 form status
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public int validateA23Status(int reference_No, String a2_RefNo) throws Exception {
		int a23Count = 0;
		try {

			whereClause = Constants.VSP_REF_NO +" and " + Constants.A23_REF_NO + "and " +Constants.A23_STATUS ;
			String sql = QueryBuilder.COUNT_A23 + " " + whereClause;
	
			a23Count = DealDAO.isA23Exist(sql, reference_No, a2_RefNo);
	        			
			
		} catch (Exception e) {
			System.out.println("Error in validateA1Status():" + e);
			e.printStackTrace();
		}
		return a23Count;
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
		jscript = null;
		generateA1RefNo(false,0);
		System.out.println("New A1 Ref No:"+a1RefNo +a1statusFlag);
	
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
		jscript = null;
		System.out.println("A1 Ref No:"+a1RefNo);
		System.out.println("Ref No:"+reference_No);
		int a1Count = validateA1Status(reference_No,a1RefNo);
		System.out.println("a1Count:"+a1Count);
		if(a1Count > 0) {
			jscript = null;
			a1StatusMessage = null;
			a1FormList = new ArrayList<A1Form> ();
			a1FormList.add(new A1Form());
			generateA1RefNo(true,a1Count);
			
		}
		else {
		
			jscript ="pendingA1";
			System.out.println("jscript:"+jscript);
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
	public void historyListenerA1(ActionEvent e) throws Exception {
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
	/**
	 * Method to handle A23 history button listener
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void historyListenerA23(ActionEvent e) throws Exception {
		String buttonValue = (String)e.getComponent().getAttributes().get("value");
		System.out.println("buttonValue:"+buttonValue);
		if(a23FormList!=null) {
		for(A23Form a23form:a23FormList)
		{
			if(a23form.getWorkshop_Ref_Number().equals(buttonValue)) {
				int index = a23FormList.indexOf(a23form);
				System.out.println("index:"+index);
				a23FormList.remove(a23form);
				a23FormList.add(0,a23form);
				a23RefNo = a23form.getWorkshop_Ref_Number();
		}
			}
		}
			
	}
	/**
	 * Method to add a new comment to database
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void addComment()  {
		
		System.out.println("In Deal: Entering addComment()...");
		
		try {
			
			String sql = QueryBuilder.INSERT_COMMENT;
			int status = CommentsDAO.insertComments(sql,commentsObj);
			if (status<=0) {
			
					jscript ="database_error";
				}
			else {
				sql = QueryBuilder.SELECT_COMMENTS;
				commentsList = CommentsDAO.getComments(sql,reference_No);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("In Deal: Exit addComment()...");
	}
	/**
	 * Method to handle ADD WORKSHOP button click event
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void addWorkshop(ActionEvent e) throws Exception {
		a23ButtonClick = true;
		jscript = null;
		generatea23RefNo(false,0);
	
	}
	/**
	 * Method to generate new Workshop reference number
	 * 
	 * @param value
	 * @return void
	 * @throws Exception
	 */
	public void generatea23RefNo (boolean workshopStatus,int workshopCount) {
		
		if (workshopStatus == true) {
			
			a23RefNo = Constants.WORKSHOP_REF_NO_BASE+ String.format("%02d", workshopCount+1);
			
		}
		else {
			a23RefNo = Constants.WORKSHOP_REF_NO_START;
			if(a23ButtonClick == true) {
				a23statusFlag = true;
			}
			else {
				a23statusFlag = true;
			}
			
		}
		System.out.println("Workshop Ref No:"+a23RefNo);
	
		
}
/**
 * Method to handle NEW WORKSHOP button click event
 * 
 * @param value
 * @return void
 * @throws Exception
 */
public void addNewWorkshop(ActionEvent e) throws Exception {
	a23ButtonClick = true;
	jscript = null;
	int wsCount = validateA23Status(reference_No,a23RefNo);
	if(wsCount > 0) {
		jscript = null;
		a23StatusMessage = null;
		a23FormList = new ArrayList<A23Form> ();
		a23FormList.add(new A23Form());
		generatea23RefNo(true,wsCount);
		
	}
	else {
	
		jscript ="pendingA23";
	}
	
}
/**
 * Method to check A1 status of a deal
 * 
 * @param value
 * @return void
 * @throws Exception
 */
public boolean hasA23Exist(int referenceNo) throws Exception {
	boolean status = false;
	int a23Count = 0;
	try {

		whereClause = Constants.VSP_REF_NO ;
		String sql = QueryBuilder.COUNT_A23 + " " + whereClause;
	
		a23Count = DealDAO.hasA23Exist(sql, referenceNo);
       if(a23Count> 0 ) {
    	  status = true; 
       }
       else {
    	   status = false; 
       }

		
	} catch (Exception e) {
		System.out.println("Error in hasA23Exist():" + e);
		e.printStackTrace();
	}
	return status;
}


}
