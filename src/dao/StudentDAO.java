package dao;

import java.sql.SQLException;

import exception.StudentNotFoundException;
import model.Student;

public interface StudentDAO {

	void addStudent(Student student) throws SQLException;

	void removeStudent(int studentId) throws StudentNotFoundException;

}
