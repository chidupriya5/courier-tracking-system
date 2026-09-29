package Courier.Tracking.System.controller;

import Courier.Tracking.System.service.CustomerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String password,
            Model model) {

        // Check whether email already exists
        if (customerService.emailExists(email)) {

            model.addAttribute(
                    "error",
                    "An account with this email already exists."
            );

            return "register";
        }

        // Register customer
        customerService.registerCustomer(
                name,
                email,
                phone,
                address,
                password
        );

        model.addAttribute(
                "success",
                "Registration successful! You can now login."
        );

        return "register";
    }
}