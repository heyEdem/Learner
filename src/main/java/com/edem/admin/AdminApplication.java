package com.edem.admin;
import com.edem.admin.dao.*;
import com.edem.admin.utility.OperationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AdminApplication implements CommandLineRunner {
	private UserDao userDao;
	private CourseDao courseDao;
	private InstructorDao instructorDao;
	private StudentDao studentDao;
	private RoleDao roleDao;

	public AdminApplication(UserDao userDao, CourseDao courseDao, InstructorDao instructorDao, StudentDao studentDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.courseDao = courseDao;
		this.instructorDao = instructorDao;
		this.studentDao = studentDao;
		this.roleDao = roleDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		OperationUtility.usersOperation(userDao);
	}
}
