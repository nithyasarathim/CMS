package service;

import dao.ScheduleDAO;
import dao.ScheduleDAOimpl;
import exception.ScheduleNotFoundException;

public class ScheduleService {
	
	private ScheduleDAO scheduleDAO;
	
	public ScheduleService() {
		this.scheduleDAO =new ScheduleDAOimpl();
	}

	public void addCourseSchedule(int courseId, String dayOfWeek, String startTimeStr, String endTimeStr) {
		scheduleDAO.addCourseSchedule(courseId, dayOfWeek, startTimeStr, endTimeStr);
		
	}

	public void removeCourseSchedule(int scheduleId) {
		scheduleDAO.removeCourseSchedule(scheduleId);
	}

	public void viewSchedule() throws ScheduleNotFoundException {
		scheduleDAO.viewSchedule();
	}

}
