package com.vsp.util;

	import java.sql.Connection;
    import java.sql.DriverManager;
	/**
	 * <p>
	 * This the DB connection  class for VSP
	 *
	 * </p>
	 * 
	 * @author Anju Sasidharan (anju.sasidharan@in.ibm.com)
	 * @version 1.0
	 * @Date 10/Nov/2017
	 */
	public class DBConnect {
		
		private static DBConnect singleInstance;
		private static Connection connection;
	;
		private DBConnect() { 
	    	try {
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				
				System.out.println("connection got");
				
			} catch (Exception ex) {
				System.out.println(ex);
				System.out.println("Database.getConnection() Error -->"
						+ ex.getMessage());
				
			}
	  	 }
		 public static DBConnect getInstance()
		  {
		    if(singleInstance == null)
		    {
		      synchronized (DBConnect.class)
		      {
		        if(singleInstance == null)
		        {
		          singleInstance = new DBConnect();
		        }
		      }
		    }
		 
		    return singleInstance;
		  }
		 
		 public Connection getConnInst()
		  {
		 try {
		  connection = DriverManager.getConnection(
					"jdbc:db2://dashdb-entry-yp-dal09-09.services.dal.bluemix.net:50000/BLUDB", "dash9924", "05CL_zl_PKpz");
		      
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		    return connection;   
		  }
	   /* public static Connection getConnection() throws Exception { 
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
	     	 }*/
	
		
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}