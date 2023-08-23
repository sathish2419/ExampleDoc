package com.mapping.onetomany.Service.Impl;

import com.mapping.onetomany.Entity.BankAccount;
import com.mapping.onetomany.Entity.Customer;
import com.mapping.onetomany.Model.BankAccountDTO;
import com.mapping.onetomany.Model.CustomerDTO;
import com.mapping.onetomany.Repository.BankAccountRepository;
import com.mapping.onetomany.Repository.CustomerRepository;
import com.mapping.onetomany.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;


    @Override
    public CustomerDTO createCustomerWithBankAccounts(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());

        for (BankAccountDTO bankAccountDTO : customerDTO.getBankAccounts()) {
            // Check for duplicate account numbers before adding
            if (!isAccountNumberDuplicate(customer, bankAccountDTO.getAccountNumber())) {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setAccountNumber(bankAccountDTO.getAccountNumber());
                bankAccount.setCustomer(customer);
                customer.getBankAccounts().add(bankAccount);
            }
        }

        Customer createdCustomer = customerRepository.save(customer);

        CustomerDTO createdCustomerDTO = new CustomerDTO();
        createdCustomerDTO.setId(createdCustomer.getId());
        createdCustomerDTO.setName(createdCustomer.getName());
        createdCustomerDTO.setBankAccounts(createdCustomer.getBankAccounts().stream()
                .map(bankAccount -> new BankAccountDTO(bankAccount.getId(), bankAccount.getAccountNumber()))
                .collect(Collectors.toList()));

        return createdCustomerDTO;
    }

    private boolean isAccountNumberDuplicate(Customer customer, String accountNumber) {
        return customer.getBankAccounts().stream()
                .anyMatch(bankAccount -> bankAccount.getAccountNumber().equals(accountNumber));
    }

    @Override
    public CustomerDTO getCustomerWithBankAccounts(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            List<BankAccountDTO> bankAccountDTOs = customer.getBankAccounts()
                    .stream()
                    .map(bankAccount -> new BankAccountDTO(bankAccount.getId(), bankAccount.getAccountNumber()))
                    .collect(Collectors.toList());
            return new CustomerDTO(customer.getId(), customer.getName(), bankAccountDTOs);
        }
        return null;
    }



    @Override
    public CustomerDTO getCustomerByAccountNumber(String accountNumber) {
        BankAccount bankAccount =  bankAccountRepository.findByAccountNumber(accountNumber);
        if (bankAccount != null) {
            Customer customer = bankAccount.getCustomer();
            List<BankAccountDTO> bankAccountDTOs = customer.getBankAccounts()
                    .stream()
                    .map(ba -> new BankAccountDTO(ba.getId(), ba.getAccountNumber()))
                    .collect(Collectors.toList());

            return new CustomerDTO(customer.getId(), customer.getName(), bankAccountDTOs);
        }
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomersWithBankAccounts() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getName(),
                        customer.getBankAccounts().stream()
                                .map(bankAccount -> new BankAccountDTO(bankAccount.getId(), bankAccount.getAccountNumber()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomerDTO) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            customer.setName(updatedCustomerDTO.getName());

            // Remove previous bank accounts
            customer.getBankAccounts().clear();

            // Add updated bank accounts
            customer.getBankAccounts().addAll(updatedCustomerDTO.getBankAccounts().stream()
                    .map(bankAccountDTO -> {
                        BankAccount bankAccount = new BankAccount();
                        bankAccount.setAccountNumber(bankAccountDTO.getAccountNumber());
                        bankAccount.setCustomer(customer);
                        return bankAccount;
                    })
                    .collect(Collectors.toList()));

            Customer updatedCustomer = customerRepository.save(customer);

            CustomerDTO updatedCustomerResponseDTO = new CustomerDTO();
            updatedCustomerResponseDTO.setId(updatedCustomer.getId());
            updatedCustomerResponseDTO.setName(updatedCustomer.getName());
            updatedCustomerResponseDTO.setBankAccounts(updatedCustomer.getBankAccounts().stream()
                    .map(bankAccount -> new BankAccountDTO(bankAccount.getId(), bankAccount.getAccountNumber()))
                    .collect(Collectors.toList()));

            return updatedCustomerResponseDTO;
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }


}




