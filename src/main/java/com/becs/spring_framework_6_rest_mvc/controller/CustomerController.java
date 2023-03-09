package com.becs.spring_framework_6_rest_mvc.controller;

import com.becs.spring_framework_6_rest_mvc.model.Customer;
import com.becs.spring_framework_6_rest_mvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId") UUID customerId,
                                         @RequestBody Customer customer) {
        customerService.pachCustomerById(customerId, customer);
        log.info("The {} Customer has been patched!", customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.deleteById(customerId);
        log.info("The {} Customer has been deleted!", customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId,
                                         @RequestBody Customer customer) {
        customerService.updateCustomer(customerId, customer);
        log.info("The {} Customer has been updated!", customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveNewCustomer(customer);
        log.info("New Customer has been saved. The new Customer ID: {}", savedCustomer.getId().toString());

        // Good practice to inform the client int the HTTP response header.
        // This case, we send back the new Customer location.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Customer> listAllCustomer() {
        log.debug("Get all Customer, in the CustomerController");
        return customerService.listCustomers();
    }

    //@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Get a Customer in the CustomerController. ID: {}", customerId.toString());
        return customerService.getCustomerById(customerId);
    }

}
