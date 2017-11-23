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
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

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
@SessionScoped
public class Deal implements Serializable {
	private static final long serialVersionUID = 1094801825228386363L;
	private String searchType = Constants.DEFAULT_SEARCH_TYPE;
	private static ArrayList<DealInfo> dealList = null ;
	
	private static HashMap<Integer,String> optionMap;
	private String searchValue;

	private static String sql = null;
	private int reference_No ;
	private String whereClause = null;
	private boolean show = false;
	private int count;
	private DealInfo dealInfo ;
	
	public Deal() throws Exception {

	}
	@ManagedProperty(value="#{dealInfo}")
	private DealInfo dealInfoObj = new DealInfo();

	public void setDealInfoObj(DealInfo dealInfo) {
		this.dealInfoObj = dealInfo;
	}
	//generating the reference number in YYYY+4 digit serial number format
	public int getReference_No() {
		Calendar now = Calendar.getInstance();  
		int year = now.get(Calendar.YEAR); 
		sql = QueryBuilder.SELECT_MAX_VSP_REF_NO;
		int currentRefNo = DealDAO.getReferenceNumber(sql);
		if(Integer.parseInt(Integer.toString(currentRefNo).substring(0, 4))==year) {
			currentRefNo ++;
			reference_No = currentRefNo;
		}
		else {
			reference_No = Integer.parseInt(String.valueOf(year)+Constants.VSP_REF_NO_START);
		}
		dealInfoObj.setReference_No(reference_No);
		return reference_No;
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

	public ArrayList<DealInfo> getDealList() {

		return dealList;
	}
	
	//searching deals
	public void searchDeal() throws Exception {
		show = false;
		sql = QueryBuilder.SEARCH_DEAL + "" + whereClause;
		dealList = DealDAO.getDealList(sql, searchValue);
		if (dealList.isEmpty()) {
			show = true;
		} else {
			count = dealList.size();
		}
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
	public String openDeal(int referenceNo) {

		System.out.println("referenceNo>" + referenceNo);
		dealInfo = new DealInfo();
		for (int i = 0; i < dealList.size(); i++) {
			dealInfo = dealList.get(i);
			if (!(dealInfo.getReference_No() == referenceNo)) {
				dealList.remove(i);
			}
		}
		System.out.println("size:" + dealList.size());
		return "dealForm";

	}

	// Invoked from NEW DEAL FORM button
	public String newDeal() {
		
		if (dealList != null) {
			System.out.println("inside newDeal");
			dealList.clear();
		}
		return "newDeal";

	}

	// Populating Led By drop down
	public ArrayList<SelectItem> getLedByList() {
		whereClause = Constants.LED_BY_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
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
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
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
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
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
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
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
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
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
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
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
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating IMT drop down
	public  List<SelectItem> getIMTList() {
		sql = QueryBuilder.SELECT_IMT;
		ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
		try {
			optionMap = FormDAO.getOptionList(sql);
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}

	// Populating SCIMT drop down
		public  List<SelectItem> getSCIMTList() {
			sql = QueryBuilder.SELECT_IMT;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
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
			for (Map.Entry<Integer,String> entry : optionMap.entrySet()) {
				optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return optionList;
	}
	
	/**
	 * Method to add a new deal to database
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public void insertDeal() throws Exception {
		
		try {
			
			String sql = QueryBuilder.INSERT_DEAL;
			
			int insertFlag = DealDAO.insertDeal(sql,dealInfoObj);
			
			if(insertFlag > 0) {
				System.out.println("Deal insert successful.");
			}else {
				System.out.println("Deal insert unsuccessful.");
			}
				
			
	     }catch(Exception e) {
			System.out.println("Error in insertDeal();:"+e);
			e.printStackTrace();
		 }
		
	}
	
	
    /**
     * Method to update an existing deal into database	
     * @param value
     * @return
     * @throws Exception
     */
	public void updateDeal(String value) throws Exception {

		try {
			    whereClause = Constants.VSP_REF_NO;
				sql = QueryBuilder.UPDATE_DEAL +" "+ whereClause;
			
			int updateFlag = DealDAO.updateDeal(sql, dealList.get(0));
			
			if(updateFlag > 0) {
				System.out.println("Deal update successful.");
			}else {
				System.out.println("Deal update unsuccessful.");
			}
						
		} catch(Exception e) {
			System.out.println("Error in updateDeal();:"+e);
			e.printStackTrace();
		}
		
		//return tempList;
}

}
