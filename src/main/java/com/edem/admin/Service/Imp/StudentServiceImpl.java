package com.edem.admin.Service.Imp;

import com.edem.admin.Service.StudentService;
import com.edem.admin.Service.UserService;
import com.edem.admin.dao.StudentDao;
import com.edem.admin.entity.Course;
import com.edem.admin.entity.Student;
import com.edem.admin.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private UserService userService;

    public StudentServiceImpl(StudentDao studentDao, UserService userService) {
        this.studentDao = studentDao;
        this.userService = userService;
    }

    @Override
    public Student loadStudentById(Long studentId) {
        return studentDao.findById(studentId).orElseThrow(
                ()-> new EntityNotFoundException("Student with id "+ studentId+ " not found"));

    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentDao.findStudentByName(name);
    }

    @Override
    public List<Student> loadStudentsByEmail(String email) {
        return studentDao.findStudentByEmail(email);
    }

    @Override
    public Student createStudent(String firstName, String lastName, String level, String email, String password) {
        User user = userService.createUser(email, password);
        userService.assignRolesToUsers(email, "Student");
        return studentDao.save(new Student(firstName,lastName,level,user ));
    }

    @Override
    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public List<Student> fetchAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public void removeStudent(Long studentId) {
        Student student = loadStudentById(studentId);
        Iterator <Course> iterator = student.getCourses().iterator();
        if(iterator.hasNext()){
            Course course = iterator.next();
            course.removeStudentFromCourse(student);
        }
        studentDao.deleteById(studentId);
    }
}
