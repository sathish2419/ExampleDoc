package com.spring.mapping.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address_table")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_street")
    private String addressStreet;
}
