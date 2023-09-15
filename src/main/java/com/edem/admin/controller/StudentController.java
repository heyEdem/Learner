package com.edem.admin.controller;

import com.edem.admin.Service.StudentService;
import com.edem.admin.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.edem.admin.Constants.Constants.KEYWORD;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/index")
    private String students(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword){
        List<Student> studentList = studentService.findStudentsByName(keyword);
        model.addAttribute("nameListStudents",studentList);
        model.addAttribute(KEYWORD,keyword);
        return "student-views/students";
    }

    @GetMapping(value = "/delete")
    public String delete(Long studentId, String keyword){
        studentService.removeStudent(studentId);
        return "redirect:/students/index?keyword="+ keyword;
    }
}
