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
public static String SEARCH_DEAL = "Select * from DEAL_INFO_VIEW where ";
public static String SELECT_VSP_REF_NO = "Select distinct VSP_REF_NO from DEAL_INFO_VIEW";
public static String SELECT_DEAL_ID = "Select distinct DEAL_ID from DEAL_INFO_VIEW";
public static String SELECT_CUSTOMER_NAME = "Select distinct CUSTOMER_NAME from DEAL_INFO_VIEW";
public static String SELECT_SALES_CONNECT_OPP_NO = "Select distinct SALES_CONNECT_OPP_NO from DEAL_INFO_VIEW";
public static String SELECT_OPTION = "Select VALUE_ID,TAGGED_TO from VSP_OPTION_MASTER where ";
public static String SELECT_IMT = "Select IMT_ID, IMT_NAME from IOT_IMT_VIEW where IOT_NAME='Europe'";
public static String SELECT_SECTOR = "Select SECTOR_ID, SECTOR_NAME from SECTOR";
public static String SELECT_MAX_VSP_REF_NO = "Select MAX(REFERENCE_NO) from DEAL_INFO_VIEW";

//inserting new deal
public static String INSERT_DEAL = "Insert into DEAL_INFO (REFERENCE_NO, SECTOR_ID, INDUSTRY_ID, IMT_ID, SC_NO,"
		+ "CUSTOMER_NAME, OPPORTUNITY_NAME, OPPORTUNITY_OWNER, OPPORTUNITY_DESCRIPTION,"
		+ "ADDITIONAL_CONTACTS, TCV, POTENTIAL_TCV, BOX_LINK, IPPF_NUMBER, GBS_GTS_LED, "
		+ "OTHER_LINKED_OPP_NO, SSM_STATE_ID, SSM_STAGE, SALES_CONNECT_NO, SC_OPP_OWNER, "
		+ "DECISION_DATE, BNP_SPENT, SC_IMT_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
//updating deal
public static String UPDATE_DEAL = "Update DEAL_INFO set SECTOR_ID =?, INDUSTRY_ID =?, IMT_ID =?, SC_NO =?,"
		+ "CUSTOMER_NAME =?, OPPORTUNITY_NAME =?, OPPORTUNITY_OWNER =?, OPPORTUNITY_DESCRIPTION =?, "
		+ "ADDITIONAL_CONTACTS =?, TCV =?, POTENTIAL_TCV =?, BOX_LINK =?, IPPF_NUMBER =?, GBS_GTS_LED =?, "
		+ "OTHER_LINKED_OPP_NO =?, SSM_STATE_ID =?, SSM_STAGE =?, SALES_CONNECT_NO =?, SC_OPP_OWNER =?,"
		+ "DECISION_DATE =?, BNP_SPENT =?, SC_IMT_ID =? where ";

}
