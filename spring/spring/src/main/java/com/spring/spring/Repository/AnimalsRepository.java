package com.spring.spring.Repository;

import com.spring.spring.Entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalsRepository extends JpaRepository<Animals,Integer> {
    Animals findByName(String name);
}
