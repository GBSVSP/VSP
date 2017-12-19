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
public static String VALIDATE_LOGIN = "Select ROLE_ID from USER_DATA where USER_NAME=? and IS_ACTIVE ='Y'";
public static String SEARCH_DEAL = "Select REFERENCE_NO,SECTOR_ID,SECTOR_NAME,INDUSTRY_ID,INDUSTRY_NAME,IMT_ID,"
		+ "IMT_NAME,SC_NO,CUSTOMER_NAME,OPPORTUNITY_NAME,OPPORTUNITY_OWNER,OPPORTUNITY_DESCRIPTION,ADDITIONAL_CONTACTS,TCV,"
		+ "POTENTIAL_TCV,BOX_LINK,IPPF_NUMBER,GBS_GTS_LED,OTHER_LINKED_OPP_NO,SSM_STATE_ID,SSM_STAGE,SALES_CONNECT_NO,SC_OPP_OWNER,"
		+ "DECISION_DATE,BNP_SPENT,SC_IMT_ID,CREATED_BY,MODIFIED_BY,IS_DELETE,SC_REFRESH from DEAL_INFO_VIEW where IS_DELETE='N' and ";

public static String GET_DEAL = "Select REFERENCE_NO,SECTOR_ID,SECTOR_NAME,INDUSTRY_ID,INDUSTRY_NAME,IMT_ID,"
		+ "IMT_NAME,SC_NO,CUSTOMER_NAME,OPPORTUNITY_NAME,OPPORTUNITY_OWNER,OPPORTUNITY_DESCRIPTION,ADDITIONAL_CONTACTS,TCV,"
		+ "POTENTIAL_TCV,BOX_LINK,IPPF_NUMBER,GBS_GTS_LED,OTHER_LINKED_OPP_NO,SSM_STATE_ID,SSM_STAGE,SALES_CONNECT_NO,SC_OPP_OWNER,"
		+ "DECISION_DATE,BNP_SPENT,SC_IMT_ID,CREATED_BY,MODIFIED_BY,IS_DELETE,SC_REFRESH from DEAL_INFO_VIEW where IS_DELETE='N' and REFERENCE_NO=?";

public static String SEARCH_DEAL_ORDER_BY = "order by REFERENCE_NO desc";

public static String SELECT_VSP_REF_NO = "Select distinct REFERENCE_NO from DEAL_INFO_VIEW";
public static String SELECT_CUSTOMER_NAME = "Select distinct CUSTOMER_NAME from DEAL_INFO_VIEW";
public static String SELECT_SALES_CONNECT_NO = "Select distinct SALES_CONNECT_NO from DEAL_INFO_VIEW";
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

//get A1 Forms
public static String GET_A1_FORMS = "select REFERENCE_NO,A1_REF_NO,A1_STATUS_ID,A1_COMPLETE_DATE,A1_MANAGER,NEXT_STEP, "
		+ "SHOULD_WE_SELL_SCORE,CAN_WE_SELL_SCORE, EVALUATION_STATUS, PURSUIT_PROFILER_WIN_CHANCE,CREATION_DATE, "
		+ "CREATED_BY,MODIFICATION_DATE, MODIFIED_BY from a1_workshop_info where REFERENCE_NO=? order by A1_REF_NO desc";
//get A1 history
public static String A1_HISTORY = "select A1_REF_NO from A1_WORKSHOP_INFO where REFERENCE_NO=? and  ";

/** User Mgmt Form  queries **/

//User Role 
public static String SELECT_USER_ROLE ="Select ROLE_ID, ROLE_NAME from USER_ROLE where"; 

//Users Info
public static String SELECT_USER_INFO ="Select USER_ID, USER_NAME, EMAIL, ROLE_ID, ACTIVE, IMT_ID from USERS where";

//User Info insert
public static String INSERT_USER_INFO ="Insert into USERS (USER_NAME, EMAIL, ROLE_ID, ACTIVE, CREATED_BY, IMT_ID, IS_DELETE) "
		+ "values (?, ?, ?, ?, ?, ?, ?)";

//User Info update
public static String UPDATE_USER_INFO = "Update USERS set ROLE_ID =?, ACTIVE =?, MODIFIED_BY =?, IMT_ID=? where";

//check existing user
public static String COUNT_USER ="Select count(USER_ID) from USERS where";

//User Info delete
public static String DELETE_USER_INFO = "Update USERS set IS_DELETE= ? , MODIFIED_BY =? where ";

/** Participant Master Form Queries **/

//Participant Role 
public static String SELECT_PARTICIPANT_SENTIMENT ="Select VALUE_ID, TAGGED_TO from VSP_OPTION_MASTER where "; 

//Participant Info
public static String SELECT_PARTICIPANT_INFO ="Select PARTICIPANT_ID, USER_NAME, EMAIL, STORM_TRAINED, IMT_ID, A2_INVITED, "
		+ "A2_ATTENDED, A3_INVITED, A3_ATTENDED, SENTIMENT_ID from PARTICIPANTS where";

//Participant Info insert
public static String INSERT_PARTICIPANT_INFO ="Insert into PARTICIPANTS (USER_NAME, EMAIL, STORM_TRAINED, IMT_ID, "
		+ "A2_INVITED, A2_ATTENDED, A3_INVITED, A3_ATTENDED, SENTIMENT_ID, ACTIVE, CREATED_BY, IS_DELETE) "
		+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

//Participant Info update
public static String UPDATE_PARTICIPANT_INFO = "Update PARTICIPANTS set IMT_ID =?, SENTIMENT_ID =?, STORM_TRAINED =?, "
		+ "MODIFIED_BY =? where";

//check existing participant
public static String COUNT_PARTICIPANT ="Select count(PARTICIPANT_ID) from PARTICIPANTS where";

//Participant Info delete
public static String DELETE_PARTICIPANT_INFO = "Update PARTICIPANTS set IS_DELETE= ? , MODIFIED_BY =? where ";

//Insert Comments
public static String INSERT_COMMENT ="Insert into DEAL_COMMENTS ( VSP_OPP_ID,COMMENT,CREATED_BY ) "
		+ "values (?, ?, ?)";
//select comments
public static String SELECT_COMMENTS ="Select COMMENT,CREATION_DATE,CREATED_BY from DEAL_COMMENTS where IS_DELETE='N' and VSP_OPP_ID=? order by COMMENT_ID desc";
//insert workshop form
public static String INSERT_WORKSHOP ="";
}
