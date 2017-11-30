package com.vsp.util;

/**
 * <p>
 * This the class for constant variables
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 13/Nov/2017
 */
public class Constants {

public static final String A1_REF_NO_START = "A101";
public static final String A1_REF_NO_BASE = "A1";

//deal form constants	
public static final String VSP_OPP_NO = "VSP_OPP_NO";
public static final String CLIENT = "CLIENT";
public static final String DEAL = "DEAL";
public static final String SALES_CONNECT_NO = "SALES_CONNECT_NO";
public static final String DEFAULT_SEARCH_TYPE = "VSP_OPP_NO";
public static final String VSP_OPP_NO_SQL = "REFERENCE_NO=?";
public static final String CLIENT_SQL = "CUSTOMER_NAME=?";
public static final String DEAL_SQL = "REFERENCE_NO=?";
public static final String SALES_CONNECT_NO_SQL="SALES_CONNECT_OPP_NO=?";
public static final String LED_BY_CONDITION="VALUE_NAME='GBS_or_GTS_lead'";
public static final String PPOTENTIAL_TCV_CONDITION="VALUE_NAME='Potential_TCV($m)'";
public static final String A1STATUS_CONDITION="VALUE_NAME='A1_status'";
public static final String NEXT_STEP_CONDITION="VALUE_NAME='Next_step?'";
public static final String SHOULD_WE_SELL_CONDITION="VALUE_NAME='Should_we_sell?'";
public static final String CAN_WE_SELL_CONDITION="VALUE_NAME='Can_we_sell?'";
public static final String A1EVALUATION_CONDITION="VALUE_NAME='Evaluation'";
public static final String VSP_REF_NO_START="0001";
public static final String VSP_REF_NO = "REFERENCE_NO=?";
public static final String A1_REF_NO = "A1_REF_NO=?";
public static final String A1_STATUS = "A1_STATUS_ID in ('Complete','Cancelled')";

//message constants
public static final String LOGIN_INVALID = "login_invalid";
public static final String LOGIN_ACCESS_DENIED = "login_access_denied";
public static final String A1_STATUS_MSG = "Please Complete Current Active A1 Form";


}
