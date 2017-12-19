package com.vsp.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.vsp.dao.CommentsDAO;
import com.vsp.dao.DealDAO;
import com.vsp.dao.FormDAO;
import com.vsp.util.Constants;
import com.vsp.util.QueryBuilder;

/**
 * <p>
 * This the bean class for loading all the drop downs
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 19/Dec/2017
 */
@ManagedBean
@ViewScoped
public class SelectList {
	private static String whereClause = null;
	private static HashMap<Integer, String> optionMap;
	
	// Populating Led By drop down
		public ArrayList<SelectItem> getLedByList() {
			whereClause = Constants.LED_BY_CONDITION;
			String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getValue(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}
		// Populating Potential TCV drop down
		public ArrayList<SelectItem> getPotentialTCVList() {
			whereClause = Constants.PPOTENTIAL_TCV_CONDITION;
			String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}

		// Populating A1 Status drop down
		public ArrayList<SelectItem> getA1StatusList() {
			whereClause = Constants.A1STATUS_CONDITION;
			String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getValue(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}

		// Populating Next Step drop down
		public ArrayList<SelectItem> getNextStepList() {
			whereClause = Constants.NEXT_STEP_CONDITION;
			String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}

		// Populating Should we sell drop down
		public ArrayList<SelectItem> getShouldweSellList() {
			whereClause = Constants.SHOULD_WE_SELL_CONDITION;
			String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}

		// Populating can we sell drop down
		public ArrayList<SelectItem> getCanweSellList() {
			whereClause = Constants.CAN_WE_SELL_CONDITION;
			String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;

			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}

		// Populating Evaluation drop down
		public ArrayList<SelectItem> getA1EvaluationList() {
			whereClause = Constants.A1EVALUATION_CONDITION;
			String sql = QueryBuilder.SELECT_OPTION + "" + whereClause;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}

		// Populating IMT drop down
		public List<SelectItem> getIMTList() {
			String sql = QueryBuilder.SELECT_IMT;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}

		// Populating SCIMT drop down
		public List<SelectItem> getSCIMTList() {
			String sql = QueryBuilder.SELECT_IMT;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}

		// Populating Sector drop down
		public ArrayList<SelectItem> getSectorList() {
			String sql = QueryBuilder.SELECT_SECTOR;
			ArrayList<SelectItem> optionList = new ArrayList<SelectItem>();
			try {
				optionMap = FormDAO.getOptionList(sql);
				for (Map.Entry<Integer, String> entry : optionMap.entrySet()) {
					optionList.add(new SelectItem(entry.getKey(), entry.getValue()));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return optionList;
		}
}
