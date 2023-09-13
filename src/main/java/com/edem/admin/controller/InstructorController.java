package com.edem.admin.controller;

import com.edem.admin.Service.InstructorService;
import com.edem.admin.dao.InstructorDao;
import com.edem.admin.entity.Instructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/index")
    public String instructors(Model model, @RequestParam(name = "keyword",defaultValue = "")String keyword){
        List<Instructor> instructors = instructorService.findInstructorsByName(keyword);
        model.addAttribute("listInstructors", instructors);
        model.addAttribute("keyword", keyword);
        return "instructor-views/instructors";
    }
}
