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
public static String VALIDATE_LOGIN = "Select ROLE_ID from USER_DATA where USER_NAME=?";
public static String SEARCH_DEAL = "Select * from DEAL_INFO_view where ";
public static String SELECT_VSP_REF_NO = "Select distinct VSP_REF_NO from DEAL_INFO";
public static String SELECT_DEAL_ID = "Select distinct DEAL_ID from DEAL_INFO";
public static String SELECT_CUSTOMER_NAME = "Select distinct CUSTOMER_NAME from DEAL_INFO";
public static String SELECT_SALES_CONNECT_OPP_NO = "Select distinct SALES_CONNECT_OPP_NO from DEAL_INFO";
public static String SELECT_LED_BY = "Select VALUE_ID, VALUE_NAME from VSP_OPTION_MASTER";
public static String SELECT_IMT = "Select IMT_ID, IMT_NAME from IMT";
public static String SELECT_SECTOR = "Select SECTOR_ID, SECTOR_NAME from SECTOR";
}
