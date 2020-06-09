package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Student;

public class StudentDAO {
	
	public StudentDAO() {
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}
	
	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);
	}
	
	public List<Student> getAllStudents() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			connection = openConnection();
			
			String sqlText = "SELECT * FROM Student ORDER BY lastname";
			
			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String fName = resultSet.getString("firstname");
				String lName = resultSet.getString("lastname");
				String address = resultSet.getString("streetaddress");
				String postCode = resultSet.getString("postcode");
				String postOffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, fName, lName, address, postCode, postOffice));
			}
		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			studentList = null;
		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		
		return studentList;
	}
	
	public Student getStudentByID(int givenId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Student student = new Student();
		
		try {
			connection = openConnection();
			
			String sqlText = "SELECT * FROM Student WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, givenId);

			resultSet = preparedStatement.executeQuery();
			
			boolean rowFound = false;
			
			while (resultSet.next()) {
				rowFound = true;
				
				int id = resultSet.getInt("id");
				student.setId(id);
				String fName = resultSet.getString("firstname");
				student.setfName(fName);
				String lName = resultSet.getString("lastname");
				student.setlName(lName);
				String address = resultSet.getString("streetaddress");
				student.setAddress(address);
				String postCode = resultSet.getString("postcode");
				student.setPostCode(postCode);
				String postOffice = resultSet.getString("postoffice");
				student.setPostOffice(postOffice);
			}
			
			if (rowFound == false) {
				student = null;
			}
		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getStudentByID() failed. " + sqle.getMessage() + "\n");
		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		return student;
	}

	public String getAllStudentsJSON() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			connection = openConnection();
			
			String sqlText = "SELECT * FROM Student ORDER BY lastname";
			
			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String fName = resultSet.getString("firstname");
				String lName = resultSet.getString("lastname");
				String address = resultSet.getString("streetaddress");
				String postCode = resultSet.getString("postcode");
				String postOffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, fName, lName, address, postCode, postOffice));
			}
		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudentsJSON() failed. " + sqle.getMessage() + "\n");
			studentList = null;
		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(studentList);
		
		return jsonString;
	}
	
	public int insertStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;
		
		try {
			connection = openConnection();
			
			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getfName());
			preparedStatement.setString(3, student.getlName());
			preparedStatement.setString(4, student.getAddress());
			preparedStatement.setString(5, student.getPostCode());
			preparedStatement.setString(6, student.getPostOffice());
			
			preparedStatement.executeUpdate();
			errorCode = 0;

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				errorCode = 1;
			} else {
				System.out.println("\n[ERROR] StudentDAO: insertStudent() failed. " + sqle.getMessage() + "\n");
				errorCode = -1;
			}

		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}
	
	public int deleteStudent(int studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;
		
		try {
			connection = openConnection();
			
			String sqlText = "DELETE FROM Student WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);
			
			int flag = preparedStatement.executeUpdate();
			if (flag == 1) {
				errorCode = 0;
			} else {
				errorCode = 1;
			}
			
		} catch (SQLException sqle) {
				System.out.println("\n[ERROR] StudentDAO: deleteStudent() failed. " + sqle.getMessage() + "\n");
				errorCode = -1;
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}
	
	public int updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;
		
		try {
			connection = openConnection();
			
			String sqlText = "UPDATE Student SET firstname = ?, lastname = ?, streetaddress = ?, postcode = ?, postoffice = ? WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setString(1, student.getfName());
			preparedStatement.setString(2, student.getlName());
			preparedStatement.setString(3, student.getAddress());
			preparedStatement.setString(4, student.getPostCode());
			preparedStatement.setString(5, student.getPostOffice());
			preparedStatement.setInt(6, student.getId());
			
			int flag = preparedStatement.executeUpdate();
			if (flag == 1) {
				errorCode = 0;
			} else {
				errorCode = 1;
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: deleteStudent() failed. " + sqle.getMessage() + "\n");
			errorCode = -1;
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}
}

