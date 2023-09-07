package com.edem.admin.dao;

import com.edem.admin.AbstractTest;
import com.edem.admin.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/java/com/edem/admin/resources/clearAll.sql","file:src/test/java/com/edem/admin/resources/ss.sql"})
class CourseDaoTest extends AbstractTest {

    private CourseDao courseDao;

    public CourseDaoTest(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Test
    void findCoursesByCourseNameContains() {
        List<Course> courses = courseDao.findCoursesByCourseNameContains("Java");
        int expectedResult = 1;

        assertEquals(expectedResult,courses.size());
    }

    @Test
    void getCoursesByStudentId() {
        List<Course> courses = courseDao.getCoursesByStudentId(1L);
    }
}