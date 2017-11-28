package com.vsp.util;
/**
 * <p>
 * SQL queries are generated from this class
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 13/Nov/2017
 */
public class QueryBuilder {
	
//deal form queries	
public static String VALIDATE_LOGIN = "Select ROLE_ID from USER_DATA where USER_NAME=?";
public static String SEARCH_DEAL = "Select REFERENCE_NO,SECTOR_ID,SECTOR_NAME,INDUSTRY_ID,INDUSTRY_NAME,IMT_ID,"
		+ "IMT_NAME,SC_NO,CUSTOMER_NAME,OPPORTUNITY_NAME,OPPORTUNITY_OWNER,OPPORTUNITY_DESCRIPTION,ADDITIONAL_CONTACTS,TCV,"
		+ "POTENTIAL_TCV,BOX_LINK,IPPF_NUMBER,GBS_GTS_LED,OTHER_LINKED_OPP_NO,SSM_STATE_ID,SSM_STAGE,SALES_CONNECT_NO,SC_OPP_OWNER,"
		+ "DECISION_DATE,BNP_SPENT,SC_IMT_ID,CREATED_BY,MODIFIED_BY,IS_DELETE,SC_REFRESH from DEAL_INFO_VIEW where IS_DELETE='N' and ";

public static String SELECT_VSP_REF_NO = "Select distinct VSP_REF_NO from DEAL_INFO_VIEW";
public static String SELECT_DEAL_ID = "Select distinct DEAL_ID from DEAL_INFO_VIEW";
public static String SELECT_CUSTOMER_NAME = "Select distinct CUSTOMER_NAME from DEAL_INFO_VIEW";
public static String SELECT_SALES_CONNECT_OPP_NO = "Select distinct SALES_CONNECT_OPP_NO from DEAL_INFO_VIEW";
public static String SELECT_OPTION = "Select VALUE_ID,TAGGED_TO from VSP_OPTION_MASTER where ";
public static String SELECT_IMT = "Select IMT_ID, IMT_NAME from IOT_IMT_VIEW where IOT_NAME='Europe'";
public static String SELECT_SECTOR = "Select SECTOR_ID, SECTOR_NAME from SECTOR";
public static String SELECT_MAX_VSP_REF_NO = "Select MAX(REFERENCE_NO) from DEAL_INFO";

//inserting new deal
public static String INSERT_DEAL = "Insert into DEAL_INFO (REFERENCE_NO, SECTOR_ID, INDUSTRY_ID, IMT_ID, SC_NO,"
		+ "CUSTOMER_NAME, OPPORTUNITY_NAME, OPPORTUNITY_OWNER, OPPORTUNITY_DESCRIPTION,"
		+ "ADDITIONAL_CONTACTS, TCV, POTENTIAL_TCV, BOX_LINK, IPPF_NUMBER, GBS_GTS_LED, "
		+ "OTHER_LINKED_OPP_NO, SSM_STATE_ID, SSM_STAGE, SALES_CONNECT_NO, SC_OPP_OWNER, "
		+ "DECISION_DATE, BNP_SPENT, SC_IMT_ID, CREATED_BY) "
		+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
//updating deal
public static String UPDATE_DEAL = "Update DEAL_INFO set SECTOR_ID =?, INDUSTRY_ID =?, IMT_ID =?, SC_NO =?,"
		+ "CUSTOMER_NAME =?, OPPORTUNITY_NAME =?, OPPORTUNITY_OWNER =?, OPPORTUNITY_DESCRIPTION =?, "
		+ "ADDITIONAL_CONTACTS =?, TCV =?, POTENTIAL_TCV =?, BOX_LINK =?, IPPF_NUMBER =?, GBS_GTS_LED =?, "
		+ "OTHER_LINKED_OPP_NO =?, SSM_STATE_ID =?, SSM_STAGE =?, SALES_CONNECT_NO =?, SC_OPP_OWNER =?,"
		+ "DECISION_DATE =?, BNP_SPENT =?, SC_IMT_ID =?, MODIFIED_BY =? where ";

//check existing deal
public static String COUNT_DEAL ="Select count(REFERENCE_NO) from DEAL_INFO where ";

//check existing A1 workshop
public static String COUNT_A1 ="Select count(A1_REF_NO) from A1_WORKSHOP_INFO where ";

//inserting A1 workshop
public static String INSERT_A1_WORKSHOP = "insert into A1_WORKSHOP_INFO (REFERENCE_NO,A1_REF_NO,A1_STATUS_ID,A1_COMPLETE_DATE,A1_MANAGER,NEXT_STEP,SHOULD_WE_SELL_SCORE,"
		+ "CAN_WE_SELL_SCORE,EVALUATION_STATUS,PURSUIT_PROFILER_WIN_CHANCE,CREATED_BY) values (?,?,?,?,?,?,?,?,?,?,?) ";
//update A1 workshop
public static String UPDATE_A1_WORKSHOP = "update A1_WORKSHOP_INFO set A1_STATUS_ID=?,A1_COMPLETE_DATE=?,A1_MANAGER=?,NEXT_STEP=?,"
		+ "SHOULD_WE_SELL_SCORE=?,CAN_WE_SELL_SCORE=?,EVALUATION_STATUS=?,PURSUIT_PROFILER_WIN_CHANCE=?,MODIFIED_BY=? where ";

}
