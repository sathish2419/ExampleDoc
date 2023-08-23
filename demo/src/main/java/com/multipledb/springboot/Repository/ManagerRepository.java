package com.multipledb.springboot.Repository;

import com.multipledb.springboot.Secondary.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
