package com.bank.viewcontrollers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.bank.entities.BankAccount;
import com.bank.entities.Customer;
import com.bank.entities.Exchange;
import com.bank.entities.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/customer")
@Controller
public class CustomerViewController {

    /**
     * Show the info of the customer
     * 
     * @param session
     * @return customerIndex
     */
    @GetMapping
    public String customerIndex(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            RestTemplate restTemplate = new RestTemplate();

            // Get the customer
            Customer customer = restTemplate.getForObject(ApiUrls.customerUrl + "/" + user.getId(), Customer.class);

            // Set the customer to session
            session.removeAttribute("user");
            session.setAttribute("customer", customer);
        }
        return "customer/index";
    }

    @GetMapping("/exchanges")
    public String customerExchangeHistory(HttpSession session, Model model) {
        Customer customer = getCustomerFromSession(session);
        if (customer != null) {

            // Get exchanges by customer if
            RestTemplate restTemplate = new RestTemplate();
            Exchange[] exchanges = restTemplate.getForObject(ApiUrls.exchangeUrl, Exchange[].class);
            model.addAttribute("exchanges", exchanges);
            return "customer/exchanges";
        }
        return "redirect:/login";
    }

    @GetMapping("/bank-accounts")
    public String customerBankAccounts(HttpSession session, Model model) {
        Customer customer = getCustomerFromSession(session);
        if (customer != null) {

            // Get customer bank accounts
            model.addAttribute("savingAccounts", customer.getListSavingAccounts());
            model.addAttribute("creditAccounts", customer.getListCreditAccounts());
            return "customer/bank-accounts";
        }
        return "redirect:/login";
    }

    @GetMapping("/transfer")
    public String customerTransferMoneyPage(HttpSession session, Model model) {
        Customer customer = getCustomerFromSession(session);
        if (customer != null) {

            // Show the list of customer bank accounts
            model.addAttribute("savingAccounts", customer.getListSavingAccounts());
            model.addAttribute("creditAccounts", customer.getListCreditAccounts());
            return "customer/transfer";
        }
        return "redirect:/login";
    }

    @PostMapping("/transfer")
    public String customerTransferMoney(HttpSession session, @RequestBody Map<String, Object> request, Model model) {
        Customer customer = getCustomerFromSession(session);
        if (customer != null) {

            UUID senderId = (UUID) request.get("sender");
            UUID receiverId = (UUID) request.get("receiver");
            double amount = (double) request.get("amount");

            // Get bank account by id
            RestTemplate restTemplate = new RestTemplate();
            BankAccount sender = restTemplate.getForObject(ApiUrls.bankAccountUrl + "/" + senderId, BankAccount.class);
            BankAccount receiver = restTemplate.getForObject(ApiUrls.bankAccountUrl + "/" + receiverId,
                    BankAccount.class);
            if (sender != null && receiver != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("sender", sender);
                map.put("receiver", receiver);
                map.put("amount", amount);
                Exchange exchange = restTemplate.postForObject(ApiUrls.exchangeUrl, map, Exchange.class);
                model.addAttribute("exchange", exchange);
                return "customer/transfer-result";
            }

            // Make POST request to create new exchange
        }
        return "redirect:/login";
    }

    private Customer getCustomerFromSession(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        return customer;
    }
}
