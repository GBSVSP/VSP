package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vsp.model.DealInfo;
import com.vsp.util.DBConnect;
import com.vsp.util.QueryBuilder;
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
				dealInfo.setDeal_Id(rs.getInt(1));
				dealInfo.setVSP_Ref_No(rs.getString(2));
				dealInfo.setSector_Id(rs.getInt(3));
				dealInfo.setSector_Name(rs.getString(4));
				dealInfo.setIndustry_Id(rs.getInt(5));
				dealInfo.setIndustry_Name(rs.getString(6));
				dealInfo.setIMT_Id(rs.getInt(7));
				dealInfo.setIMT_Name(rs.getString(8));
				dealInfo.setSales_Connect_Opp_No(rs.getString(9));
				dealInfo.setCustomer_Name(rs.getString(10));
				dealInfo.setOpportunity_Name(rs.getString(11));
				dealInfo.setOpportunity_Owner(rs.getString(12));
				dealInfo.setOpportunity_Description(rs.getString(13));
				dealInfo.setAdditional_Contacts(rs.getString(14));
				dealInfo.setTCV(rs.getDouble(15));
				dealInfo.setDeal_Size_Range(rs.getString(16));
				dealInfo.setVSP_Document_Link(rs.getString(17));
				dealInfo.setIPPF_Number(rs.getString(18));
				dealInfo.setGBS_GTS_Led(rs.getString(19));
				dealInfo.setOther_Linked_Opp_No(rs.getString(20));
				dealInfo.setSSM_State_Id(rs.getInt(21));
				dealInfo.setOpp_Business_Partners(rs.getString(22));
				dealInfo.setOpp_Close_Date(rs.getString(23));
				dealInfo.setBnP_Spent(rs.getDouble(24));
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

}