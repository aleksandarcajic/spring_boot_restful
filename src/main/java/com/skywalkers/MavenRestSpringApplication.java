package com.skywalkers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class MavenRestSpringApplication implements CommandLineRunner {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MavenRestSpringApplication.class);
    
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(MavenRestSpringApplication.class, args);
    }
    
    /**
     * Create JDBC template instance
     */
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    /**
     * Add db table and put data while running Spring boot 
     * @param strings
     * @throws Exception 
     */
    @Override
    public void run(String... strings) throws Exception {
        
        LOGGER.info("RUNNING....");
        
        jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
        jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
        
        List<Object[]> splitUpNames = Arrays.asList("Aleksandar Dojcinovic", "Aleksandar Milincic", "Aleksandar Cajic")
                .stream().map(name -> name.split(" ")).collect(Collectors.toList());
        
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
        
        LOGGER.info("Data added into database....");
    }
}
