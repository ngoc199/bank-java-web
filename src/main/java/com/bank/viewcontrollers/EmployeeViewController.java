package com.bank.viewcontrollers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.bank.entities.Customer;
import com.bank.entities.Department;
import com.bank.entities.Employee;
import com.bank.entities.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/employee")
@Controller
public class EmployeeViewController {

    @GetMapping
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            RestTemplate restTemplate = new RestTemplate();

            // Get the customer
            Employee employee = restTemplate.getForObject(ApiUrls.employeeUrl + "/" + user.getId(), Employee.class);

            // Set the customer to session
            session.removeAttribute("user");
            session.setAttribute("employee", employee);
        }
        return "employee/index";
    }

    @GetMapping("/create-customer")
    public String employeeCreateCustomerPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Employee employee = getEmployeeFromSession(session);
        if (employee != null) {
            if (employee.getDepartment().equals(Department.SALE)) {
                model.addAttribute("customer", new Customer());
                return "employee/create-customer";
            }
            redirectAttributes.addAttribute("err", "You are not allowed to create new customer");
            return "redirect:/employee";
        }
        return "redirect:/login";
    }

    @PostMapping("/create-customer")
    public String employeeCreateCustomer(HttpSession session, @Valid @RequestBody Customer newCustomer,
            RedirectAttributes redirectAttributes) {
        Employee employee = getEmployeeFromSession(session);
        if (employee != null) {
            if (employee.getDepartment().equals(Department.SALE)) {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.postForObject(ApiUrls.customerUrl, newCustomer, Customer.class);
                return "redirect:/employee/customers";
            }
            redirectAttributes.addAttribute("err", "You are not allowed to create new customer");
            return "redirect:/employee";
        }
        return "redirect:/login";
    }

    @GetMapping("/customers")
    public String employeeCustomerPage(HttpSession session, Model model) {
        Employee employee = getEmployeeFromSession(session);
        if (employee != null) {

            // Get all customer
            RestTemplate restTemplate = new RestTemplate();
            Customer[] customers = restTemplate.getForObject(ApiUrls.customerUrl, Customer[].class);
            model.addAttribute("customers", customers);
            return "employee/customers";
        }
        return "redirect:/login";
    }

    @GetMapping("/customers/{id}")
    public String employeeCustomerDetailPage(HttpSession session, Model model, @PathVariable("id") long id) {
        Employee employee = getEmployeeFromSession(session);
        if (employee != null) {
            Customer customer = getCustomerFromSession(session);
            if (!customer.getId().equals(id)) {
                // Get customer by id
                RestTemplate restTemplate = new RestTemplate();
                customer = restTemplate.getForObject(ApiUrls.customerUrl + "/" + id, Customer.class);
                session.setAttribute("customer", customer);
            }
            return "employee/customer-detail";
        }
        return "redirect:/login";
    }

    @PostMapping("/customers/{id}")
    public String employeeUpdateCustomer(HttpSession session, @PathVariable("id") long id,
            @Valid @RequestBody Customer customer) {
        Employee employee = getEmployeeFromSession(session);
        if (employee != null) {
            // Update customer
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(ApiUrls.customerUrl + "/" + id, customer);
            session.setAttribute("customer", customer);
            return "redirect:/employee/customers/" + id;
        }
        return "redirect:/login";
    }

    private Employee getEmployeeFromSession(HttpSession session) {
        return (Employee) session.getAttribute("employee");
    }

    private Customer getCustomerFromSession(HttpSession session) {
        return (Customer) session.getAttribute("customer");
    }
}
