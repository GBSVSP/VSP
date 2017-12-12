package com.vsp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vsp.dao.UserMgmtDAO;
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
public class CommonUtils {
  
	private static Connection con = DBConnect.getInstance().getConnInst();
	private static Map<String, Object> sessionMap;
	private static FacesContext context;
	private static HashMap<Integer, String> optionMap;
	private static String sql = null;
	private static String whereClause = null;
	
	
	/** Method to populate IMT drop down 
	 * 
	 * @return ArrayList<SelectItem>
	 */
		public static ArrayList<SelectItem> getImtList() {
			context  = FacesContext.getCurrentInstance();
			sessionMap = context.getExternalContext().getSessionMap();
			ArrayList<SelectItem> imtList = new ArrayList<SelectItem>();
			
			if(sessionMap.containsKey("imtListInSession")) {
				imtList = (ArrayList<SelectItem>) sessionMap.get("imtListInSession");
			}else {	
			   sql = QueryBuilder.SELECT_IMT;
		
		 	   try {
				optionMap = getListInMap(sql);
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
		
		/** Method to populate Role drop down
		 * 
		 * @return ArrayList<SelectItem>
		 */
		public static ArrayList<SelectItem> getRoleList() {
			FacesContext context  = FacesContext.getCurrentInstance();
			sessionMap = context.getExternalContext().getSessionMap();
			ArrayList<SelectItem> roleList = new ArrayList<SelectItem>();
			
			if(sessionMap.containsKey("roleListInSession")) {
				roleList = (ArrayList<SelectItem>) sessionMap.get("roleListInSession");
				
			}else {	
			whereClause = Constants.IS_ACTIVE_CONDITION;
			sql = QueryBuilder.SELECT_USER_ROLE + " " + whereClause;
			
			try {
				optionMap = getListInMap(sql);
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
		
	
		/** Method to populate Sentiment drop down
		 * 
		 * @return ArrayList<SelectItem>
		 */
		public static ArrayList<SelectItem> getSentimentList() {
			context  = FacesContext.getCurrentInstance();
			sessionMap = context.getExternalContext().getSessionMap();
			ArrayList<SelectItem> sentimentList = new ArrayList<SelectItem>();
			
			if(sessionMap.containsKey("sentimentListInSession")) {
				sentimentList = (ArrayList<SelectItem>) sessionMap.get("sentimentListInSession");
				
			}else {	
			whereClause = Constants.SENTIMENT;
			sql = QueryBuilder.SELECT_PARTICIPANT_SENTIMENT + " " + whereClause;
			
			try {
				optionMap = getListInMap(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					sentimentList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
				sessionMap.put("sentimentListInSession", sentimentList);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			}
			return sentimentList;
		}
		

	/**
	 * Method to populate any List from Database
	 * 
	 * @param sql
	 * @return HashMap<Integer, String>
	 * @throws Exception
	 */

	public static HashMap<Integer, String> getListInMap(String sql) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap<Integer, String> listByMap = new HashMap<Integer, String>();

		try {

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				listByMap.put(rs.getInt(1), rs.getString(2));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}finally {
			ps.close();
			rs.close();
		}

		return listByMap;

	}
	
	
	
}
