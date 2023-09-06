package com.edem.admin.Service.Imp;

import com.edem.admin.Service.CourseService;
import com.edem.admin.Service.UserService;
import com.edem.admin.dao.CourseDao;
import com.edem.admin.dao.InstructorDao;
import com.edem.admin.dao.RoleDao;
import com.edem.admin.entity.Course;
import com.edem.admin.entity.Instructor;
import com.edem.admin.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {
    @Mock
    private CourseDao courseDao;

    @Mock
    private CourseService courseService;
    @InjectMocks
    private InstructorServiceImpl instructorService;

    @Mock
    private InstructorDao instructorDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private UserService userService;

    @Test
    void testLoadInstructorById() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1L);

        when(instructorDao.findById(1L)).thenReturn(Optional.of(instructor));

        Instructor expected = instructorService.loadInstructorById(1L);

        assertEquals(expected,instructor);

    }

    @Test
    void testFindInstructorsByName() {
        String instructorName = "Java Corner";
        instructorService.findInstructorsByName(instructorName);

        verify(instructorDao).findInstructorByName(instructorName);
    }

    @Test
    void findInstructorByEmail() {
        String email = "javacorner@gmail.com";
        instructorService.findInstructorByEmail(email);

        verify(instructorDao).findInstructorByEmail(email);

    }

    @Test
    void testCreateInstructor() {
        User user = new User("user1@gmail.com", "pass1");

        when(userService.createUser(any(),any())).thenReturn(user);

        instructorService.createInstructor("Fname", "LName","summary","user1@gmail.com", "pass1");
        verify(instructorDao).save(any());
    }

    @Test
    void testUpdateInstructor() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(2L);

        instructorService.updateInstructor(instructor);
        verify(instructorDao).save(instructor);
    }

    @Test
    void testFetchAllInstructors() {
        instructorService.fetchAllInstructors();
        verify(instructorDao).findAll();
    }

    @Test
    void testRemoveInstructor() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1L);

        Course course = new Course();
        course.setCourseId(2L);
        instructor.getCourses().add(course);

        when(instructorDao.findById(1L)).thenReturn(Optional.of(instructor));

        instructorService.removeInstructor(1L);
        verify(instructorDao).deleteById(any());
        verify(courseService,times(1)).removeCourse(any());

    }
}