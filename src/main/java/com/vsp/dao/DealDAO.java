package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
				dealInfo.setDecision_Date(rs.getString(24));
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

}