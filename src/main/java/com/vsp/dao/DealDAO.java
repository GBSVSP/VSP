package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.vsp.model.A1Form;
import com.vsp.model.A23Form;
import com.vsp.model.DealInfo;
import com.vsp.util.DBConnect;
import com.vsp.util.SessionUtils;

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

	public static ResultSet getDeal(String sql) throws Exception {
		System.out.println("sql:" + sql);
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = st.executeQuery(sql);

		} catch (SQLException ex) {
			System.out.println("Error in getDeal():" + ex);
			ex.printStackTrace();

		}
		return rs;
	}

	public static List<DealInfo> getDealList(String sql, String param) throws Exception {

		PreparedStatement ps = null;
		List<DealInfo> dealInfoList = new ArrayList<DealInfo>();

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, param);
			ResultSet rs = ps.executeQuery();
			DealInfo dealInfo = null;
			while (rs.next()) {
				dealInfo = new DealInfo();
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
				
				if (rs.getDate(24) != null) {
					dealInfo.setDecision_Date(rs.getDate(24).toString());
				}
				else {
					dealInfo.setDecision_Date("");
				}
				dealInfo.setBnp_Spent(rs.getDouble(25));
				dealInfo.setSc_Imt_Id(rs.getInt(26));
				dealInfoList.add(dealInfo);
				
			}
			

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return dealInfoList;
	}

	public static DealInfo getDealInfo(String sql, int referenceNo) throws Exception {

		PreparedStatement ps = null;
		DealInfo dealInfo = new DealInfo();
		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, referenceNo);
			ResultSet rs = ps.executeQuery();
		
			while (rs.next()) {
				
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
				
				if (rs.getDate(24) != null) {
					dealInfo.setDecision_Date(rs.getDate(24).toString());
				}
				else {
					dealInfo.setDecision_Date("");
				}
				dealInfo.setBnp_Spent(rs.getDouble(25));
				dealInfo.setSc_Imt_Id(rs.getInt(26));
								
			}
			

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return dealInfo;
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

	public static int insertDealOnly(String dealSQL, String a1SQL, DealInfo dealInfo) throws Exception {

		System.out.println("In DealDAO: Entering insertDealOnly()...");
		PreparedStatement ps = null;
		int insertFlag = 0;
		System.out.println("Deal Ref. No.: " + dealInfo.getReference_No());

		try {

			con.setAutoCommit(false);
			ps = con.prepareStatement(dealSQL);

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
	
			if (!(dealInfo.getDecision_Date().isEmpty()) ) {
				ps.setDate(21, java.sql.Date.valueOf(dealInfo.getDecision_Date()));
			} else {
				ps.setDate(21, null);
			}
			ps.setDouble(22, dealInfo.getBnp_Spent());
			ps.setDouble(23, dealInfo.getSc_Imt_Id());
			ps.setString(24, SessionUtils.getUserName());

			insertFlag = ps.executeUpdate();
			System.out.println("Deal insert status:"+insertFlag);
			
			if(insertFlag > 0) {
				con.commit();
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		System.out.println("In DealDAO: Exiting insertDealOnly()...");
		return insertFlag;
	}
	public static int insertDeal(String dealSQL, String a1SQL, String a23SQL, DealInfo dealInfo, A1Form a1Form, A23Form a23Form, boolean a1statusFlag, boolean a23statusFlag) throws Exception {

		System.out.println("In DealDAO: Entering insertDeal()..."+a1statusFlag);
		PreparedStatement ps = null;
		int insertFlag = 0;
		System.out.println("Deal Ref. No.: " + dealInfo.getReference_No());

		try {

			con.setAutoCommit(false);
			ps = con.prepareStatement(dealSQL);

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
			System.out.println("getDecision_Date().isEmpty():"+dealInfo.getDecision_Date());
			if (!(dealInfo.getDecision_Date().isEmpty()) ) {
				ps.setDate(21, java.sql.Date.valueOf(dealInfo.getDecision_Date()));
			} else {
				ps.setDate(21, null);
			}
			ps.setDouble(22, dealInfo.getBnp_Spent());
			ps.setDouble(23, dealInfo.getSc_Imt_Id());
			ps.setString(24, SessionUtils.getUserName());

			insertFlag = ps.executeUpdate();
			System.out.println("Deal insert status:"+insertFlag);
		
			if(insertFlag > 0) {
		
				if(a1statusFlag == true) {
					insertFlag = 0;
					insertFlag = insertA1(a1SQL, a1Form, con);
					if(insertFlag > 0) {	
						if(a23statusFlag == true) {
							insertFlag = 0;
							insertFlag = insertA23(a23SQL, a23Form, con);
						}
					}
				}
				else {
					if(a23statusFlag == true) {
						insertFlag = 0;
						insertFlag = insertA23(a23SQL, a23Form, con);
					}
				}
				
			}
			if(insertFlag > 0) {
				con.commit();
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		System.out.println("In DealDAO: Exiting insertDeal()...");
		return insertFlag;
	}
	// inserting A1 Workshop
	public static int insertA1(String a1SQL,A1Form a1Form,Connection con) throws Exception {

		System.out.println("In DealDAO: Entering insertA1()...");
		PreparedStatement ps = null;
		int insertFlag = 0;
			try {

				ps = con.prepareStatement(a1SQL);
				ps.setInt(1,a1Form.getReferenceNo());
				ps.setString(2, a1Form.getA1_RefNo());
				ps.setString(3, a1Form.getA1_Status());
										
				if (a1Form.getA1_Complete()!=null && !(a1Form.getA1_Complete().isEmpty())) {
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
					Date decisionDate = format.parse(a1Form.getA1_Complete());
					java.sql.Date decisionSqlDate = new java.sql.Date(decisionDate.getTime());
					ps.setDate(4, decisionSqlDate);
				} else {
					ps.setDate(4, null);
				}
				ps.setString(5, a1Form.getA1_Manager());
				ps.setString(6, a1Form.getA1_NextStep());
				ps.setString(7, a1Form.getA1_ShouldSell());
				ps.setString(8, a1Form.getA1_CanSell());
				ps.setString(9, a1Form.getA1_Evaluation());
				ps.setInt(10, a1Form.getA1_Pursuit());
				ps.setString(11, SessionUtils.getUserName());
				insertFlag = ps.executeUpdate();
				System.out.println("A1Form insert status:"+insertFlag);
		
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		System.out.println("In DealDAO: Exiting insertA1()...");
		return insertFlag;
	}
//inserting A23 form
		public static int insertA23(String a23SQL,A23Form a23Form,Connection con) throws Exception {

			System.out.println("In DealDAO: Entering insertA23()...");
			PreparedStatement ps = null;
			int insertFlag = 0;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				try {

					ps = con.prepareStatement(a23SQL);
					
					ps.setInt(1,a23Form.getReference_No());
					ps.setString(2, a23Form.getWorkshop_Ref_Number());
						
					if (a23Form.getWorkshop_CompleteDate()!=null && !(a23Form.getWorkshop_CompleteDate().isEmpty())) {
						Date completeDate = format.parse(a23Form.getWorkshop_CompleteDate());
						java.sql.Date completeSqlDate = new java.sql.Date(completeDate.getTime());
						ps.setDate(3, completeSqlDate);
					} else {
						ps.setDate(3, null);
					}
					
					ps.setString(4, a23Form.getWorkshop_DealCoach());
					ps.setString(5, a23Form.getWorkshop_Status());
					ps.setString(6, a23Form.getWorkshop_Length());
					ps.setString(7, a23Form.getWorkshop_DealCoachEval());
					ps.setString(8, a23Form.getWorkshop_DealCoachJustification());
					ps.setString(9, a23Form.getWorkshop_ShouldSell());
					ps.setString(10, a23Form.getWorkshop_CanSell());
					ps.setInt(11, a23Form.getWorkshop_Pursuit());
					ps.setString(12, a23Form.getEvaluation());
					
				    if (a23Form.getBriefingCallDate()!=null && !(a23Form.getBriefingCallDate().isEmpty())) {
							
							Date briefingDate = format.parse(a23Form.getBriefingCallDate());
							java.sql.Date briefingSqlDate = new java.sql.Date(briefingDate.getTime());
							ps.setDate(13, briefingSqlDate);
					} else {
							ps.setDate(13, null);
					}
	                    
			
	               if (a23Form.getNextFollowUpCallDate()!=null && !(a23Form.getNextFollowUpCallDate().isEmpty())) {
							
							Date nextFollowUpDate = format.parse(a23Form.getNextFollowUpCallDate());
							java.sql.Date nextFollowUpSqlDate = new java.sql.Date(nextFollowUpDate.getTime());
							ps.setDate(14, nextFollowUpSqlDate);
				   } else {
							ps.setDate(14, null);
				   }
	
					
					if(a23Form.getWorkshop_ModulesRun().length != 0) {
					    String[] modulesRun = a23Form.getWorkshop_ModulesRun();
						
						String modules = "";
						
					    for(int i = 0; i < modulesRun.length; i++) {
					    	
					    	if(i ==0)
					    	  modules = modulesRun[i].trim(); 
					    	else 
					    	  modules= modules+ " , " + modulesRun[i].trim(); 
					    }
					  
						ps.setString(15, modules);
						
					} else {
					     ps.setString(15, "");
					}
					
					ps.setString(16, a23Form.getWorkshop_IBMBelovedDeal());
					ps.setDouble(17, a23Form.getWorkshop_AnticipatedPowerBase1());
					ps.setDouble(18, a23Form.getWorkshop_AnticipatedPowerBase2());
					ps.setDouble(19, a23Form.getWorkshop_ActualPowerBase());
					ps.setString(20, a23Form.getWorkshop_AnticipatedCompetitor1());
					ps.setString(21, a23Form.getWorkshop_AnticipatedCompetitor2());
					ps.setString(22, a23Form.getWorkshop_ActualCompetitor());
					ps.setString(23, a23Form.getWorkshop_ClientAttended());
					ps.setString(24, a23Form.getWorkshop_Consortium());
					ps.setString(25, SessionUtils.getUserName());
										
					insertFlag = ps.executeUpdate();
					System.out.println("A1Form insert status:"+insertFlag);
			
			} catch (SQLException ex) {
				ex.printStackTrace();

			}

			System.out.println("In DealDAO: Exiting insertA23()...");
			return insertFlag;
		}
	public static int updateDeal(String dealSQL, String a1SQL, String a23SQL, DealInfo dealInfo, A1Form a1Form, A23Form a23Form, boolean a1ExistFlag, boolean a23ExistFlag) throws Exception {

		System.out.println("In DealDAO: Entering updateDeal()...");
		PreparedStatement ps = null;
		int updateFlag = 0;
		System.out.println("customer name:" + dealInfo.getCustomer_Name());
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(dealSQL);
			
			// Set clause
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
		
			if (dealInfo.getDecision_Date()!=null && !(dealInfo.getDecision_Date().isEmpty())) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date decisionDate = format.parse(dealInfo.getDecision_Date());
				java.sql.Date decisionSqlDate = new java.sql.Date(decisionDate.getTime());
				ps.setDate(20, decisionSqlDate);
			} else {
				ps.setDate(20, null);
			}

			ps.setDouble(21, dealInfo.getBnp_Spent());
			ps.setDouble(22, dealInfo.getSc_Imt_Id());
			ps.setString(23, SessionUtils.getUserName());

			// where clause
			System.out.println("dealInfo.getReference_No():"+dealInfo.getReference_No());
			ps.setInt(24, dealInfo.getReference_No());

			updateFlag = ps.executeUpdate();
			
			if(updateFlag > 0) {
				
				if(a1ExistFlag == true) {
					updateFlag = 0;
					updateFlag = updateA1(a1SQL, a1Form, con);
					if(updateFlag > 0) {
						
						if(a23ExistFlag == true) {
							updateFlag = 0;
							updateFlag = updateA23(a23SQL, a23Form, con);
						}
					}
				}
				else {
					if(a23ExistFlag == true) {
						updateFlag = 0;
						updateFlag = updateA23(a23SQL, a23Form, con);
					}
				}
				
			}
				
			
			if(updateFlag > 0) {
				con.commit();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		System.out.println("In DealDAO: Exiting updateDeal()...");
		return updateFlag;
	}
	//update A1 workshop
	public static int updateA1(String a1SQL, A1Form a1Form, Connection con) throws Exception {

		System.out.println("In DealDAO: Entering updateA1()...");
		PreparedStatement ps = null;
		int updateFlag = 0;
		try {
			
				ps = con.prepareStatement(a1SQL);
				ps.setString(1, a1Form.getA1_Status());
				if (!(a1Form.getA1_Complete().isEmpty())) {
					ps.setDate(2, java.sql.Date.valueOf(a1Form.getA1_Complete()));
				} else {
					ps.setDate(2, null);
				}
				ps.setString(3, a1Form.getA1_Manager());
				ps.setString(4, a1Form.getA1_NextStep());
				ps.setString(5, a1Form.getA1_ShouldSell());
				ps.setString(6, a1Form.getA1_CanSell());
				ps.setString(7, a1Form.getA1_Evaluation());
				ps.setInt(8, a1Form.getA1_Pursuit());
				ps.setString(9, SessionUtils.getUserName());
				ps.setInt(10,a1Form.getReferenceNo());
				ps.setString(11, a1Form.getA1_RefNo());
				updateFlag = ps.executeUpdate();	
				
			}
		catch (SQLException ex) {
			ex.printStackTrace();

		}
		System.out.println("In DealDAO: Exiting updateA1()...");
		return updateFlag;
	}
	//update A23 workshop
		public static int updateA23(String a23SQL, A23Form a23Form, Connection con) throws Exception {

			System.out.println("In DealDAO: Entering updateA1()...");
			PreparedStatement ps = null;
			int updateFlag = 0;
			try {
				
					ps = con.prepareStatement(a23SQL);
					
					updateFlag = ps.executeUpdate();	
					
				}
			catch (SQLException ex) {
				ex.printStackTrace();

			}
			System.out.println("In DealDAO: Exiting updateA1()...");
			return updateFlag;
		}
	public static int updateDeal_InsertA123(String dealSQL, String a1SQL,String a23SQL, DealInfo dealInfo, A1Form a1Form,A23Form a23Form,boolean a1statusFlag,boolean a23statusFlag) throws Exception {

		System.out.println("In DealDAO: Entering updateDeal()...");
		PreparedStatement ps = null;
		int updateFlag = 0;
		System.out.println("customer name:" + dealInfo.getCustomer_Name());
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(dealSQL);
			
			// Set clause
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
		
			if ( dealInfo.getDecision_Date()!=null &&!(dealInfo.getDecision_Date().isEmpty())) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date decisionDate = format.parse(dealInfo.getDecision_Date());
				java.sql.Date decisionSqlDate = new java.sql.Date(decisionDate.getTime());
				ps.setDate(20, decisionSqlDate);
			} else {
				ps.setDate(20, null);
			}

			ps.setDouble(21, dealInfo.getBnp_Spent());
			ps.setDouble(22, dealInfo.getSc_Imt_Id());
			ps.setString(23, SessionUtils.getUserName());

			// where clause
			System.out.println("dealInfo.getReference_No():"+dealInfo.getReference_No());
			ps.setInt(24, dealInfo.getReference_No());

			updateFlag = ps.executeUpdate();
			
			if(updateFlag > 0) {
			if(a1statusFlag == true){
				updateFlag = 0;
				ps = con.prepareStatement(a1SQL);
				updateFlag = insertA1(a1SQL,a1Form,con);
				if(updateFlag > 0) {
					
					if(a23statusFlag == true) {
						updateFlag = 0;
						updateFlag = insertA23(a23SQL, a23Form, con);
					}
				}
			}
			else {
				if(a23statusFlag == true) {
					updateFlag = 0;
					updateFlag = insertA23(a23SQL, a23Form, con);
				}
			}
			
		}
			
			if(updateFlag > 0) {
				con.commit();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		System.out.println("In DealDAO: Exiting updateDeal()...");
		return updateFlag;
	}
	public static boolean isDealExist(String sql, int dealRefNo) throws Exception {

		System.out.println("In DealDAO: Entering isDealExist()...");
		PreparedStatement ps = null;
		boolean existDealFlag = false;
		int count =0;
		System.out.println("Deal Ref. No.: " + dealRefNo);

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, dealRefNo);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    count = rs.getInt(1);
			}
			
			if(count==1) {
				existDealFlag = true;
			}else {
				existDealFlag = false;
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("In DealDAO: Exiting isDealExist()...");
		return existDealFlag;
	}
	public static int isA1Exist(String sql, int ref_No, String a1RefNo) throws Exception {

		System.out.println("In DealDAO: Entering isA1Exist()...");
		PreparedStatement ps = null;
			int count = 0;
				try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ref_No);
			ps.setString(2, a1RefNo);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    count = rs.getInt(1);
			}
			
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("In DealDAO: Exiting isA1Exist()...");
		return count;
	}
	public static int hasA1Exist(String sql, int ref_No) throws Exception {

		System.out.println("In DealDAO: Entering hasA1Exist()...");
		PreparedStatement ps = null;
	
		int count = 0;
		System.out.println("Reference No: " + ref_No);

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ref_No);
			
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    count = rs.getInt(1);
			}
			
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("In DealDAO: Exiting isA1Exist()...");
		return count;
	}

	public static ArrayList<A1Form> getA1List(String sql, int referenceNo) {
		PreparedStatement ps = null;
		ArrayList<A1Form> a1FormList = new ArrayList<A1Form>();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, referenceNo);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				A1Form a1Form = new A1Form();

				a1Form.setReferenceNo(rs.getInt(1));
				a1Form.setA1_RefNo(rs.getString(2));
				a1Form.setA1_Status(rs.getString(3));
				if (rs.getDate(4) != null) {
					a1Form.setA1_Complete(rs.getDate(4).toString());
				}
				else {
					a1Form.setA1_Complete("");
				}
				a1Form.setA1_Manager(rs.getString(5));
				a1Form.setA1_NextStep(rs.getString(6));
				a1Form.setA1_ShouldSell(rs.getString(7));
				a1Form.setA1_CanSell(rs.getString(8));
				a1Form.setA1_Evaluation(rs.getString(9));
				a1Form.setA1_Pursuit(rs.getInt(10));
				a1FormList.add(a1Form);
				
	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("a1FormList size:"+a1FormList.size());
		return a1FormList;
	}
	public static ArrayList<A23Form> getA23List(String sql, int referenceNo) {
		PreparedStatement ps = null;
		ArrayList<A23Form> a23FormList = new ArrayList<A23Form>();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, referenceNo);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				A23Form a23Form = new A23Form();

				
				a23FormList.add(a23Form);
				
	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("a23FormList size:"+a23FormList.size());
		return a23FormList;
	}
	public static ArrayList<String> getA1History(String sql,int referenceNo) throws Exception {

		PreparedStatement ps = null;
		ArrayList<String> a1HistoryList = new ArrayList<String>();
			try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, referenceNo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				a1HistoryList.add(rs.getString(1));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return a1HistoryList;
	}
	public static ArrayList<String> getA23History(String sql,int referenceNo) throws Exception {

		PreparedStatement ps = null;
		ArrayList<String> a23HistoryList = new ArrayList<String>();
			try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, referenceNo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				a23HistoryList.add(rs.getString(1));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return a23HistoryList;
	}
	public static int isA23Exist(String sql, int ref_No, String a23RefNo) throws Exception {

		System.out.println("In DealDAO: Entering isA23Exist()...");
		PreparedStatement ps = null;
			int count = 0;
				try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ref_No);
			ps.setString(2, a23RefNo);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    count = rs.getInt(1);
			}
			
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("In DealDAO: Exiting isA23Exist()...");
		return count;
	}
	
	public static int hasA23Exist(String sql, int ref_No) throws Exception {

		System.out.println("In DealDAO: Entering hasA23Exist()...");
		PreparedStatement ps = null;
	
		int count = 0;
		System.out.println("Reference No: " + ref_No);

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ref_No);
			
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    count = rs.getInt(1);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("In DealDAO: Exiting hasA23Exist()...");
		return count;
	}
}