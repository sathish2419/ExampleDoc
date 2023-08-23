package com.spring.spring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Animal_Table")
public class Animals {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public int Id;
    private String name;
    private String sound;
    private String character;
    private boolean status = true;

}
