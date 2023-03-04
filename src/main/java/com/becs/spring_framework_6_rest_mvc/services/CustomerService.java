package com.becs.spring_framework_6_rest_mvc.services;

import com.becs.spring_framework_6_rest_mvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> ListCustomers();

    Customer getCustomerById(UUID id);



}