package controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import exception.CourseNotFoundException;
import exception.StudentNotFoundException;
import model.DoctoralCourse;
import model.PostgraduateCourse;
import model.Student;
import model.UndergraduateCourse;
import service.CourseService;
import service.RegistrationService;
import service.ScheduleService;
import service.StudentService;

public class CourseController {

	private final CourseService courseService;
	private final RegistrationService registrationService;
	private final ScheduleService scheduleService;
	private final StudentService studentService;
	private final BufferedReader br;
	
	public CourseController() {
		this.courseService = new CourseService();
		this.registrationService = new RegistrationService();
		this.scheduleService = new ScheduleService();
		this.studentService = new StudentService();
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void start() throws NumberFormatException, IOException, SQLException, StudentNotFoundException, CourseNotFoundException {
		boolean running =true;
		while(running)
		{
			display();
			int choice = Integer.parseInt(br.readLine());
			
			switch (choice) {
				
			case 0:
				running=false;
				System.out.println("Exited successfully");
				break;
			
			case 1:
				addStudent();
				break;
			
			case 2:
				removeStudent();
				break;
				
			case 3:
				addCourse();
				break;
			
			case 4:
				removeCourse();
				break;
			
			case 5:
				registerCourse();
				break;
			
			case 6:
				dropCourse();
				break;
				
			case 7:
				viewCourse();
				break;
				
			case 8:
				addCourseSchedule();
				break;
				
			default:
				System.out.println("Invalid option");
			}
			
		}
	}
	
	private void viewCourse() {
		courseService.viewCourse();
		
	}


	
   private void addCourseSchedule() throws IOException {
        System.out.println("Enter the course ID:");
        int courseId = Integer.parseInt(br.readLine());  
        System.out.println("Enter the day of the week:");
        String dayOfWeek = br.readLine();  
        System.out.println("Enter the start time (HH:mm):");
        String startTimeStr = br.readLine(); 
        Time startTime = convertStringToTime(startTimeStr);
        System.out.println("Enter the end time (HH:mm):");
        String endTimeStr = br.readLine(); 
        Time endTime = convertStringToTime(endTimeStr);
        scheduleService.addCourseSchedule(courseId, dayOfWeek, startTimeStr, endTimeStr);
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
	
	private void dropCourse() throws NumberFormatException, IOException {
		System.out.println("Enter the registration ID:");
		int registrationID =Integer.parseInt(br.readLine());
		registrationService.dropCourse(registrationID);
	}

	private void registerCourse() throws CourseNotFoundException, StudentNotFoundException, NumberFormatException, IOException {
		System.out.println("Enter the student ID :");
		int studentId = Integer.parseInt(br.readLine());
		System.out.println("Enter the course ID  :");
		int courseId = Integer.parseInt(br.readLine());
		registrationService.enrollCourse(studentId, courseId);
		
	}

	private void removeCourse() throws NumberFormatException, IOException, CourseNotFoundException {
		System.out.println("Enter the Course Id :");
		int courseId = Integer.parseInt(br.readLine());
		courseService.removeCourse(courseId);
		
	}

	private void addCourse() throws IOException {
		System.out.println("Enter the course name :");
		String courseName = br.readLine();
		System.out.println("Enter the course description :");
		String courseDesc = br.readLine();
		System.out.println("Enter the credits :");
		int credit = Integer.parseInt(br.readLine());
		System.out.println("Enter the course level ( UG/PG/DR) :");
		String level = br.readLine();
		System.out.println("Enter the schedule (in months):");
		int schedule = Integer.parseInt(br.readLine());
		System.out.println("Enter the max capacity :");
		int maxCap =Integer.parseInt(br.readLine());
		if ("UG".equalsIgnoreCase(level)) {
			courseService.addCourse(new UndergraduateCourse(courseName, courseDesc, credit, schedule, maxCap));
		}
		if("PG".equalsIgnoreCase(level)) {
			courseService.addCourse(new PostgraduateCourse(courseName, courseDesc, credit, schedule, maxCap));
		}
		if("DR".equalsIgnoreCase(level)) {
			courseService.addCourse(new DoctoralCourse(courseName, courseDesc, credit, schedule, maxCap));
		}else {
			System.out.println("Invalid choice !");
		}
		
	}

	private void removeStudent() throws NumberFormatException, IOException, StudentNotFoundException {
		System.out.println("Enter the student ID :");
		int StudentId =Integer.parseInt(br.readLine());
		studentService.removeStudent(StudentId);
	}

	private void addStudent() throws IOException, SQLException {
		System.out.println("Enter the first name of the student :");
		String first_name = br.readLine();
		System.out.println("Enter the last name of the student :");
		String last_name = br.readLine();
		System.out.println("Enter the phone number :");
		String phone = br.readLine();
		System.out.println("Enter the email ID of the student :");
		String email = br.readLine();
		studentService.addStudent(new Student(first_name, last_name, phone, email));
	}

	public void display() {
	    System.out.println("==========================================");
	    System.out.println("|       COURSE REGISTRATION SYSTEM       |");
	    System.out.println("==========================================");
	    System.out.println("|  [1]  ADD STUDENT                      |");
	    System.out.println("|  [2]  REMOVE STUDENT                   |");
	    System.out.println("|  [3]  ADD COURSE                       |");
	    System.out.println("|  [4]  REMOVE COURSE                    |");
	    System.out.println("|  [5]  REGISTER NEW COURSE              |");
	    System.out.println("|  [6]  DROP A COURSE                    |");
	    System.out.println("|  [7]  VIEW AVAILABLE COURSE            |");
	    System.out.println("|  [8]  ADD COURSE SCHEDULE              |");
	    System.out.println("|  [0]  EXIT                             |");
	    System.out.println("==========================================");
	    System.out.println("Please select an option (0-9): ");
	}

}
