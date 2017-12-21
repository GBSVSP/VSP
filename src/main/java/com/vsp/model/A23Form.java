package com.vsp.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * <p>
 * This the bean class for A23/Workshop Form
 *
 * </p>
 * 
 * @author Amrita Sahu (amritasahu@in.ibm.com)
 * @version 1.0
 * @Date 19/Dec/2017
 */

@ManagedBean(name="a23Form")
@ViewScoped
public class A23Form implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int workshop_Ref_Id;
	private int reference_No;
	private String workshop_Ref_Number;
	private String workshop_CompleteDate;
	private String workshop_DealCoach;
	private String workshop_Status;
	private String workshop_Length;
	private String workshop_DealCoachEval;
	private String workshop_DealCoachJustification;
	private String workshop_ShouldSell;
	private String workshop_CanSell;
	private int workshop_Pursuit;
	private String evaluation;
	private String briefingCallDate;
	private String nextFollowUpCallDate;
	private String[] workshop_ModulesRun;
	private String workshop_IBMBelovedDeal;
	private double workshop_AnticipatedPowerBase1;
	private double workshop_AnticipatedPowerBase2;
	private double workshop_ActualPowerBase;
	private String workshop_AnticipatedCompetitor1;
	private String workshop_AnticipatedCompetitor2;
	private String workshop_ActualCompetitor;
	private String workshop_ClientAttended;
	private String workshop_Consortium;
	
	private String avg_survey_score;
	private String client_centric_score;
	private String deal_Team_Feedback;
	private String created_by;
	private String created_date;
	private String modified_by;
	private String modified_date;
	private boolean is_delete;
	
    private List<String> workshop_HistoricalInfo;
	
	private String workshop_EvalStatusHistory;

	public int getWorkshop_Ref_Id() {
		return workshop_Ref_Id;
	}

	public void setWorkshop_Ref_Id(int workshop_Ref_Id) {
		this.workshop_Ref_Id = workshop_Ref_Id;
	}

	public int getReference_No() {
		return reference_No;
	}

	public void setReference_No(int reference_No) {
		this.reference_No = reference_No;
	}

	public String getWorkshop_Ref_Number() {
		return workshop_Ref_Number;
	}

	public void setWorkshop_Ref_Number(String workshop_Ref_Number) {
		this.workshop_Ref_Number = workshop_Ref_Number;
	}

	public String getWorkshop_CompleteDate() {
		return workshop_CompleteDate;
	}

	public void setWorkshop_CompleteDate(String workshop_CompleteDate) {
		this.workshop_CompleteDate = workshop_CompleteDate;
	}

	public String getWorkshop_DealCoach() {
		return workshop_DealCoach;
	}

	public void setWorkshop_DealCoach(String workshop_DealCoach) {
		this.workshop_DealCoach = workshop_DealCoach;
	}

	public String getWorkshop_Status() {
		return workshop_Status;
	}

	public void setWorkshop_Status(String workshop_Status) {
		this.workshop_Status = workshop_Status;
	}

	public String getWorkshop_Length() {
		return workshop_Length;
	}

	public void setWorkshop_Length(String workshop_Length) {
		this.workshop_Length = workshop_Length;
	}

	public String getWorkshop_DealCoachEval() {
		return workshop_DealCoachEval;
	}

	public void setWorkshop_DealCoachEval(String workshop_DealCoachEval) {
		this.workshop_DealCoachEval = workshop_DealCoachEval;
	}

	public String getWorkshop_DealCoachJustification() {
		return workshop_DealCoachJustification;
	}

	public void setWorkshop_DealCoachJustification(String workshop_DealCoachJustification) {
		this.workshop_DealCoachJustification = workshop_DealCoachJustification;
	}

	public String getWorkshop_ShouldSell() {
		return workshop_ShouldSell;
	}

	public void setWorkshop_ShouldSell(String workshop_ShouldSell) {
		this.workshop_ShouldSell = workshop_ShouldSell;
	}

	public String getWorkshop_CanSell() {
		return workshop_CanSell;
	}

	public void setWorkshop_CanSell(String workshop_CanSell) {
		this.workshop_CanSell = workshop_CanSell;
	}

	public int getWorkshop_Pursuit() {
		return workshop_Pursuit;
	}

	public void setWorkshop_Pursuit(int workshop_Pursuit) {
		this.workshop_Pursuit = workshop_Pursuit;
	}

	public String[] getWorkshop_ModulesRun() {
		return workshop_ModulesRun;
	}

	public void setWorkshop_ModulesRun(String[] workshop_ModulesRun) {
		this.workshop_ModulesRun = workshop_ModulesRun;
	}

	public String getWorkshop_IBMBelovedDeal() {
		return workshop_IBMBelovedDeal;
	}

	public void setWorkshop_IBMBelovedDeal(String workshop_IBMBelovedDeal) {
		this.workshop_IBMBelovedDeal = workshop_IBMBelovedDeal;
	}

	public double getWorkshop_AnticipatedPowerBase1() {
		return workshop_AnticipatedPowerBase1;
	}

	public void setWorkshop_AnticipatedPowerBase1(double workshop_AnticipatedPowerBase1) {
		this.workshop_AnticipatedPowerBase1 = workshop_AnticipatedPowerBase1;
	}

	public double getWorkshop_AnticipatedPowerBase2() {
		return workshop_AnticipatedPowerBase2;
	}

	public void setWorkshop_AnticipatedPowerBase2(double workshop_AnticipatedPowerBase2) {
		this.workshop_AnticipatedPowerBase2 = workshop_AnticipatedPowerBase2;
	}

	public double getWorkshop_ActualPowerBase() {
		return workshop_ActualPowerBase;
	}

	public void setWorkshop_ActualPowerBase(double workshop_ActualPowerBase) {
		this.workshop_ActualPowerBase = workshop_ActualPowerBase;
	}

	public String getWorkshop_AnticipatedCompetitor1() {
		return workshop_AnticipatedCompetitor1;
	}

	public void setWorkshop_AnticipatedCompetitor1(String workshop_AnticipatedCompetitor1) {
		this.workshop_AnticipatedCompetitor1 = workshop_AnticipatedCompetitor1;
	}

	public String getWorkshop_AnticipatedCompetitor2() {
		return workshop_AnticipatedCompetitor2;
	}

	public void setWorkshop_AnticipatedCompetitor2(String workshop_AnticipatedCompetitor2) {
		this.workshop_AnticipatedCompetitor2 = workshop_AnticipatedCompetitor2;
	}

	public String getWorkshop_ActualCompetitor() {
		return workshop_ActualCompetitor;
	}

	public void setWorkshop_ActualCompetitor(String workshop_ActualCompetitor) {
		this.workshop_ActualCompetitor = workshop_ActualCompetitor;
	}

	public String getWorkshop_ClientAttended() {
		return workshop_ClientAttended;
	}

	public void setWorkshop_ClientAttended(String workshop_ClientAttended) {
		this.workshop_ClientAttended = workshop_ClientAttended;
	}

	public String getWorkshop_Consortium() {
		return workshop_Consortium;
	}

	public void setWorkshop_Consortium(String workshop_Consortium) {
		this.workshop_Consortium = workshop_Consortium;
	}

	public String getAvg_survey_score() {
		return avg_survey_score;
	}

	public void setAvg_survey_score(String avg_survey_score) {
		this.avg_survey_score = avg_survey_score;
	}

	public String getClient_centric_score() {
		return client_centric_score;
	}

	public void setClient_centric_score(String client_centric_score) {
		this.client_centric_score = client_centric_score;
	}

	public String getDeal_Team_Feedback() {
		return deal_Team_Feedback;
	}

	public void setDeal_Team_Feedback(String deal_Team_Feedback) {
		this.deal_Team_Feedback = deal_Team_Feedback;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public List<String> getWorkshop_HistoricalInfo() {
		return workshop_HistoricalInfo;
	}

	public void setWorkshop_HistoricalInfo(List<String> workshop_HistoricalInfo) {
		this.workshop_HistoricalInfo = workshop_HistoricalInfo;
	}

	public String getWorkshop_EvalStatusHistory() {
		return workshop_EvalStatusHistory;
	}

	public void setWorkshop_EvalStatusHistory(String workshop_EvalStatusHistory) {
		this.workshop_EvalStatusHistory = workshop_EvalStatusHistory;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getBriefingCallDate() {
		return briefingCallDate;
	}

	public void setBriefingCallDate(String briefingCallDate) {
		this.briefingCallDate = briefingCallDate;
	}

	public String getNextFollowUpCallDate() {
		return nextFollowUpCallDate;
	}

	public void setNextFollowUpCallDate(String nextFollowUpCallDate) {
		this.nextFollowUpCallDate = nextFollowUpCallDate;
	}
	

}
