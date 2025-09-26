package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RestController
class HelloRestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Java!";
    }
} 

@Controller
class PageController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "Spring + Thymeleaf is working!");
        return "index"; // templates/index.html
    }
}
