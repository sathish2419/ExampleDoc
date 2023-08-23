package com.Spring.OnetoOne.Repositorys;

import com.Spring.OnetoOne.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
