package com.vsp.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

/**
 * <p>
 * This the bean class for deal
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 24/Nov/2017
 */
@ManagedBean(name="a1Form")
@SessionScoped
public class A1Form implements Serializable {
	private static final long serialVersionUID = 1094801825228386363L;
	private String a1_Status;
	private String a1_Manager;
	private String a1_Complete;
	private String a1_NextStep;
	private String a1_ShouldSell;
	private String a1_CanSell;
	private String a1_Evaluation;
	private int a1_Pursuit;
	private String a1_RefNo;
	
	
	public String getA1_RefNo() {
		return a1_RefNo;
	}
	public void setA1_RefNo(String a1_RefNo) {
		this.a1_RefNo = a1_RefNo;
	}
	public String getA1_Status() {
		return a1_Status;
	}
	public void setA1_Status(String a1_Status) {
		this.a1_Status = a1_Status;
	}
	public String getA1_Manager() {
		return a1_Manager;
	}
	public void setA1_Manager(String a1_Manager) {
		this.a1_Manager = a1_Manager;
	}
	public String getA1_Complete() {
		return a1_Complete;
	}
	public void setA1_Complete(String a1_Complete) {
		this.a1_Complete = a1_Complete;
	}
	public String getA1_NextStep() {
		return a1_NextStep;
	}
	public void setA1_NextStep(String a1_NextStep) {
		this.a1_NextStep = a1_NextStep;
	}
	public String getA1_ShouldSell() {
		return a1_ShouldSell;
	}
	public void setA1_ShouldSell(String a1_ShouldSell) {
		this.a1_ShouldSell = a1_ShouldSell;
	}
	public String getA1_CanSell() {
		return a1_CanSell;
	}
	public void setA1_CanSell(String a1_CanSell) {
		this.a1_CanSell = a1_CanSell;
	}
	public String getA1_Evaluation() {
		return a1_Evaluation;
	}
	public void setA1_Evaluation(String a1_Evaluation) {
		this.a1_Evaluation = a1_Evaluation;
	}
	public int getA1_Pursuit() {
		return a1_Pursuit;
	}
	public void setA1_Pursuit(int a1_Pursuit) {
		this.a1_Pursuit = a1_Pursuit;
	}
	
	public void createA1() {
		
		
	//	a1statusFlag = false;
	}

}
