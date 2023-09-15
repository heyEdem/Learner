package com.edem.admin.controller;

import com.edem.admin.Service.InstructorService;
import com.edem.admin.Service.UserService;
import com.edem.admin.entity.Instructor;
import com.edem.admin.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

import static com.edem.admin.Constants.Constants.*;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;
    private final UserService userService;

    public InstructorController(InstructorService instructorService, UserService userService) {
        this.instructorService = instructorService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String instructors(Model model, @RequestParam(name = "keyword",defaultValue = "")String keyword){
        List<Instructor> instructors = instructorService.findInstructorsByName(keyword);
        model.addAttribute(LIST_INSTRUCTORS, instructors);
        model.addAttribute(KEYWORD, keyword);
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
        model.addAttribute(INSTRUCTOR, instructor);
        return "instructor-views/formUpdate";
    }

    @PostMapping(value = "/update")
    public String update(Instructor instructor){
        instructorService.updateInstructor(instructor);
        return "redirect:/instructors/index";
    }

    @GetMapping(value = "/formCreate")
    public String create(Model model){
        model.addAttribute(INSTRUCTOR, new Instructor());
        return "instructor-views/formCreate";
    }

    @PostMapping(value = "/save")
    public String save (@Valid Instructor instructor, BindingResult bindingResult){
        User user = userService.loadUserByEmail(instructor.getUser().getEmail());
        if(user!= null)
            bindingResult.rejectValue("user.email",null,"An account already exists with this email");

        if(bindingResult.hasErrors())
            return "instructor-views/formCreate";

        instructorService.createInstructor(instructor.getFirstName(), instructor.getLastName(), instructor.getSummary(),
                instructor.getUser().getEmail(), instructor.getUser().getPassword());
        return "redirect:/instructors/index";
    }
}
