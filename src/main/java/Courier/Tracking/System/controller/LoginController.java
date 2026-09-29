package Courier.Tracking.System.controller;

import Courier.Tracking.System.model.Customer;
import Courier.Tracking.System.service.CustomerService;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final CustomerService customerService;

    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        Optional<Customer> customer =
                customerService.findByEmail(email);

        if (customer.isPresent()
                && customer.get().getPassword().equals(password)) {

            model.addAttribute(
                    "customer",
                    customer.get()
            );

            return "dashboard";
        }

        model.addAttribute(
                "error",
                "Invalid email or password!"
        );

        return "login";
    }
}