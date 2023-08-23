package com.Spring.OnetoOne.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "us_id")
    private Long us_id;

    @Column(name ="us_name")
    private String us_name;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_us_id")
    private Address address;




}
