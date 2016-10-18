/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skywalkers.dao;

import com.skywalkers.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aleksandar
 */
@Repository
public class GreetingDAO {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingDAO.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Find customer by id
     * @param id
     * @return 
     */
    public List<Customer> findById(long id) {
        try {
            if (id > 0) {
                return jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE id = ?", new Object[]{id}, new GreetingMapper());
            }
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
        return null;
    }
    
    /**
     * Find all
     * @return 
     */
    public List<Customer> findAll() {
        try {
            return jdbcTemplate.query("SELECT id, first_name, last_name FROM customers", new GreetingMapper());
        } catch(DataAccessException e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
    }

}
