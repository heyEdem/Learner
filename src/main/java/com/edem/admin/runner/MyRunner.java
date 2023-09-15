//package com.edem.admin.runner;
//
//import com.edem.admin.Service.*;
//import com.edem.admin.entity.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyRunner implements CommandLineRunner {
//
//    public MyRunner(UserService userService, StudentService studentService, InstructorService instructorService, RoleService roleService, CourseService courseService) {
//        this.userService = userService;
//        this.studentService = studentService;
//        this.instructorService = instructorService;
//        this.roleService = roleService;
//        this.courseService = courseService;
//    }
//
//    private UserService userService;
//    private StudentService studentService;
//    private InstructorService instructorService;
//    private RoleService roleService;
//    private CourseService courseService;
//
//    public static final String ADMIN = "Admin";
//    public static final String STUDENT = "Student";
//    public static final String INSTRUCTOR = "Instructor";
//
//
////    @Override
////    public void run(String... args) throws Exception {
////
////                User user1 = userService.createUser("user1@gmail.com","pass1");
////                User user2 = userService.createUser("user2@gmail.com","pass2");
////
////                Role role1 = roleService.createRole(ADMIN);
////                Role role2 = roleService.createRole(INSTRUCTOR);
////                Role role3 = roleService.createRole(STUDENT);
////
////                userService.assignRolesToUsers(user1.getEmail(),ADMIN);
////                userService.assignRolesToUsers(user1.getEmail(),INSTRUCTOR);
////                userService.assignRolesToUsers(user2.getEmail(),STUDENT);
////
////                Instructor instructor1 = instructorService.createInstructor("instructor1FN","instructor2LN","Experienced Java","instructor1@gmail.com","pass1");
////                Instructor instructor2 = instructorService.createInstructor("instructor2FN","instructor2LN","Senior Instructor","instructor2@gmail.com","pass2");
////
////                Student student1 = studentService.createStudent("student1FN","student1LN","beginner","std1@gmail.com","pass1");
////                Student student2 = studentService.createStudent("student2FN","student2LN","intermediate","std2@gmail.com","pass2");
////
////                Course course1 = courseService.createCourse("Java","3 months","Java for beginners",instructor1.getInstructorId());
////                Course course2 = courseService.createCourse("C++","3 months","C++ for beginners",instructor2.getInstructorId());
////
////                courseService.assignStudentToCourse(course1.getCourseId(),student1.getStudentId());
////                courseService.assignStudentToCourse(course2.getCourseId(),student2.getStudentId());
////    };
//
//}
