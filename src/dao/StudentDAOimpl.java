package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.StudentNotFoundException;

import java.sql.CallableStatement;
import model.Student;
import utility.DBconnection;


public class StudentDAOimpl implements StudentDAO {

	@Override
	public void addStudent(Student student) throws SQLException {
		
		String sql = "INSERT INTO STUDENTS (first_name, last_name, phone_number, email) VALUES (?,?,?,?) ";
		
		try(Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getPhone());
			
			ps.executeUpdate();
			ResultSet rs =ps.getGeneratedKeys();
			if(rs.next()) {
				int studentId = rs.getInt(1);
				System.out.println("Student Added successfully with ID :"+studentId);
			}
		}
	}

	@Override
	public void removeStudent(int studentId) throws StudentNotFoundException {
	    String sql = "{CALL removeStudent(?)}";
	    
	    try (Connection con = DBconnection.getConnection(); CallableStatement cs = con.prepareCall(sql)) {
	        cs.setInt(1, studentId);
	        cs.execute();
	        
	        System.out.println("Student and the related data removed successfully");
	    } catch (SQLException e) {
	        if (e.getMessage().contains("Student not found")) {
	            throw new StudentNotFoundException("Student with ID " + studentId + " not found");
	        } else {
	            e.printStackTrace();
	        }
	    }
	}


}