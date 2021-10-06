package poc;

import java.sql.*;
public class JoinQueryExample {

	public static void main(String[] args) throws SQLException {

		Connection myConnection = null;
		Statement firstStatement = null;
		ResultSet resultData= null;
		try {
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poc", "student" , "student@123");
			System.out.println("Database connection successful!\n");
			firstStatement = myConnection.createStatement();
			resultData=firstStatement.executeQuery("select * from poc.student left join poc.department on poc.student.departmentId = poc.department.id;");			
			while (resultData.next()) {
				System.out.println(resultData.getString("studentId") + ", " + resultData.getString("fullName")+ ", " + resultData.getString("lastName")+ ", " + resultData.getString("departmentId")+ ", " + resultData.getString("joiningDate")+ ", " + resultData.getString("studentDob")+ ", " + resultData.getString("mobileNo")+ ", " + resultData.getString("email")+ ", " + resultData.getString("id")+ ", " + resultData.getString("department"));
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

