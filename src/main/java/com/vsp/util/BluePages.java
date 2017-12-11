package com.vsp.util;

import java.io.IOException;
import java.util.ResourceBundle;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import org.json.*;

public class BluePages {
	public final static ResourceBundle bundle = ResourceBundle.getBundle("com.vsp.util.vspMessages");
	public  static String client_id = bundle.getString(Constants.CLIENT_ID);
	private static String firstName;
	private static String lastName;
	private static String serialNo;
	

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	public static void getUserDetail(String email) throws IOException, JSONException {
		
		System.out.println("client_id:"+client_id);
		System.out.println("email:"+email);
		 OkHttpClient client = new OkHttpClient();
	        Request request = new Request.Builder()
	          .url("https://w3.api.ibm.com/common/run/bluepages/userid/"+email+"/emailaddress&hrFirstName&hrLastName&uid")
	          .get()
	          .addHeader("x-ibm-client-id", "e9559df8-11f2-42aa-9d4d-ac334d7b98b9")
	          .addHeader("accept", "application/json")
	          .build();
	        Response response = client.newCall(request).execute();
	      //  String output = response.body().string();
	        String jsonData = response.body().string();
	       // System.out.println(jsonData);
	        jsonData = jsonData.replace("\n", "");
	        jsonData= jsonData.substring(jsonData.indexOf("attribute"), jsonData.indexOf("return")-3);
	        String properties [] = jsonData.split(":");
	        String data ;
	        for(int i = 0; i<properties.length;i++){
	        	 if(properties[i].contains("uid")) {
	        		 i++;
	        		 data = properties[i];
	        		serialNo = data.split(",")[0].replaceAll("[^a-zA-Z0-9]", "");
	        		 SessionUtils.setSerialNo(String.valueOf(serialNo));
	        	 }
	        	 if(properties[i].contains("hrFirstName")) {
	        		 i++;
	        		 data = properties[i];
	        		 firstName = data.split(",")[0].replaceAll("[^a-zA-Z0-9]", "");
	        		 System.out.println("firstName:"+firstName);
	        		 SessionUtils.setFirstName(String.valueOf(firstName));
	        	 }
	        	 if(properties[i].contains("hrLastName")) {
	        		 i++;
	        		 data = properties[i];
	        		 lastName = data.split(",")[0].replaceAll("[^a-zA-Z0-9]", "");
	        	 }
	}
	   	   }
	}
	
