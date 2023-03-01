package com.becs.spring_framework_6_rest_mvc.controller;

import com.becs.spring_framework_6_rest_mvc.model.Customer;
import com.becs.spring_framework_6_rest_mvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Customer> listAllCustomer() {
        log.debug("Get all Customer, in the CustomerController");
        return customerService.ListCustomers();
    }

    //@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Get a Customer in the CustomerController. ID: {}", customerId.toString());
        return customerService.getCustomerById(customerId);
    }

}
