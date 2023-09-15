package com.edem.admin.controller;

import com.edem.admin.Service.InstructorService;
import com.edem.admin.entity.Instructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping("/delete")
    public String deleteInstructor(Long instructorId, String keyword){
        instructorService.removeInstructor(instructorId);
        return "redirect:/instructors/index?keyword=" + keyword;
    }

    @GetMapping(value = "/formUpdate")
    public String updateInstructor(Model model, Long instructorId){
        Instructor instructor =  instructorService.loadInstructorById(instructorId);
        model.addAttribute("instructor", instructor);
        return "instructor-views/formUpdate";
    }

    @PostMapping(value = "/update")
    public String update(Instructor instructor){
        instructorService.updateInstructor(instructor);
        return "redirect:/instructors/index";
    }
}
