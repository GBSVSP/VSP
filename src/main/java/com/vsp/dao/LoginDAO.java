package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vsp.util.DBConnect;
import com.vsp.util.QueryBuilder;
import com.vsp.util.SessionUtils;

public class LoginDAO {

	public static int validate(String user, String password) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = QueryBuilder.VALIDATE_LOGIN;
		int roleId=0;
		try {
			con = DBConnect.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,user);	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				roleId = rs.getInt(1);
					
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		} finally {
			DBConnect.close(con);
		}
		return roleId;
	}
	
}