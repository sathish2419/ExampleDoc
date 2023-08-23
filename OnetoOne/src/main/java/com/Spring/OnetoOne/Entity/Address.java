package com.Spring.OnetoOne.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address_table")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "address_id")
    private Long address_id;

    @Column(name = "add_city")
    private String add_city;

    @Column(name = "add_street")
    private String add_street;



}