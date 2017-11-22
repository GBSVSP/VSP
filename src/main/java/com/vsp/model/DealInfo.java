package com.vsp.model;

import java.util.Calendar;

import com.vsp.dao.DealDAO;
import com.vsp.util.Constants;
import com.vsp.util.QueryBuilder;

/**
 * <p>
 * This the bean class for deal informations
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 13/Nov/2017
 */
public class DealInfo {

private int reference_No ;
private int sector_Id ;
private String sector_Name ;
private int industry_Id ;
private String industry_Name ;
private int imt_Id ;
private String imt_Name;
private String sc_No;
private String customer_Name ;
private String opportunity_Name;
private String opportunity_Owner;
private String opportunity_Description;
private String additional_Contacts ;
private double tcv ;
private String potential_TCV ;
private String box_Link ;
private String ippf_Number ;
private String gbs_gts_Led ;
private String other_Linked_Opp_No ;
private int ssm_Stage_Id ;
private int ssm_Stage ;
private String sales_Connect_No ;
private String sc_Opp_Owner ;
private String decision_Date ; 
private double bnp_Spent ;
private int sc_Imt_Id ;

public void setReference_No(int reference_No) {
	this.reference_No = reference_No;
}

public int getSector_Id() {
	return sector_Id;
}

public void setSector_Id(int sector_Id) {
	this.sector_Id = sector_Id;
}

public String getSector_Name() {
	return sector_Name;
}

public void setSector_Name(String sector_Name) {
	this.sector_Name = sector_Name;
}

public int getIndustry_Id() {
	return industry_Id;
}

public void setIndustry_Id(int industry_Id) {
	this.industry_Id = industry_Id;
}

public String getIndustry_Name() {
	return industry_Name;
}

public void setIndustry_Name(String industry_Name) {
	this.industry_Name = industry_Name;
}

public int getImt_Id() {
	return imt_Id;
}

public void setImt_Id(int imt_Id) {
	this.imt_Id = imt_Id;
}

public String getImt_Name() {
	return imt_Name;
}

public void setImt_Name(String imt_Name) {
	this.imt_Name = imt_Name;
}

public String getSc_No() {
	return sc_No;
}

public void setSc_No(String sc_No) {
	this.sc_No = sc_No;
}

public String getCustomer_Name() {
	return customer_Name;
}

public void setCustomer_Name(String customer_Name) {
	this.customer_Name = customer_Name;
}

public String getOpportunity_Name() {
	return opportunity_Name;
}

public void setOpportunity_Name(String opportunity_Name) {
	this.opportunity_Name = opportunity_Name;
}

public String getOpportunity_Owner() {
	return opportunity_Owner;
}

public void setOpportunity_Owner(String opportunity_Owner) {
	this.opportunity_Owner = opportunity_Owner;
}

public String getOpportunity_Description() {
	return opportunity_Description;
}

public void setOpportunity_Description(String opportunity_Description) {
	this.opportunity_Description = opportunity_Description;
}

public String getAdditional_Contacts() {
	return additional_Contacts;
}

public void setAdditional_Contacts(String additional_Contacts) {
	this.additional_Contacts = additional_Contacts;
}

public double getTcv() {
	return tcv;
}

public void setTcv(double tcv) {
	this.tcv = tcv;
}

public String getPotential_TCV() {
	return potential_TCV;
}

public void setPotential_TCV(String potential_TCV) {
	this.potential_TCV = potential_TCV;
}

public String getBox_Link() {
	return box_Link;
}

public void setBox_Link(String box_Link) {
	this.box_Link = box_Link;
}

public String getIppf_Number() {
	return ippf_Number;
}

public void setIppf_Number(String ippf_Number) {
	this.ippf_Number = ippf_Number;
}

public String getGbs_gts_Led() {
	return gbs_gts_Led;
}

public void setGbs_gts_Led(String gbs_gts_Led) {
	this.gbs_gts_Led = gbs_gts_Led;
}

public String getOther_Linked_Opp_No() {
	return other_Linked_Opp_No;
}

public void setOther_Linked_Opp_No(String other_Linked_Opp_No) {
	this.other_Linked_Opp_No = other_Linked_Opp_No;
}

public int getSsm_Stage_Id() {
	return ssm_Stage_Id;
}

public void setSsm_Stage_Id(int ssm_Stage_Id) {
	this.ssm_Stage_Id = ssm_Stage_Id;
}

public int getSsm_Stage() {
	return ssm_Stage;
}

public void setSsm_Stage(int ssm_Stage) {
	this.ssm_Stage = ssm_Stage;
}
public String getSales_Connect_No() {
	return sales_Connect_No;
}

public void setSales_Connect_No(String sales_Connect_No) {
	this.sales_Connect_No = sales_Connect_No;
}

public String getSc_Opp_Owner() {
	return sc_Opp_Owner;
}

public void setSc_Opp_Owner(String sc_Opp_Owner) {
	this.sc_Opp_Owner = sc_Opp_Owner;
}

public String getDecision_Date() {
	return decision_Date;
}

public void setDecision_Date(String decision_Date) {
	this.decision_Date = decision_Date;
}

public double getBnp_Spent() {
	return bnp_Spent;
}

public void setBnp_Spent(double bnp_Spent) {
	this.bnp_Spent = bnp_Spent;
}

public int getSc_Imt_Id() {
	return sc_Imt_Id;
}

public void setSc_Imt_Id(int sc_Imt_Id) {
	this.sc_Imt_Id = sc_Imt_Id;
}

private static String  sql = null;

public int getReference_No() {
/*	Calendar now = Calendar.getInstance();  
	int year = now.get(Calendar.YEAR); 
	sql = QueryBuilder.SELECT_MAX_VSP_REF_NO;
	int currentRefNo = DealDAO.getReferenceNumber(sql);
	System.out.println("currentRefNo:"+currentRefNo+"year:"+year);
	if(Integer.parseInt(Integer.toString(currentRefNo).substring(0, 4))==year) {
		currentRefNo ++;
		reference_No = currentRefNo;
	}
	else {
		reference_No = Integer.parseInt(String.valueOf(year)+Constants.VSP_REF_NO_START);
	}
	*/
	return reference_No;
}
}
