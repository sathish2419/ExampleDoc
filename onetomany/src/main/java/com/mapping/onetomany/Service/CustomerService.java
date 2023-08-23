package com.mapping.onetomany.Service;

import com.mapping.onetomany.Entity.Customer;
import com.mapping.onetomany.Model.CustomerDTO;

import java.util.List;


public interface CustomerService {

    CustomerDTO createCustomerWithBankAccounts(CustomerDTO customerDTO);

    CustomerDTO getCustomerWithBankAccounts(Long customerId);

    CustomerDTO getCustomerByAccountNumber(String accountNumber);

    List<CustomerDTO> getAllCustomersWithBankAccounts();

    CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomerDTO);

    void deleteCustomer(Long customerId);


}

