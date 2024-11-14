package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import utility.DBconnection;

public class ScheduleDAOimpl implements ScheduleDAO {

	@Override
	 public void addCourseSchedule(int courseId, String dayOfWeek, String startTimeStr, String endTimeStr) {
        Time startTime = convertStringToTime(startTimeStr);
        Time endTime = convertStringToTime(endTimeStr);
        if (startTime == null || endTime == null) {
            System.out.println("Invalid time format. Please use HH:mm format.");
            return;
        }
        String sql = "INSERT INTO COURSESCHEDULES (course_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (Connection con = DBconnection.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ps.setString(2, dayOfWeek);
            ps.setTime(3, startTime);
            ps.setTime(4, endTime);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course schedule added successfully.");
            } else {
                System.out.println("Failed to add course schedule.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Time convertStringToTime(String timeStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            java.util.Date date = sdf.parse(timeStr);
            return new Time(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
    
    

}
