package com.edem.admin.Service.Imp;

import com.edem.admin.Service.CourseService;
import com.edem.admin.dao.CourseDao;
import com.edem.admin.dao.InstructorDao;
import com.edem.admin.dao.StudentDao;
import com.edem.admin.entity.Course;
import com.edem.admin.entity.Instructor;
import com.edem.admin.entity.Student;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private StudentDao studentDao;
    private CourseDao courseDao;
    private InstructorDao instructorDao;

    public CourseServiceImpl(CourseDao courseDao, InstructorDao instructorDao , StudentDao studentDao) {
        this.courseDao = courseDao;
        this.instructorDao = instructorDao;
        this.studentDao = studentDao;
    }

    @Override
    public Course loadCourseById(Long courseId) {
        return courseDao.findById(courseId).orElseThrow(()->new EntityNotFoundException("Course with id "+courseId +" not Found"));
    }

    @Override
    public Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructorId) {
        Instructor instructor = instructorDao.findById(instructorId).orElseThrow(()-> new EntityNotFoundException("Instructor with id "+instructorId+" not found"));
        return courseDao.save(new Course(courseName,courseDuration,courseDescription,instructor));
    }

    @Override
    public Course createOrUpdateCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public List<Course> findCoursesByCourseName(String keyword) {
        return courseDao.findCoursesByCourseNameContains(keyword);
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
        Student student = studentDao.findById(studentId).orElseThrow(()-> new EntityNotFoundException("Student with id "+studentId+" not found"));
        Course course = courseDao.findById(courseId).orElseThrow(()-> new EntityNotFoundException("Course with id "+courseId+" not found"));
        course.assignStudentToCourse(student);
    }

    @Override
    public List<Course> fetchAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> fetchAllCoursesForStudent(Long studentId) {
        return courseDao.getCoursesByStudentId(studentId);
    }

    @Override
    public void removeCourse(Long courseId) {
        courseDao.deleteById(courseId);
    }
}
