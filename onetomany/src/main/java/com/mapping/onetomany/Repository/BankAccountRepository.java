package com.mapping.onetomany.Repository;

import com.mapping.onetomany.Entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

     BankAccount findByAccountNumber(String accountNumber);

}
