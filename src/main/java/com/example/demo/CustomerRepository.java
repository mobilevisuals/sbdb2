package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFirstName(String lastName);

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Customer> findByFirstNameOrLastName(String firstName, String lastName);

    List<Customer> findByIdLessThan(long id);

    List<Customer> findByIdLessThanEqual(long id);

    List<Customer> findByidGreaterThan(long id);

    List<Customer> findByLastNameStartingWith(String lastName);

    List<Customer> findByLastNameContaining(String lastName);
    List<Customer> findByLastNameContainingIgnoreCase(String lastName);

    List<Customer> findByActiveTrue();

    List<Customer> findByActiveFalse();
    // List<Customer> findBothNot(String name);
    @Query("select u from Customer u where u.firstName = ?1 and u.lastName = ?1")
    List<Customer> findBoth(String emailAddress);
    List<Customer> bothNamed(String name);
    //List<Customer> findTop3ByFirstNameOrderByIdDesc();


}