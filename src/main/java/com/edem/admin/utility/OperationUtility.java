package com.edem.admin.utility;

import com.edem.admin.dao.*;
import com.edem.admin.entity.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public class OperationUtility {

    public static void usersOperation(UserDao userDao){
        createUsers(userDao);
        updateUsers(userDao);
        deleteUsers(userDao);
    }


    public static void roleOperations(RoleDao roleDao){
        createRoles(roleDao);
//        updateRoles(roleDao);
//        deleteRoles(roleDao);
//        fetchRoles(roleDao);
    }

    public static void instructorOperations (UserDao userDao, InstructorDao instructorDao, RoleDao roleDao){
//        createInstructors(userDao, instructorDao,roleDao);
        updateInstructor(instructorDao);
//        removeInstructor(instructorDao);
//        fetchInstructors(instructorDao);
    }

    public static void studentOperations(UserDao userDao, StudentDao studentDao,RoleDao roleDao){
        createStudent(userDao, studentDao,roleDao);
        updateStudent(studentDao);
        deleteStudent(studentDao);
        fetchStudents(studentDao);
    }

    public static void coursesOperations(CourseDao courseDao,StudentDao studentDao, UserDao userDao, RoleDao roleDao, InstructorDao instructorDao){
        createCourse(courseDao, instructorDao);
        updateCourse(courseDao);
        deleteCourse(courseDao);
        fetchCourses(courseDao);
        assignStudentToCourse(courseDao,studentDao);
        fetchCoursesForStudents(courseDao);
    }

    private static void fetchCoursesForStudents(CourseDao courseDao) {
        courseDao.getCoursesByStudentId(1L).forEach(course -> System.out.println(course.toString()));
    }

    private static void assignStudentToCourse(CourseDao courseDao, StudentDao studentDao) {
       Optional<Student> student1 = studentDao.findById(1L);
       Optional<Student> student2 = studentDao.findById(2L);

       Course course = courseDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Course Not Found"));
       student1.ifPresent(course::assignStudentToCourse);
       student2.ifPresent(course::assignStudentToCourse);
       courseDao.save(course);
    }

    private static void fetchCourses(CourseDao courseDao) {
        courseDao.findAll().forEach(course -> System.out.println(course.toString()));
    }

    private static void deleteCourse(CourseDao courseDao) {
        courseDao.deleteById(2L);
    }

    private static void updateCourse(CourseDao courseDao) {
        Course course = courseDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Course not found"));
        course.setCourseDescription("Updated course");
        course.setCourseName("updated course");
        course.setCourseDuration("2 hours");
        courseDao.save(course);
    }

    private static void createCourse(CourseDao courseDao, InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Instructor not found"));

        Course course1 = new Course("Hibernate", "5 hours","Introduction to Hibernate", instructor);
        courseDao.save(course1);
        Course course2 = new Course("Java", "3 hours","Introduction to Java", instructor);
        courseDao.save(course2);
        Course course3 = new Course("Spring", "5 hours","Introduction to Spring", instructor);
        courseDao.save(course3);



    }


    private static void updateStudent(StudentDao studentDao) {
        Student student = studentDao.findById(2L).orElseThrow(()->new EntityNotFoundException("Student Not Found"));
        student.setFirstName("Updated Student firstname");
        student.setLastName("updated student lastname");
        studentDao.save(student);
    }


    private static void createUsers(UserDao userDao) {
        User user1 = new User("u1@gmail.com","pass1");
        userDao.save(user1);

        User user2 = new User("u2@gmail.com","pass2");
        userDao.save(user2);

        User user3 = new User("u3@gmail.com","pass2");
        userDao.save(user3);
    }
    private static void deleteUsers(UserDao userDao) {
        userDao.deleteById(3L);
    }


    private static void updateUsers(UserDao userDao){
        User user = userDao.findById(2L).orElseThrow(()->new EntityNotFoundException("User not found"));
    }

    private static void fetchRoles(RoleDao roleDao) {
        roleDao.findAll().forEach(role-> System.out.println(role.toString()));
    }

    private static void deleteRoles(RoleDao roleDao) {
        roleDao.deleteById(2L);
    }

    private static void updateRoles(RoleDao roleDao) {
        Role role = roleDao.findById(1L).orElseThrow(()->new EntityNotFoundException("Not found"));
        role.setName("New Admin");
        roleDao.save(role);

    }

    private static void createRoles(RoleDao roleDao) {
        Role role1 = new Role("Admin");
        roleDao.save(role1);

        Role role2 = new Role("Instructor");
        roleDao.save(role2);

        Role role3 = new Role("Student");
        roleDao.save(role3);
    }

    public static void assignRolesToUsers (UserDao userDao, RoleDao roleDao){
        Role role = roleDao.findByName("Admin");
        if (role == null) throw new EntityNotFoundException("Role Not Found");
        List<User> users = userDao.findAll();
        users.forEach( user-> {
            user.assignRoleToUser(role);
            userDao.save (user);
        });
    }

    private static void fetchInstructors(InstructorDao instructorDao) {
        instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }

    private static void removeInstructor(InstructorDao instructorDao) {
        instructorDao.deleteById(2L);
    }

    private static void updateInstructor(InstructorDao instructorDao) {
        Instructor instructor = instructorDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Instructor Not Found"));
        instructor.setSummary("Certified Java");
        instructorDao.save(instructor);
    }

    private static void createInstructors(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Instructor");
//        if (role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("instructor1@gmail.com","pass1");
        userDao.save(user1);
        user1.assignRoleToUser(role);
        Instructor instructor1 = new Instructor("Instructor1FN","instructor1LN","Experienced Java",user1);
        instructorDao.save(instructor1);

        User user2 = new User("instructor2@gmail.com","pass1");
        userDao.save(user2);
        user1.assignRoleToUser(role);
        Instructor instructor2 = new Instructor("Instructor2FN","instructor2LN","Senior Java",user2);
        instructorDao.save(instructor2);
    }

    private static void createStudent(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Student");
        if (role == null) throw new EntityNotFoundException("Student Not Found");

        User user1 = new User("student1@gmail.com","pass1");
        userDao.save(user1);
        user1.assignRoleToUser(role);
        Student student1 = new Student("student1FN","student1LN","beginner",user1);
        studentDao.save(student1);


        User user2 = new User("student2@gmail.com","pass2");
        userDao.save(user2);
        user2.assignRoleToUser(role);
        Student student2 = new Student("student2FN","student2LN","PHD",user2);
        studentDao.save(student2);
    }
    private static void fetchStudents(StudentDao studentDao) {
        studentDao.findAll().forEach(student -> System.out.println(student.toString()));
    }

    private static void deleteStudent(StudentDao studentDao) {
        studentDao.deleteById(1L);
    }
}
