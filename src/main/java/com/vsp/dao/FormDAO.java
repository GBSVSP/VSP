package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.vsp.util.DBConnect;
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

	
public static HashMap<Integer,String> getOptionList(String sql) throws Exception {
		
		PreparedStatement ps = null;
		HashMap<Integer,String> ledByMap = new HashMap<Integer,String>();
	
		try {
			
			ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ledByMap.put(rs.getInt(1),rs.getString(2));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
	return ledByMap;
	}

}