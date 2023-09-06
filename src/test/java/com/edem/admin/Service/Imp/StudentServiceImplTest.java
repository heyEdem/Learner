package com.edem.admin.Service.Imp;
import com.edem.admin.dao.StudentDao;
import com.edem.admin.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentServiceImpl studentService;
    @Test
    void testLoadStudentById() {
        Student student = new Student();
        student.setStudentId(1L);

        when(studentDao.findById(student.getStudentId())).thenReturn(Optional.of(student));
        Student expected  = studentService.loadStudentById(1L);

        assertEquals(student, expected);

    }

    @Test
    void testNotFoundByIdException(){
        assertThrows(EntityNotFoundException.class, ()->studentService.loadStudentById(any()));
    }
    @Test
    void testFindStudentsByName() {
        String name = "Java";
        studentService.findStudentsByName(name);
        verify(studentDao).findStudentByName(name);
    }

    @Test
    void testLoadStudentsByEmail() {
        String email = "test@gmail.com";
        studentService.loadStudentsByEmail(email);
        verify(studentDao).findStudentByEmail(email);
    }

    @Test
    void testCreateStudent() {
    }

    @Test
    void testUpdateStudent() {
    }

    @Test
    void testFetchAllStudents() {
    }

    @Test
    void testRemoveStudent() {
    }
}