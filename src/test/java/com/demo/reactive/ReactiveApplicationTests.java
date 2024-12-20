package com.demo.reactive;

import com.demo.reactive.sec02.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

public class ReactiveApplicationTests extends AbstractTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAll() {
        customerRepository.findAll()
                .doOnNext(customer -> System.out.println("customer = " + customer))
                .as(StepVerifier::create)
                .expectNextCount(10)
//                .doOnComplete(() -> System.out.println("Completed"))
                .expectComplete()
                .verify();
    }

    @Test
    public void findById() {
        customerRepository.findById(3)
                .doOnNext(customer -> System.out.println("customer = " + customer))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByName() {
        customerRepository.findByName("jake")
                .doOnNext(customer -> System.out.println("customer = " + customer))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("jake@gmail.com", c.getEmail()))
                .expectComplete()
                .verify();
    }


}
