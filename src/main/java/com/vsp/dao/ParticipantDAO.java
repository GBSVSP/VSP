package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vsp.model.ParticipantInfo;
import com.vsp.util.DBConnect;
import com.vsp.util.SessionUtils;

/**
 * <p>
 * This the DAO class for participant master.
 *
 * </p>
 * 
 * @author Amrita Sahu (amritasahu@in.ibm.com)
 * @version 1.0
 * @Date 12/Dec/2017
 */

public class ParticipantDAO {
	private static Connection con = DBConnect.getInstance().getConnInst();

	/**
	 * Method to populate participantList from Database
	 * 
	 * @param sql
	 * @return ArrayList<ParticipantInfo>
	 * @throws Exception
	 */
	public static ArrayList<ParticipantInfo> getAllParticipantList(String sql) throws Exception {
		//System.out.println("In ParticipantDAO : Entering getAllParticipantList...");
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<ParticipantInfo> partInfoList = new ArrayList<ParticipantInfo>();
		int count = 1;
		try {

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				ParticipantInfo partInfo = new ParticipantInfo();
				partInfo.setParticipant_Id(rs.getInt(1));
				partInfo.setSerialNo(count);
				partInfo.setUser_Name(rs.getString(2));
				partInfo.setEmail(rs.getString(3));
				
				if (rs.getString(4).equalsIgnoreCase("Y")) {
					partInfo.setStorm_Trained(true);
				} else {
					partInfo.setStorm_Trained(false);
				}
					
				partInfo.setImt_Id(rs.getString(5));
				partInfo.setA2_invited(rs.getInt(6));
				partInfo.setA2_attended(rs.getInt(7));
				partInfo.setA3_invited(rs.getInt(8));
				partInfo.setA3_attended(rs.getInt(9));
				partInfo.setSentiment(rs.getString(10));
				
				partInfoList.add(partInfo);

				count = count + 1;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}finally {
			ps.close();
			rs.close();
		}
		//System.out.println("In ParticipantDAO : Exiting getAllParticipantList...");
		return partInfoList;
	}


	/**
	 * Method to insert a new participant into Database
	 * 
	 * @param sql, partInfo
	 * @return int
	 * @throws Exception
	 */
	public static int insertParticipant(String sql, ParticipantInfo partInfo) throws Exception {

		//System.out.println("In ParticipantDAO: Entering insertParticipant()...");
		PreparedStatement ps = null;
		int insertFlag = 0;
		System.out.println("Participant Email : " + partInfo.getEmail());

		try {
			// con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, partInfo.getUser_Name());
			ps.setString(2, partInfo.getEmail());
			
			if (partInfo.isStorm_Trained() == true) {
				ps.setString(3, "Y");
			} else {
				ps.setString(3, "N");
			}
			
			ps.setString(4, partInfo.getImt_Id());
			ps.setInt(5, partInfo.getA2_invited());
			ps.setInt(6, partInfo.getA2_attended());
			ps.setInt(7, partInfo.getA3_invited());
			ps.setInt(8, partInfo.getA3_attended());
			ps.setString(9, partInfo.getSentiment());
			ps.setString(10, "Y");  //active
		
			ps.setString(11, SessionUtils.getUserName());  //created by
			ps.setString(12, "N");   //is_delete

			insertFlag = ps.executeUpdate();
			System.out.println("Participant insert status:" + insertFlag);

		} catch (SQLException ex) {
			ex.printStackTrace();

		}finally {
			ps.close();
		}

		//System.out.println("In Participant: Exiting insertParticipant()...");
		return insertFlag;
	}

	/**
	 * Method to update an existing participant into Database
	 * 
	 * @param sql, partInfoList
	 * @return int
	 * @throws Exception
	 */
	public static int updateParticipant(String sql, List<ParticipantInfo> updateList) throws Exception {

		//System.out.println("In ParticipantDAO: Entering updateParticipant()...");
		PreparedStatement ps = null;
		int[] updateCountFlag = new int[0];
		System.out.println("Participant update list size: " + updateList.size());
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			for (ParticipantInfo partInfo : updateList) {
				
				// Set clause
				ps.setString(1, partInfo.getImt_Id());
				ps.setString(2, partInfo.getSentiment());
		
				if (partInfo.isStorm_Trained() == true) {
					ps.setString(3, "Y");
				} else {
					ps.setString(3, "N");
				}

				ps.setString(4, SessionUtils.getUserName());  //modified by

				// where clause
				ps.setInt(5, partInfo.getParticipant_Id());  //participant id
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

		//System.out.println("In ParticipantDAO: Exiting updateParticipant()...");
		return updateCountFlag.length;
	}

	/**
	 * Method to check whether participant already exist into Database
	 * 
	 * @param sql,
	 *            email
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean checkParticipantExist(String sql, String email) throws Exception {

		//System.out.println("In ParticipantDAO: Entering checkParticipantExist()...");
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean existParticipantFlag = false;
		int count = 0;
		System.out.println("Participant email: " + email);

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, email);

			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

			if (count == 1) {
				existParticipantFlag = true;
			} else {
				existParticipantFlag = false;
			}
			System.out.println("existParticipantFlag: " + existParticipantFlag);
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			ps.close();
			rs.close();
		}

		//System.out.println("In ParticipantDAO: Exiting checkParticipantExist()...");
		return existParticipantFlag;
	}

	/**
	 * Method to delete an existing participant fromDatabase
	 * 
	 * @param sql, partInfoList
	 * @return int
	 * @throws Exception
	 */
	public static int deleteParticipant(String sql, List<ParticipantInfo> deleteList) throws Exception {

		//System.out.println("In ParticipantDAO: Entering deleteParticipant()...");
		PreparedStatement ps = null;
		int[] deleteCountFlag = new int[0];

		System.out.println("Participant deleteList size: " + deleteList.size());
		
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			
			for (ParticipantInfo partInfo : deleteList) {	
				// Set clause
				ps.setString(1, "Y");                            //IS_DELETE
				ps.setString(2, SessionUtils.getUserName());     //MODIFIED_BY
				// where clause
				ps.setInt(3, partInfo.getParticipant_Id());      //PARTICIPANT_ID
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

		//System.out.println("In ParticipantDAO: Exiting deleteParticipant()...");
		return deleteCountFlag.length;
	}

}