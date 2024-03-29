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
import java.util.stream.Collectors;

import static com.edem.admin.Constants.Constants.*;

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
        model.addAttribute(LIST_COURSES,courses);
        model.addAttribute(KEYWORD,keyword);
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

        model.addAttribute(COURSE,course);
        model.addAttribute(LIST_INSTRUCTORS, instructors);
        return "course-views/formUpdate";
    }

    @GetMapping(value = "/formCreate")
    public String formCreate(Model model){
        List<Instructor> instructors = instructorService.fetchAllInstructors();

        model.addAttribute(LIST_INSTRUCTORS,instructors);
        model.addAttribute("course", new Course());
        return "course-views/formCreate";
    }

   @PostMapping(value = "/save")
   public String  save (Course course){
        courseService.createOrUpdateCourse(course);
        return "redirect:/courses/index";
   }

   @GetMapping(value = "/index/student")
    public String currentStudentCourses(Model model){
        Long studentId = 1L;

        List<Course> courseList = courseService.fetchAllCoursesForStudent(studentId);
        List<Course> otherCourses= courseService.fetchAll().stream().filter(course -> !courseList.contains(course)).collect(Collectors.toList());
        model.addAttribute(LIST_COURSES,courseList);
        model.addAttribute(OTHER_COURSES,otherCourses);
        return "course-views/student-courses";
   }

   @GetMapping(value = "/enrollStudent")
    public String enrollCurrentStudentInCourse(Long courseId){
        Long studentId = 1L;

        courseService.assignStudentToCourse(courseId, studentId);
        return "redirect:/courses/index/student";
   }

   @GetMapping(value = "/index/instructor")
   public String coursesForCurrentInstructor(Model model){
        Long instructorId = 1L;

        Instructor instructor = instructorService.loadInstructorById(instructorId);
        model.addAttribute(LIST_COURSES,instructor.getCourses());
        return "course-views/instructor-courses";
   }
   @GetMapping(value = "/instructor")
   public String coursesByInstructorId(Model model, Long instructorId){

        Instructor instructor = instructorService.loadInstructorById(instructorId);
        model.addAttribute(LIST_COURSES,instructor.getCourses());
        return "course-views/instructor-courses";
   }

}
