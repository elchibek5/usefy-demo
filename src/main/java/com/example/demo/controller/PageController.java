package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "Spring + Thymeleaf is working!");
        return "index"; // refers to templates/index.html
    }
}
