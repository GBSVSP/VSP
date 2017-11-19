package com.vsp.util;

	import java.net.URI;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.util.Map;

	import com.ibm.json.java.JSON;
	import com.ibm.json.java.JSONArray;
	import com.ibm.json.java.JSONObject;

	public class DBConnect {
		
	    public static Connection getConnection() throws Exception { 
	    	 Map<String, String> env = System.getenv(); 
	    	   
	     	  if (env.containsKey("VCAP_SERVICES")) { 
	     	   // we are running on cloud foundry, let's grab the service details from vcap_services 
	     	   JSONObject vcap = (JSONObject) JSON.parse(env.get("VCAP_SERVICES")); 
	     	   JSONObject service = null; 
	     	    
	     	   // We don't know exactly what the service is called, but it will contain "elephantsql" 
	     	   for (Object key : vcap.keySet()) { 
	     	    String keyStr = (String) key; 
	     	    if (keyStr.toLowerCase().contains("dashdb")) { 
	     	     service = (JSONObject) ((JSONArray) vcap.get(keyStr)).get(0); 
	     	     break; 
	     	    } 
	     	   } 
	     	    
	     	   if (service != null) { 
	     	    JSONObject creds = (JSONObject) service.get("credentials"); 
	     	    URI uri = URI.create((String) creds.get("uri")); 
	     	    String url = "jdbc:db2://" + uri.getHost() + ":" + uri.getPort() + uri.getPath(); 
	     	    String username = uri.getUserInfo().split(":")[0]; 
	     	    String password = uri.getUserInfo().split(":")[1]; 
	     	    System.out.println("got connection...");
		     	  
	     	    return DriverManager.getConnection(url, username, password); 
	     	   
	     	   } 
	     	   
	     	  } 
	     	   
	     	  throw new Exception("DB2 service URL found. Make sure you have bound the correct services to your app."); 
	     	 }
	
		
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}