package poc;

import java.sql.*;
import java.io.*;
import java.util.*;

public class Test {

	public static void main(String[] args) throws SQLException {

		Connection myConnection = null;
		Statement firstStatement = null, secondStatement = null;
		ResultSet resultData= null;
		String[] headerData={"studentId","fullName","lastName","departmentId","joiningDate","studentDob","mobileNo","email"};
		try {
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "student" , "student@123");
			System.out.println("Database connection successful!\n");
			firstStatement = myConnection.createStatement();
			secondStatement = myConnection.createStatement();
			File myObj = new File("C:\\Users\\a.balasubramaniam\\Documents\\POC-DBData\\input.txt");
		    Scanner scan = new Scanner(myObj); 
		    int headerCount = 0, dataId = 0;
		  
		      while (scan.hasNext() && headerCount < 8 ) {
		        String data = scan.next();
		        if(headerCount==0) {
		        	dataId=Integer.parseInt(data);
		        	firstStatement.executeUpdate("insert into student (" +headerData[headerCount] + ") values (" + data+")");			
		        }else {
		        	if(headerCount==3) {
		        		resultData=secondStatement.executeQuery("select id from department where department ='"+data+"';");
			            while (resultData.next()) {
			            	firstStatement.executeUpdate("update student set " +headerData[headerCount] +"=" +  resultData.getInt("id")+" where studentId="+dataId+";");
		 			    }
		        	}else {
		        		firstStatement.executeUpdate("update student set " +headerData[headerCount] +"= '" + data+"' where studentId="+dataId+";"); 					
		        	}
		        }
		        if(headerCount < 8) headerCount++;
		        else headerCount=0;
		      }
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (resultData != null) resultData.close();
			if (firstStatement != null) firstStatement.close();
			if (secondStatement != null) secondStatement.close();
	        if (myConnection != null) myConnection.close();
		}
	}

}

