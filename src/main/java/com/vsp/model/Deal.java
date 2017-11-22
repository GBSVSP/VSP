package com.vsp.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;

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
	private static ArrayList<String> ledByList;
	private static ArrayList<String> potentialTCVList;
	private static ArrayList<String> a1StatusList;
	private static ArrayList<String> nextStepList;
	private static ArrayList<String> shouldweSellList;
	private static ArrayList<String> canweSellList;
	private static ArrayList<String> a1EvaluationList;
	private static ArrayList<String> IMTList;
	private static ArrayList<String> sectorList;

	private String searchValue;

	private static String sql = null;

	private String whereClause = null;
	private boolean show = false;
	private int count;

	public Deal() throws Exception {

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
		DealInfo deal = new DealInfo();
		for (int i = 0; i < dealList.size(); i++) {
			deal = dealList.get(i);
			if (!(deal.getReference_No() == referenceNo)) {
				dealList.remove(i);
			}
		}
		System.out.println("size:" + dealList.size());
		return "dealForm";

	}

	// Invoked from NEW DEAL FORM button
	public String newDeal() {
		System.out.println("inside newDeal");
		if (dealList != null) {
			dealList.clear();
		}
		return "newDeal";

	}

	// Populating Led By drop down
	public ArrayList<String> getLedByList() {
		whereClause = Constants.LED_BY_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

		try {
			ledByList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return ledByList;
	}

	// Populating Potential TCV drop down
	public ArrayList<String> getPotentialTCVList() {
		whereClause = Constants.PPOTENTIAL_TCV_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

		try {
			potentialTCVList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return potentialTCVList;
	}

	// Populating A1 Status drop down
	public ArrayList<String> getA1StatusList() {
		whereClause = Constants.A1STATUS_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

		try {
			a1StatusList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return a1StatusList;
	}

	// Populating Next Step drop down
	public ArrayList<String> getNextStepList() {
		whereClause = Constants.NEXT_STEP_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

		try {
			nextStepList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return nextStepList;
	}

	// Populating Should we sell drop down
	public ArrayList<String> getShouldweSellList() {
		whereClause = Constants.SHOULD_WE_SELL_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

		try {
			shouldweSellList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return shouldweSellList;
	}

	// Populating can we sell drop down
	public ArrayList<String> getCanweSellList() {
		whereClause = Constants.CAN_WE_SELL_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

		try {
			canweSellList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return canweSellList;
	}

	// Populating Evaluation drop down
	public ArrayList<String> getA1EvaluationList() {
		whereClause = Constants.A1EVALUATION_CONDITION;
		sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

		try {
			a1EvaluationList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return a1EvaluationList;
	}

	// Populating IMT drop down
	public ArrayList<String> getIMTList() {
		sql = QueryBuilder.SELECT_IMT;
		try {
			IMTList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return IMTList;
	}

	// Populating Sector drop down
	public ArrayList<String> getSectorList() {
		sql = QueryBuilder.SELECT_SECTOR;
		try {
			sectorList = FormDAO.getOptionList(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return sectorList;
	}

}
