package com.edem.admin.controller;

import com.edem.admin.Service.CourseService;
import com.edem.admin.entity.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {
    private CourseService courseService;

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
}
