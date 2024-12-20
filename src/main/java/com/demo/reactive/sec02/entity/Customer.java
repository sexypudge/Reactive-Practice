package com.demo.reactive.sec02.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/*
    We do not have @Entity in R2DBC.
    @Table / @Column are not really required here. but adding it here for your reference...!
 */
@Table("customer")
@Getter
@Setter
public class Customer {

    @Id
    private Integer id;

    @Column("name")
    private String name;
    private String email;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}