package poc;


import java.sql.*;
import java.io.*;
import java.util.*;


public class CreateAndDeleteExample {

    public static void main(String[] args) throws SQLException {

		Connection myConnection = null;
		Statement firstStatement = null, secondStatement = null, thirdStatement = null,deleteStatement=null,dataStatement=null;
		ResultSet resultData= null,resultData2=null,resultData3 = null;
		
		try {
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "student" , "student@123");
			System.out.println("Database connection successful!\n");
			firstStatement = myConnection.createStatement();
			secondStatement= myConnection.createStatement();
			deleteStatement=myConnection.createStatement();
			File myObj = new File("C:\\Users\\a.balasubramaniam\\Downloads\\input.txt");
		    File deleteFile = new File("C:\\Users\\a.balasubramaniam\\Documents\\deletestudent.txt");	
		    Scanner scanner = new Scanner(deleteFile);
		      
		      while (scanner.hasNext())  {
		    	  String data = scanner.next();
		    	  deleteStatement.executeUpdate("delete from student where studentId="+Integer.parseInt(data)+";");
		      }
		      
		      Writer writer = new FileWriter("C:\\Users\\a.balasubramaniam\\Documents\\updatedFile.txt");
		      BufferedWriter bufferedWriter = new BufferedWriter(writer);
		      dataStatement=myConnection.createStatement();
		      System.out.println("Enter the department");
		      Scanner departmentId = new Scanner(System.in);
		      String id = departmentId.next();
		      
		      resultData=secondStatement.executeQuery("select id from department where department ='"+id+"';");
	          while (resultData.next()) {	      
	            	resultData3 = dataStatement.executeQuery("select * from student where departmentId="+Integer.parseInt(resultData.getString("id"))+";");		       	 
	          }
		      
		      while (resultData3.next()) {
		    	  String resultantData=resultData3.getString("studentId") + ", " + resultData3.getString("fullName")+ ", " + resultData3.getString("lastName")+ ", " + resultData3.getString("departmentId")+ ", " + resultData3.getString("joiningDate")+ ", " + resultData3.getString("studentDob")+ ", " + resultData3.getString("mobileNo")+ ", " + resultData3.getString("email");
		    	  System.out.println(resultantData);
		    	  bufferedWriter.write(resultantData);
			  }  
		      
		      resultData2=secondStatement.executeQuery("SELECT departmentId ,fullName FROM student Group BY departmentId;");
		      while (resultData2.next()) {
		    	  String resultantData2=resultData2.getString("departmentId") + ", " + resultData2.getString("fullName");
		    	  System.out.println(resultantData2);
			  }
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (resultData != null) resultData.close();
			if (firstStatement != null) firstStatement.close();
	        if (myConnection != null) myConnection.close();
		}
	}

}
