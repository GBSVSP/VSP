package com.vsp.util;

public class QueryBuilder {
public static String VALIDATE_LOGIN = "Select ROLE_ID from USER_DATA where USER_NAME=?";
public static String SEARCH_DEAL = "Select * from DEAL_INFO where ";
public static String SELECT_VSP_REF_NO = "Select distinct VSP_REF_NO from DEAL_INFO";
public static String SELECT_DEAL_ID = "Select distinct DEAL_ID from DEAL_INFO";
public static String SELECT_CUSTOMER_NAME = "Select distinct CUSTOMER_NAME from DEAL_INFO";
public static String SELECT_SALES_CONNECT_OPP_NO = "Select distinct SALES_CONNECT_OPP_NO from DEAL_INFO";

}
