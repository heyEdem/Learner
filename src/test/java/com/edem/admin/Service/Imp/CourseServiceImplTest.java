package com.edem.admin.Service.Imp;

import com.edem.admin.dao.CourseDao;
import com.edem.admin.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseDao courseDao;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void testLoadCourseById() {
        Course course = new Course();
        course.setCourseId(1L);

        when(courseDao.findById(any())).thenReturn(Optional.of(course));

        Course actualCourse = courseService.loadCourseById(1L);

        assertEquals(course, actualCourse);
    }

    @Test
    void testExceptionForCourseNotFound(){
        assertThrows(EntityNotFoundException.class,()->courseService.loadCourseById(2L));
    }


    @Test
    void createCourse() {
    }

    @Test
    void createOrUpdateCourse() {
    }

    @Test
    void findCoursesByCourseName() {
    }

    @Test
    void assignStudentToCourse() {
    }

    @Test
    void fetchAll() {
    }

    @Test
    void fetchAllCoursesForStudent() {
    }

    @Test
    void removeCourse() {
    }
}