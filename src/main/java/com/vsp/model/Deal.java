package com.vsp.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.sun.javafx.collections.MappingChange.Map;
import com.vsp.dao.DealDAO;
import com.vsp.util.Constants;
import com.vsp.util.DBConnect;
import com.vsp.util.QueryBuilder;
/**
 * <p>
 * This the bean class for deal
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class Deal {
	private static final long serialVersionUID = 1094801825228386363L;
	private String searchType = Constants.DEFAULT_DEARCH_TYPE;
	private static  ArrayList<DealInfo> dealList ; 
	private String searchValue;
	private  static Connection con = null;
	private String  sql = null;
	ResultSet dealResult = null;
	private String whereClause = null;
	private boolean show = false;
	private int count;
	public Deal() throws Exception{
		
		con = DBConnect.getConnection();
		
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
	public void searchDeal() throws Exception {
		show = false;
		sql = QueryBuilder.SEARCH_DEAL+ ""+whereClause;
		dealList = DealDAO.getDealList(con,sql,searchValue);
		if(dealList.isEmpty()){
			show = true;
		}
		else {
			count = dealList.size();
		}
	}
	public List<String> getDeal(String value) throws Exception {

		List<String> tempList = new ArrayList<String>();
		
		try {
			
			if(searchType.equals(Constants.VSP_OPP_NO)) {
				sql = QueryBuilder.SELECT_VSP_REF_NO;
				whereClause = Constants.VSP_OPP_NO_SQL;
				
			}
			if(searchType.equals(Constants.CLIENT)) {
				sql = QueryBuilder.SELECT_CUSTOMER_NAME;
				whereClause = Constants.CLIENT_SQL;
			}
			if(searchType.equals(Constants.DEAL)) {
				sql = QueryBuilder.SELECT_DEAL_ID;
				whereClause = Constants.CLIENT_SQL;
			}
			if(searchType.equals(Constants.SALES_CONNECT_NO)) {
				sql = QueryBuilder.SELECT_SALES_CONNECT_OPP_NO;
				whereClause = Constants.SALES_CONNECT_NO_SQL;
			}
			
			dealResult = DealDAO.getDeal(con,sql);
			System.out.println("sql:"+sql);
			if(dealResult.next()){
			do {
				String data = dealResult.getString(1);	
				if (data.toLowerCase().startsWith(value.toLowerCase())) {
					tempList.add(data);
		        }
				
		}while (dealResult.next());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return tempList;
}
	
	public void typeChanged(AjaxBehaviorEvent e) throws Exception {
		
		UIComponent component = (UIComponent) e.getSource();
		searchType = (String) component.findComponent("searchType")
				.getAttributes().get("value");
		
		System.out.println("type:" + searchType);
		
	}
	public String openDeal(int dealId) {
		
		System.out.println("dealId>"+dealId);
		//dealList.clear();
		DealInfo deal = new DealInfo();
		for(int i=0;i<dealList.size();i++) {
			deal = dealList.get(i);
			if(!(deal.getDeal_Id()==dealId)) {
				dealList.remove(i);
			}
		}
		return "dealForm";
		
	}
}
