package com.example.demo.controller;

import org.springframework.ui.Model;
import com.example.demo.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    // Landing Page
    @GetMapping("/")
    public String name() {
        return "index";
    }

    // Registration Page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }
}
