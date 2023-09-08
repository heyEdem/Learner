package com.edem.admin.controller;

import com.edem.admin.Service.CourseService;
import com.edem.admin.Service.InstructorService;
import com.edem.admin.entity.Course;
import com.edem.admin.entity.Instructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {
    private CourseService courseService;
    private InstructorService instructorService;

    public CourseController(CourseService courseService, InstructorService instructorService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/index")
    public String courses (Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword){
        List<Course> courses = courseService.findCoursesByCourseName(keyword);
        model.addAttribute("listCourses",courses);
        model.addAttribute("keyword",keyword);
        return "course-views/courses";
    }

    @GetMapping(value ="/formUpdate")
   public String formUpdate(Model model, Long courseId){
        Course course = courseService.loadCourseById(courseId);
        model.addAttribute("course",course);

        List<Instructor> instructors = instructorService.fetchAllInstructors();
        model.addAttribute("listInstructors", instructors);
        return "course-views/formUpdate";
   }

   @PostMapping(value = "/save")
   public String  save (Course course){
        courseService.createOrUpdateCourse(course);
        return "redirect:/courses/index";
   }
}
