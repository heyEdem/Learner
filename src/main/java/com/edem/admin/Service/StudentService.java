package com.edem.admin.Service;

import com.edem.admin.entity.Student;

import java.util.List;

public interface StudentService {
    Student loadStudentById(Long studentId);
    List<Student> findStudentsByName(String name);
    List<Student> loadStudentsByEmail(String email);
    Student createStudent(String firstName, String lastName, String level, String email, String password);
    Student updateStudent(Student student);
    List<Student> fetchAllStudents();
    void removeStudent(Long studentId);
}
