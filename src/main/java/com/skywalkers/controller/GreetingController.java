/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skywalkers.controller;

import com.skywalkers.dao.GreetingDAO;
import com.skywalkers.entity.Customer;
import com.skywalkers.entity.Greeting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aleksandar
 */
@RestController
public class GreetingController {
    
    private static final String TEMPLATE = "Hello %s";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    GreetingDAO greetingDAO;
    
    /**
     * Greetings
     * @param name
     * @return 
     */
    @RequestMapping("/greeting")
    @ResponseBody
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
    
    /**
     * Get customer by ID
     * @param id
     * @return 
     */
    @RequestMapping("/getCustomer/{id}") 
    @ResponseBody
    public List<Customer> getCustomer(@PathVariable("id") long id) {
        if (id > 0) {
            return greetingDAO.findById(id);
        }
        return new ArrayList<>();
    }
    
    /**
     * Get all customers
     * @return 
     */
    @RequestMapping("/getCustomers")
    @ResponseBody
    public List<Customer> getCustomers() {
        return greetingDAO.findAll();
    }
    
}
