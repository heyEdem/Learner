package com.edem.admin.controller;

import com.edem.admin.Service.StudentService;
import com.edem.admin.Service.UserService;
import com.edem.admin.entity.Student;
import com.edem.admin.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.edem.admin.Constants.Constants.KEYWORD;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final UserService userService;

    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping(value = "/index")
    private String students(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword) {
        List<Student> studentList = studentService.findStudentsByName(keyword);
        model.addAttribute("nameListStudents", studentList);
        model.addAttribute(KEYWORD, keyword);
        return "student-views/students";
    }

    @GetMapping(value = "/delete")
    public String delete(Long studentId, String keyword) {
        studentService.removeStudent(studentId);
        return "redirect:/students/index?keyword=" + keyword;
    }

    @GetMapping(value = "/formUpdate")
    public String updateStudent(Model model, Long studentId) {
        Student student = studentService.loadStudentById(studentId);
        model.addAttribute("student", student);
        return "student-views/formUpdate";
    }


    @PostMapping(value = "/update")
    public String update(Student student) {
        studentService.updateStudent(student);
        return "redirect:/students/index";
    }

    @GetMapping(value = "/formCreate")
    public String formStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student-views/formCreate";
    }

    @PostMapping(value = "/save")
    public String save(Student student, BindingResult bindingResult) {
        User user = userService.loadUserByEmail(student.getUser().getEmail());
        if (user != null)
            bindingResult.rejectValue("user.email", null, "An account has already been registered with this email");
        if (bindingResult.hasErrors())
            return "student-views/formCreate";

        studentService.createStudent(student.getFirstName(), student.getLastName(),
                student.getLevel(), student.getUser().getEmail(), student.getUser().getPassword());

        return "redirect:/students/index";
    }
}
