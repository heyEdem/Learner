package com.edem.admin.Service.Imp;

import com.edem.admin.Service.CourseService;
import com.edem.admin.Service.InstructorService;
import com.edem.admin.dao.InstructorDao;
import com.edem.admin.entity.Course;
import com.edem.admin.entity.Instructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class InstructorServiceImpl implements InstructorService {
    private InstructorDao instructorDao;

    private CourseService courseService;

    public InstructorServiceImpl(InstructorDao instructorDao, CourseService courseService) {
        this.instructorDao = instructorDao;
        this.courseService = courseService;
    }

    @Override
    public Instructor loadInstructorById(Long instructorId) {
        return instructorDao.findById(instructorId).orElseThrow(()->new EntityNotFoundException("Instructor with id "+ instructorId+ " not found"));
    }

    @Override
    public List<Instructor> findInstructorsByName(String name) {
        return instructorDao.findInstructorByName(name);
    }

    @Override
    public Instructor findInstructorByEmail(String email) {
        return instructorDao.findInstructorByEmail(email);
    }

    @Override
    public Instructor createInstructor(String firstName, String lastName, String summary, String email, String password) {
        return null;
    }

    @Override
    public Instructor updateInstructor(Instructor instructor) {
        return instructorDao.save(instructor );
    }

    @Override
    public List<Instructor> fetchAllInstructors() {
        return instructorDao.findAll();
    }

    @Override
    public void removeInstructor(Long instructorId) {
        Instructor instructor = loadInstructorById(instructorId);
        for(Course course: instructor.getCourses()){
            courseService.removeCourse(course.getCourseId());
        }
        instructorDao.deleteById(instructorId);
    }
}
