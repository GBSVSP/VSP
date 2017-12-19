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
	private int vsp_Opp_Id;
	private int workshop_Ref_Number;
	private String workshop_BriefingCallDate;
	private String workshop_CheckpointCallDate;
	
	private String workshop_CompleteDate;
	private String workshop_DealCoach;
	private String vsp_Doc_Link;
	private String trello_Record_Link;
	private String workshop_Status;
	private String vsp_Qual_Status;
	private String vsp_Qual_Justification;
	private String qual_Status_History;
	private String workshop_ShouldSell;
	private String workshop_CanSell;
	private int workshop_Pursuit;
	private String deal_Size_Range;
	private String qualifier_Margin;
	private List<String> workshop_ModulesRun;
	private String workshop_IBMBelovedDeal;
	private double workshop_AnticipatedPowerBase1;
	private double workshop_AnticipatedPowerBase2;
	private double workshop_ActualPowerBase;
	private String workshop_AnticipatedCompetitor1;
	private String workshop_AnticipatedCompetitor2;
	private String workshop_ActualCompetitor;
	private String workshop_ClientAttended;
	private String vsp_surve_score;
	private String client_centric_score;
	private String deal_Team_Feedback;
	private String workshop_Report_Link;
	private String created_by;
	private String modified_by;
	private boolean is_delete;
	
	private String workshop_Length;
	private String workshop_DealCoachEval;
	private String workshop_EvalStatusHistory;
	private String workshop_DealCoachJustification;
	private String workshop_Evaluation;
	private String workshop_Consortium;
	private List<String> workshop_HistoricalInfo;
	
	
	public int getWorkshop_Ref_Id() {
		return workshop_Ref_Id;
	}
	public void setWorkshop_Ref_Id(int workshop_Ref_Id) {
		this.workshop_Ref_Id = workshop_Ref_Id;
	}
	public int getVsp_Opp_Id() {
		return vsp_Opp_Id;
	}
	public void setVsp_Opp_Id(int vsp_Opp_Id) {
		this.vsp_Opp_Id = vsp_Opp_Id;
	}
	public int getWorkshop_Ref_Number() {
		return workshop_Ref_Number;
	}
	public void setWorkshop_Ref_Number(int workshop_Ref_Number) {
		this.workshop_Ref_Number = workshop_Ref_Number;
	}
	public String getWorkshop_BriefingCallDate() {
		return workshop_BriefingCallDate;
	}
	public void setWorkshop_BriefingCallDate(String workshop_BriefingCallDate) {
		this.workshop_BriefingCallDate = workshop_BriefingCallDate;
	}
	public String getWorkshop_CheckpointCallDate() {
		return workshop_CheckpointCallDate;
	}
	public void setWorkshop_CheckpointCallDate(String workshop_CheckpointCallDate) {
		this.workshop_CheckpointCallDate = workshop_CheckpointCallDate;
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
	public String getVsp_Doc_Link() {
		return vsp_Doc_Link;
	}
	public void setVsp_Doc_Link(String vsp_Doc_Link) {
		this.vsp_Doc_Link = vsp_Doc_Link;
	}
	public String getTrello_Record_Link() {
		return trello_Record_Link;
	}
	public void setTrello_Record_Link(String trello_Record_Link) {
		this.trello_Record_Link = trello_Record_Link;
	}
	public String getWorkshop_Status() {
		return workshop_Status;
	}
	public void setWorkshop_Status(String workshop_Status) {
		this.workshop_Status = workshop_Status;
	}
	public String getVsp_Qual_Status() {
		return vsp_Qual_Status;
	}
	public void setVsp_Qual_Status(String vsp_Qual_Status) {
		this.vsp_Qual_Status = vsp_Qual_Status;
	}
	public String getVsp_Qual_Justification() {
		return vsp_Qual_Justification;
	}
	public void setVsp_Qual_Justification(String vsp_Qual_Justification) {
		this.vsp_Qual_Justification = vsp_Qual_Justification;
	}
	public String getQual_Status_History() {
		return qual_Status_History;
	}
	public void setQual_Status_History(String qual_Status_History) {
		this.qual_Status_History = qual_Status_History;
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
	public String getDeal_Size_Range() {
		return deal_Size_Range;
	}
	public void setDeal_Size_Range(String deal_Size_Range) {
		this.deal_Size_Range = deal_Size_Range;
	}
	public String getQualifier_Margin() {
		return qualifier_Margin;
	}
	public void setQualifier_Margin(String qualifier_Margin) {
		this.qualifier_Margin = qualifier_Margin;
	}
	public List<String> getWorkshop_ModulesRun() {
		return workshop_ModulesRun;
	}
	public void setWorkshop_ModulesRun(List<String> workshop_ModulesRun) {
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
	public String getVsp_surve_score() {
		return vsp_surve_score;
	}
	public void setVsp_surve_score(String vsp_surve_score) {
		this.vsp_surve_score = vsp_surve_score;
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
	public String getWorkshop_Report_Link() {
		return workshop_Report_Link;
	}
	public void setWorkshop_Report_Link(String workshop_Report_Link) {
		this.workshop_Report_Link = workshop_Report_Link;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public boolean isIs_delete() {
		return is_delete;
	}
	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
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
	public String getWorkshop_EvalStatusHistory() {
		return workshop_EvalStatusHistory;
	}
	public void setWorkshop_EvalStatusHistory(String workshop_EvalStatusHistory) {
		this.workshop_EvalStatusHistory = workshop_EvalStatusHistory;
	}
	public String getWorkshop_Evaluation() {
		return workshop_Evaluation;
	}
	public void setWorkshop_Evaluation(String workshop_Evaluation) {
		this.workshop_Evaluation = workshop_Evaluation;
	}
	public String getWorkshop_Consortium() {
		return workshop_Consortium;
	}
	public void setWorkshop_Consortium(String workshop_Consortium) {
		this.workshop_Consortium = workshop_Consortium;
	}
	public List<String> getWorkshop_HistoricalInfo() {
		return workshop_HistoricalInfo;
	}
	public void setWorkshop_HistoricalInfo(List<String> workshop_HistoricalInfo) {
		this.workshop_HistoricalInfo = workshop_HistoricalInfo;
	}
	public String getWorkshop_DealCoachJustification() {
		return workshop_DealCoachJustification;
	}
	public void setWorkshop_DealCoachJustification(String workshop_DealCoachJustification) {
		this.workshop_DealCoachJustification = workshop_DealCoachJustification;
	}
	
	
	
	
	
	
	

	
	
	
	
	
	

}
