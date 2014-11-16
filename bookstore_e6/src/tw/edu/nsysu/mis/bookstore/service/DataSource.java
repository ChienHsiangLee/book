package tw.edu.nsysu.mis.bookstore.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
	  String error;
	  Connection con;

	  //-----------------------------------------------
	  //	Constructor
	  //-----------------------------------------------
	  public DataSource()
	  {
	  }//--> end Constructor


	  //-----------------------------------------------
	  //	���Ʈw�s�u
	  //-----------------------------------------------
	  public boolean connect() throws SQLException 	  {
		  boolean isConnected=true;
		  try 
		  {
			  Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			  con = DriverManager.getConnection("jdbc:mysql://localhost/homework?user=root&password=3051&characterEncoding=utf8&useUnicode=true");
		  }catch(SQLException se){
			  //Handle errors for JDBC
			  se.printStackTrace();
			  isConnected=false;
		  }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		      isConnected=false;
		  }finally{
		      //finally block used to close resources

		  }//end try


	   return isConnected;
	  }//--> end connect()



	  //-----------------------------------------------
	  //	�פ��Ʈw���s�u
	  //-----------------------------------------------
	  public void disconnect() throws SQLException 
	  {
	    try 
		{
	      if ( con != null ) 
		  {
	        con.close();
	      }
	    } 
		catch (SQLException sqle) 
		{
	      error = ("SQLException: Unable to close the database connection.");
	      throw new SQLException(error);
	    }
	  }//--> end disconnect()



	  //-----------------------------------------------
	  //	����SQL����O
	  //-----------------------------------------------
	  public ResultSet ExecuteSQL(String queryString) throws SQLException
	  {
	    ResultSet rs = null;
	    try 
		{
	      Statement stmt = con.createStatement();
	      rs = stmt.executeQuery(queryString); 
	    } 
		catch (SQLException sqle) 
		{
	      error = "SQLException: Could not execute the query.";
	      throw new SQLException(error);
	    }
	    return rs;
	  }//--> end ExecuteSQL



	  public void ExecuteUpdate(String queryString) throws SQLException
	  {
	    try 
		{
	      Statement stmt = con.createStatement();
	      stmt.executeUpdate(queryString); 
	    } 
		catch (SQLException sqle) 
		{
	      error = "SQLException: Could not execute the query.";
	      throw new SQLException(error);
	    } 
	  }//--> end ExecuteUpdate	
	

}
