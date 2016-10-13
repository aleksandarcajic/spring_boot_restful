/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skywalkers.repository;

import com.skywalkers.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Client repository class
 * @author aleksandar
 */
@RepositoryRestResource(collectionResourceRel = "customer", path = "customers")
public interface ClientRepository extends PagingAndSortingRepository<Customer, Long>{
    
    List<Customer> findByName(@Param("name") String name);
    
    @Query("SELECT c.Name FROM Customer c WHERE c.Id = :id")
    Customer findNameById(@Param("id") Long id);
    
    List<Customer> findByNameOrderByName(@Param("name") String name);
    
}
