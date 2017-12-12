package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vsp.model.UserInfo;
import com.vsp.util.DBConnect;
import com.vsp.util.SessionUtils;

/**
 * <p>
 * This the DAO class for user management.
 *
 * </p>
 * 
 * @author Amrita Sahu (amritasahu@in.ibm.com)
 * @version 1.0
 * @Date 29/Nov/2017
 */

public class UserMgmtDAO {
	private static Connection con = DBConnect.getInstance().getConnInst();

	/**
	 * Method to populate userList from Database
	 * 
	 * @param sql
	 * @return ArrayList<UserInfo>
	 * @throws Exception
	 */
	public static ArrayList<UserInfo> getAllUserList(String sql) throws Exception {
		//System.out.println("In UserMgmtDAO : Entering getAllUserList...");
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo>();
		int count = 1;
		try {

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUser_Id(rs.getInt(1));
				userInfo.setSerialNo(count);
				userInfo.setUser_Name(rs.getString(2));
				userInfo.setEmail(rs.getString(3));
				userInfo.setRole_Id(rs.getString(4));

				if (rs.getString(5).equalsIgnoreCase("Y")) {
					userInfo.setActive(true);
				} else {
					userInfo.setActive(false);
				}

				userInfo.setImt_Id(rs.getString(6));
				userInfoList.add(userInfo);

				count = count + 1;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}finally {
			ps.close();
			rs.close();
		}
		//System.out.println("In UserMgmtDAO : Exiting getAllUserList...");
		return userInfoList;
	}


	/**
	 * Method to insert a new user into Database
	 * 
	 * @param sql,
	 *            userInfo
	 * @return int
	 * @throws Exception
	 */
	public static int insertUser(String sql, UserInfo userInfo) throws Exception {

		//System.out.println("In UserMgmtDAO: Entering insertUser()...");
		PreparedStatement ps = null;
		int insertFlag = 0;
		System.out.println("User Email : " + userInfo.getEmail());

		try {
			// con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, userInfo.getUser_Name());
			ps.setString(2, userInfo.getEmail());
			ps.setString(3, userInfo.getRole_Id());
			if (userInfo.isActive() == true) {
				ps.setString(4, "Y");
			} else {
				ps.setString(4, "N");
			}
			ps.setString(5, SessionUtils.getUserName());
			ps.setString(6, userInfo.getImt_Id());
			ps.setString(7, "N");

			insertFlag = ps.executeUpdate();
			System.out.println("User insert status:" + insertFlag);

		} catch (SQLException ex) {
			ex.printStackTrace();

		}finally {
			ps.close();
		}

		//System.out.println("In UserMgmtDAO: Exiting insertDeal()...");
		return insertFlag;
	}

	/**
	 * Method to update an existing user into Database
	 * 
	 * @param sql,
	 *            userInfoList
	 * @return int
	 * @throws Exception
	 */
	public static int updateUser(String sql, List<UserInfo> updateList) throws Exception {

		//System.out.println("In UserMgmtDAO: Entering updateUser()...");
		PreparedStatement ps = null;
		int[] updateCountFlag = new int[0];
		System.out.println("User update list size: " + updateList.size());
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			for (UserInfo userInfo : updateList) {
				// Set clause
				//ps.setString(1, userInfo.getUser_Name());
				//ps.setString(2, userInfo.getEmail());
				ps.setString(1, userInfo.getRole_Id());

				if (userInfo.isActive() == true) {
					ps.setString(2, "Y");
				} else {
					ps.setString(2, "N");
				}

				ps.setString(3, SessionUtils.getUserName());
				ps.setString(4, userInfo.getImt_Id());

				// where clause
				ps.setInt(5, userInfo.getUser_Id());
				ps.addBatch();

			}

			updateCountFlag = ps.executeBatch();
			con.commit();
			System.out.println("updateCountFlag: " + updateCountFlag.length);

		} catch (SQLException ex) {
			ex.printStackTrace();

		}finally {
			ps.close();
		}

		//System.out.println("In UserMgmtDAO: Exiting updateUser()...");
		return updateCountFlag.length;
	}

	/**
	 * Method to check whether user already exist into Database
	 * 
	 * @param sql,
	 *            email
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean checkUserExist(String sql, String email) throws Exception {

		//System.out.println("In UserMgmtDAO: Entering checkUserExist()...");
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean existUserFlag = false;
		int count = 0;
		System.out.println("User email: " + email);

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, email);

			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 1) {
				existUserFlag = true;
			} else {
				existUserFlag = false;
			}
			System.out.println("existUserFlag: " + existUserFlag);
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			ps.close();
			rs.close();
		}

		//System.out.println("In UserMgmtDAO: Exiting checkUserExist()...");
		return existUserFlag;
	}

	/**
	 * Method to delete an existing user fromDatabase
	 * 
	 * @param sql,userInfoList
	 * @return int
	 * @throws Exception
	 */
	public static int deleteUser(String sql, List<UserInfo> deleteList) throws Exception {

		//System.out.println("In UserMgmtDAO: Entering deleteUser()...");
		PreparedStatement ps = null;
		int[] deleteCountFlag = new int[0];

		System.out.println("User deleteList size: " + deleteList.size());
		
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			
			for (UserInfo userInfo : deleteList) {	
				// Set clause
				ps.setString(1, "Y");                            //IS_DELETE
				ps.setString(2, SessionUtils.getUserName());
				// where clause
				ps.setInt(3, userInfo.getUser_Id());
				ps.addBatch();

			}

			deleteCountFlag = ps.executeBatch();
			con.commit();
			System.out.println("deleteCountFlag: " + deleteCountFlag.length);

		} catch (SQLException ex) {
			ex.printStackTrace();

		}finally {
			ps.close();
		}

		//System.out.println("In UserMgmtDAO: Exiting deleteUser()...");
		return deleteCountFlag.length;
	}

}