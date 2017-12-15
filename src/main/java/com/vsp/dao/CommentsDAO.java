package com.vsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.vsp.model.Comments;
import com.vsp.util.DBConnect;
import com.vsp.util.SessionUtils;

/**
 * <p>
 * This the DAO class for comments
 *
 * </p>
 * 
 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
 * @version 1.0
 * @Date 13/Dec/2017
 */

public class CommentsDAO {

	static Connection con = DBConnect.getInstance().getConnInst();


	public static int insertComments(String sql,Comments comments) throws Exception {

		System.out.println("In CommentsDAO: Entering insertComments()...");
		PreparedStatement ps = null;
		int insertFlag = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, comments.getReferenceNo());
			ps.setString(2, comments.getUserComment());
			ps.setString(3, SessionUtils.getUserName());
			insertFlag = ps.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		System.out.println("In CommentsDAO: Exiting insertComments()...");
		return insertFlag;
	}


	public static List<Comments> getComments(String sql, int reference_No) throws ParseException {
		System.out.println("In CommentsDAO: Entering getComments()...");
		PreparedStatement ps = null;
		List<Comments> commentsList = new ArrayList<Comments>();

		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, reference_No);
			ResultSet rs = ps.executeQuery();
			Comments comments = null;
			int count = 1;
			while (rs.next()) {
				comments = new Comments();
				comments.setUserComment(rs.getString(1));
						if (rs.getDate(2) != null) {
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
							Date createdDate = format.parse(rs.getString(2));
							comments.setCreatedDate(createdDate.toString());
				}
				comments.setCreatedUser(rs.getString(3));
				comments.setCount(count++);
				commentsList.add(comments);
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		System.out.println("In CommentsDAO: Exiting getComments()...");
		return commentsList;
	}

}