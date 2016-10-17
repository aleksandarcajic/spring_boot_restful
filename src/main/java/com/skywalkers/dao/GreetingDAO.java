/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skywalkers.dao;

import com.skywalkers.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author aleksandar
 */
public class GreetingDAO {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingDAO.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Customer findById(long id) {
        try {
            if (id > 0) {
                jdbcTemplate.query(
                        "SELECT id, first_name, last_name FROM customers WHERE id = ?", new Object[]{id},
                        (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
                );
            }
        } catch (DataAccessException e) {
            
        }
        return null;
    }

}
