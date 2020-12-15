package com.bank.viewcontrollers;

import java.util.Arrays;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.bank.entities.Customer;
import com.bank.entities.Employee;
import com.bank.entities.User;
import com.bank.entities.UserRole;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/admin")
@Controller
public class AdminViewController {

    @GetMapping
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            session.setAttribute("admin", user);
            session.removeAttribute("user");
        }
        return "admin/index";
    }

    @GetMapping("/users")
    public String adminUserPage(HttpSession session, @RequestParam("role") String role, Model model) {
        User admin = getAdminFromSession(session);
        if (admin != null) {
            UserRole userRole = UserRole.valueOf(role);
            RestTemplate restTemplate = new RestTemplate();
            User[] users = restTemplate.getForObject(ApiUrls.userUrl, User[].class);
            users = (User[]) Arrays.stream(users).filter(user -> user.getRole().equals(userRole)).toArray();
            model.addAttribute("users", users);
            return "admin/users";
        }
        return "redirect:/login";
    }

    @GetMapping("/users/{id}")
    public String adminUserDetailPage(HttpSession session, @PathVariable("id") long id,
            @RequestParam("role") String role, Model model) {
        User admin = getAdminFromSession(session);
        if (admin != null) {
            UserRole userRole = UserRole.valueOf(role);
            RestTemplate restTemplate = new RestTemplate();
            if (userRole.equals(UserRole.CUSTOMER)) {
                Customer customer = restTemplate.getForObject(ApiUrls.customerUrl + "/" + id, Customer.class);
                model.addAttribute("customer", customer);
                return "admin/customer-detail";
            } else if (userRole.equals(UserRole.EMPLOYEE)) {
                Employee employee = restTemplate.getForObject(ApiUrls.employeeUrl + "/" + id, Employee.class);
                model.addAttribute("employee", employee);
                return "admin/employee-detail";
            }
            return "redirect:/admin/users";
        }
        return "redirect:/login";
    }

    @GetMapping("/create-employee")
    public String adminCreateEmployeePage(HttpSession session, Model model) {
        User admin = getAdminFromSession(session);
        if (admin != null) {
            model.addAttribute("newEmployee", new Employee());
            return "admin/create-employee";
        }
        return "redirect:/login";
    }

    @PostMapping("/create-employee")
    public String adminCreateEmployee(HttpSession session, @Valid @RequestBody Employee newEmployee) {
        User admin = getAdminFromSession(session);
        if (admin != null) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(ApiUrls.employeeUrl, newEmployee, Employee.class);
            return "redirect:/admin/users";
        }
        return "redirect:/login";
    }

    private User getAdminFromSession(HttpSession session) {
        return (User) session.getAttribute("admin");
    }
}
