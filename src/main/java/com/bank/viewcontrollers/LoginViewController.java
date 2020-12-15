package com.bank.viewcontrollers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bank.entities.User;
import com.bank.entities.UserRole;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping(path = { "", "/login" })
@Controller
public class LoginViewController {

    @GetMapping
    public String index() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpSession session, RedirectAttributes redirectAttributes) {
        Map<String, String> request = new HashMap<>();
        request.put("username", username);
        request.put("password", password);

        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.postForObject("http://localhost:8080/api/users/me", request, User.class);

        if (user != null) {
            session.setAttribute("user", user);
            UserRole role = user.getRole();
            switch (role) {
                case ADMIN:
                    return "redirect:/admin";
                case EMPLOYEE:
                    return "redirect:/employee";
                case CUSTOMER:
                    return "redirect:/customer";
                default:
                    break;
            }
        }
        redirectAttributes.addAttribute("err", "Invalid Credentials");
        return "redirect:/login";
    }
}
