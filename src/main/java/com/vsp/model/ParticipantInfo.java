package com.vsp.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * <p>
 * This the bean class for participant master information
 *
 * </p>
 * 
 * @author Amrita Sahu (amritasahu@in.ibm.com)
 * @version 1.0
 * @Date 12/Dec/2017
 */
@ManagedBean(name = "participantInfo")
@SessionScoped
public class ParticipantInfo implements Serializable {
	
	private static final long serialVersionUID = -1379463026651149693L;
	private int participant_Id;
	private int serialNo;
	private String user_Name;
	private String email;
	private boolean storm_Trained;
	private boolean krnegotation_Trained;
	private boolean active;
	private String imt_Id;
	private String sentiment;
	private int a2_invited;
	private int a2_attended;
	private int a3_invited;
	private int a3_attended;
	private String created_By;
	private String modified_By;
	private boolean is_Delete;
	private boolean checkboxClickedFlag;
	
	
	public int getParticipant_Id() {
		return participant_Id;
	}
	public void setParticipant_Id(int participant_Id) {
		this.participant_Id = participant_Id;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStorm_Trained() {
		return storm_Trained;
	}
	public void setStorm_Trained(boolean storm_Trained) {
		this.storm_Trained = storm_Trained;
	}
	public boolean isKrnegotation_Trained() {
		return krnegotation_Trained;
	}
	public void setKrnegotation_Trained(boolean krnegotation_Trained) {
		this.krnegotation_Trained = krnegotation_Trained;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getImt_Id() {
		return imt_Id;
	}
	public void setImt_Id(String imt_Id) {
		this.imt_Id = imt_Id;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public int getA2_invited() {
		return a2_invited;
	}
	public void setA2_invited(int a2_invited) {
		this.a2_invited = a2_invited;
	}
	public int getA2_attended() {
		return a2_attended;
	}
	public void setA2_attended(int a2_attended) {
		this.a2_attended = a2_attended;
	}
	public int getA3_invited() {
		return a3_invited;
	}
	public void setA3_invited(int a3_invited) {
		this.a3_invited = a3_invited;
	}
	public int getA3_attended() {
		return a3_attended;
	}
	public void setA3_attended(int a3_attended) {
		this.a3_attended = a3_attended;
	}
	public String getCreated_By() {
		return created_By;
	}
	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}
	public String getModified_By() {
		return modified_By;
	}
	public void setModified_By(String modified_By) {
		this.modified_By = modified_By;
	}
	public boolean isIs_Delete() {
		return is_Delete;
	}
	public void setIs_Delete(boolean is_Delete) {
		this.is_Delete = is_Delete;
	}
	public boolean isCheckboxClickedFlag() {
		return checkboxClickedFlag;
	}
	public void setCheckboxClickedFlag(boolean checkboxClickedFlag) {
		this.checkboxClickedFlag = checkboxClickedFlag;
	}
	
	
	

}
