package dao;

import exception.ScheduleNotFoundException;

public interface ScheduleDAO {

	void addCourseSchedule(int courseId, String dayOfWeek, String startTimeStr, String endTimeStr);

	void removeCourseSchedule(int scheduleId);

	void viewSchedule() throws ScheduleNotFoundException;

}
