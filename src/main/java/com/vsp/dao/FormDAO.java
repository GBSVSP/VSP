package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vsp.model.DealInfo;
import com.vsp.util.DBConnect;
import com.vsp.util.QueryBuilder;
/**
 * <p>
 * This the DAO class for deal
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 20/Nov/2017
 */

public class FormDAO {
	
	static Connection con = DBConnect.getInstance().getConnInst();

	
public static ArrayList<String> getOptionList(String sql) throws Exception {
		
		PreparedStatement ps = null;
		ArrayList<String> ledByList = new ArrayList<String>();
	
		try {
			
			ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ledByList.add(rs.getString(2));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
	return ledByList;
	}

}