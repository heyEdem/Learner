package com.edem.admin.dao;

import com.edem.admin.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/java/com/edem/admin/resources/clearAll.sql","file:src/test/java/com/edem/admin/resources/ss.sql"})
class CourseDaoTest extends AbstractTest {

    @Test
    void findCoursesByCourseNameContains() {
    }

    @Test
    void getCoursesByStudentId() {
    }
}