package com.vsp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.vsp.model.DealInfo;
import com.vsp.util.DBConnect;
/**
 * <p>
 * This the DAO class for deal
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 13/Nov/2017
 */

public class DealDAO {
	
	static Connection con = DBConnect.getInstance().getConnInst();

	public static  ResultSet getDeal(String sql) throws Exception {
		
		Statement st = null;
		ResultSet rs = null;
			try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			rs = st.executeQuery(sql);
			
						
		} catch (SQLException ex) {
			System.out.println("Error in getDeal():"+ex);
			ex.printStackTrace();
			
		} 
	return rs;
	}
	public static ArrayList<DealInfo> getDealList(String sql,String param) throws Exception {
		
		PreparedStatement ps = null;
		ArrayList<DealInfo> dealInfoList = new ArrayList<DealInfo>();
	
		try {
			
			ps = con.prepareStatement(sql);
			ps.setString(1,param);	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				DealInfo dealInfo = new DealInfo();
				
				dealInfo.setReference_No(rs.getInt(1));
				dealInfo.setSector_Id(rs.getInt(2));
				dealInfo.setSector_Name(rs.getString(3));
				dealInfo.setIndustry_Id(rs.getInt(4));
				dealInfo.setIndustry_Name(rs.getString(5));
				dealInfo.setImt_Id(rs.getInt(6));
				dealInfo.setImt_Name(rs.getString(7));
				dealInfo.setSc_No(rs.getString(8));
				dealInfo.setCustomer_Name(rs.getString(9));
				dealInfo.setOpportunity_Name(rs.getString(10));
				dealInfo.setOpportunity_Owner(rs.getString(11));
				dealInfo.setOpportunity_Description(rs.getString(12));
				dealInfo.setAdditional_Contacts(rs.getString(13));
				dealInfo.setTcv(rs.getDouble(14));
				dealInfo.setPotential_TCV(rs.getString(15));
				dealInfo.setBox_Link(rs.getString(16));
				dealInfo.setIppf_Number(rs.getString(17));
				dealInfo.setGbs_gts_Led(rs.getString(18));
				dealInfo.setOther_Linked_Opp_No(rs.getString(19));
				dealInfo.setSsm_Stage_Id(rs.getInt(20));
				dealInfo.setSsm_Stage(rs.getInt(21));
				dealInfo.setSales_Connect_No(rs.getString(22));
				dealInfo.setSc_Opp_Owner(rs.getString(23));
				dealInfo.setDecision_Date(rs.getDate(24).toString());
				dealInfo.setBnp_Spent(rs.getDouble(25));
				dealInfo.setSc_Imt_Id(rs.getInt(26));
				dealInfoList.add(dealInfo);	
				
							
				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
	return dealInfoList;
	}
public static ArrayList<String> getOptionList(String sql) throws Exception {
		
		PreparedStatement ps = null;
		ArrayList<String> ledByList = new ArrayList<String>();
	
		try {
			
			ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ledByList.add(rs.getString(2));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
	return ledByList;
	}
public static int getReferenceNumber(String sql) {
   
	PreparedStatement ps = null;
	int referenceNum = 0;
	try {
		
		ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			referenceNum = rs.getInt(1);
		}
		
	} catch (SQLException ex) {
		ex.printStackTrace();
		
	}
return referenceNum;
}
public static int insertDeal(String sql,DealInfo dealInfo) throws Exception {
	
	PreparedStatement ps = null;
	int insertFlag =0;
	System.out.println("selected:"+dealInfo.getImt_Id());
	try {
		
		ps = con.prepareStatement(sql);
		
		ps.setInt(1, dealInfo.getReference_No());
		ps.setInt(2, dealInfo.getSector_Id());
		ps.setInt(3, dealInfo.getIndustry_Id());
		ps.setInt(4, dealInfo.getImt_Id());
		ps.setString(5, dealInfo.getSc_No());
		ps.setString(6, dealInfo.getCustomer_Name());
		ps.setString(7, dealInfo.getOpportunity_Name());
		ps.setString(8, dealInfo.getOpportunity_Owner());
		ps.setString(9, dealInfo.getOpportunity_Description());
		ps.setString(10, dealInfo.getAdditional_Contacts());
		ps.setDouble(11, dealInfo.getTcv());
		ps.setString(12, dealInfo.getPotential_TCV());
		ps.setString(13, dealInfo.getBox_Link());
		ps.setString(14, dealInfo.getIppf_Number());
		ps.setString(15, dealInfo.getGbs_gts_Led());
		ps.setString(16, dealInfo.getOther_Linked_Opp_No());
		ps.setInt(17, dealInfo.getSsm_Stage_Id());
		ps.setInt(18, dealInfo.getSsm_Stage());
		ps.setString(19, dealInfo.getSales_Connect_No());
		ps.setString(20, dealInfo.getSc_Opp_Owner());
		ps.setDate(21, java.sql.Date.valueOf(dealInfo.getDecision_Date()));
		ps.setDouble(22, dealInfo.getBnp_Spent());
		ps.setDouble(23, dealInfo.getSc_Imt_Id());
	
		
		insertFlag = ps.executeUpdate();
		
		
	} catch (SQLException ex) {
		ex.printStackTrace();
		
	}
  return insertFlag;
}

public static int updateDeal(String sql, DealInfo dealInfo) throws Exception {
	
	PreparedStatement ps = null;
	//ArrayList<DealInfo> dealInfoList = new ArrayList<DealInfo>();
    int updateFlag =0;
    
	try {
		
		ps = con.prepareStatement(sql);
		
		//Set clause
		ps.setInt(1, dealInfo.getSector_Id());
		ps.setInt(2, dealInfo.getIndustry_Id());
		ps.setInt(3, dealInfo.getImt_Id());
		ps.setString(4, dealInfo.getSc_No());
		ps.setString(5, dealInfo.getCustomer_Name());
		ps.setString(6, dealInfo.getOpportunity_Name());
		ps.setString(7, dealInfo.getOpportunity_Owner());
		ps.setString(8, dealInfo.getOpportunity_Description());
		ps.setString(9, dealInfo.getAdditional_Contacts());
		ps.setDouble(10, dealInfo.getTcv());
		ps.setString(11, dealInfo.getPotential_TCV());
		ps.setString(12, dealInfo.getBox_Link());
		ps.setString(13, dealInfo.getIppf_Number());
		ps.setString(14, dealInfo.getGbs_gts_Led());
		ps.setString(15, dealInfo.getOther_Linked_Opp_No());
		ps.setInt(16, dealInfo.getSsm_Stage_Id());
		ps.setInt(17, dealInfo.getSsm_Stage());
		ps.setString(18, dealInfo.getSales_Connect_No());
		ps.setString(19, dealInfo.getSc_Opp_Owner());
		DateFormat format = new SimpleDateFormat("yyy-mm-dd", Locale.ENGLISH);
		java.sql.Date date = (Date) format.parse(dealInfo.getDecision_Date());
		ps.setDate(20, date);
		ps.setDouble(21, dealInfo.getBnp_Spent());
		ps.setDouble(22, dealInfo.getSc_Imt_Id());
		
		//where clause
		ps.setInt(23, dealInfo.getReference_No());
		
		updateFlag = ps.executeUpdate();
		
	} catch (SQLException ex) {
		ex.printStackTrace();
		
	}
	
 return updateFlag;
 }
}