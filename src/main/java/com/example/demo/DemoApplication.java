package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            repository.save(new Customer("Jack", "Bauerd",true));
            repository.save(new Customer("Chloe", "O'Brian",true));
            repository.save(new Customer("Kim", "Bauer",true));
            repository.save(new Customer("David", "Palmer",false));
            repository.save(new Customer("Michelle", "Dessler",true));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(item -> {
                log.info(item.toString());
            });
            log.info("findByFirstNameAndLastName:");
            repository.findByFirstNameAndLastName("Kim", "Bauer").forEach(item -> {
                log.info(item.toString());
            });
            log.info("indByIdLessThan:");
            repository.findByIdLessThan(3).forEach(item -> {
                log.info(item.toString());
            });
            log.info("findByLastNameStartingWith:");
            repository.findByLastNameStartingWith("B").forEach(item -> {
                log.info(item.toString());
            });
            log.info("findByLastNameContainingIgnoreCase:");
            repository.findByLastNameContainingIgnoreCase("d").forEach(item -> {
                log.info(item.toString());
            });

            log.info("findBoth:");
            repository.findBoth("test").forEach(i -> {
                log.info(i.toString());
            });

            log.info("bothNamed:");
            repository.bothNamed("test").forEach(i -> {
                log.info(i.toString());
            });
                        /*
                          log.info("findTop3ByFirstNameOrderByIdDesc:");
                        repository.findTop3ByFirstNameOrderByIdDesc().forEach(bauer -> {
				log.info(bauer.toString());
			});*/
        };
    }


}
