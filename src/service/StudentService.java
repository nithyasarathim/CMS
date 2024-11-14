package service;

import java.sql.SQLException;

import dao.StudentDAO;
import dao.StudentDAOimpl;
import exception.StudentNotFoundException;
import model.Student;

public class StudentService {
	
	private StudentDAO studentDAO;
	
	public StudentService() {
		this.studentDAO = new StudentDAOimpl();
	}

	public void addStudent(Student student) throws SQLException {
		studentDAO.addStudent(student);
	}

	public void removeStudent(int studentId) throws StudentNotFoundException {
		studentDAO.removeStudent(studentId);
	}

}
