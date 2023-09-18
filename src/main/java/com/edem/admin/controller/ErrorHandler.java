package com.edem.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorHandler {
    @GetMapping("/403")
    public String notAuthorised(){
        return "403";
    }
}
