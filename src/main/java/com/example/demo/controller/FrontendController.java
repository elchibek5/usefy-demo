package com.example.demo.controller;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FrontendController {

    private final AuthService service;

    public FrontendController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showIndex() {
        // Just render src/main/resources/templates/index.html
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(
            @Valid @ModelAttribute("user") UserRegistrationDto dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        // If validation fails, stay on the same page
        if (result.hasErrors()) {
            return "register";
        }

        try {
            // try to register user
            service.registerUser(dto);
            redirectAttributes.addFlashAttribute(
                    "success",
                    "Registration successful! You can log in."
            );
            return "redirect:/register";

        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
