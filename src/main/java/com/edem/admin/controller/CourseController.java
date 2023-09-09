package com.edem.admin.controller;

import com.edem.admin.Service.CourseService;
import com.edem.admin.Service.InstructorService;
import com.edem.admin.entity.Course;
import com.edem.admin.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CourseService courseService;
    private final InstructorService instructorService;


    public CourseController(CourseService courseService, InstructorService instructorService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    @GetMapping(value = "/index")
    public String courses (Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword){
        List<Course> courses = courseService.findCoursesByCourseName(keyword);
        model.addAttribute("listCourses",courses);
        model.addAttribute("keyword",keyword);
        return "course-views/courses";
    }

    @GetMapping(value = "/delete")
    public String deleteCourse(Long courseId, String keyword){
        courseService.removeCourse(courseId);
        return "redirect:/courses/index?keyword="+ keyword;
    }

    @GetMapping(value ="/formUpdate")
   public String formUpdate(Model model, Long courseId){
        Course course = courseService.loadCourseById(courseId);
        List<Instructor> instructors = instructorService.fetchAllInstructors();

        model.addAttribute("course",course);
        model.addAttribute("listInstructors", instructors);
        return "course-views/formUpdate";
   }

   @GetMapping(value = "/formCreate")
   public String formCreate(Model model){
        List<Instructor> instructors = instructorService.fetchAllInstructors();

        model.addAttribute("listInstructors",instructors);
        model.addAttribute("course", new Course());
        return "course-views/formCreate";
   }

   @PostMapping(value = "/save")
   public String  save (Course course){
        courseService.createOrUpdateCourse(course);
        return "redirect:/courses/index";
   }
}
