package com.mapping.onetomany.Controller;

import com.mapping.onetomany.Model.CustomerDTO;
import com.mapping.onetomany.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/customers")
    public CustomerDTO createCustomerWithBankAccounts(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomerWithBankAccounts(customerDTO);
    }



    @GetMapping("/customers/{customerId}")
    public CustomerDTO getCustomerWithBankAccounts(@PathVariable Long customerId) {
        return customerService.getCustomerWithBankAccounts(customerId);
    }


    @GetMapping("/customers/byAccountNumber/{accountNumber}")
    public CustomerDTO getCustomerByAccountNumber(@PathVariable String accountNumber) {
        return customerService.getCustomerByAccountNumber(accountNumber);
    }

    @GetMapping("/customers/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomersWithBankAccounts() {
        List<CustomerDTO> customerDTOs = customerService.getAllCustomersWithBankAccounts();
        if (!customerDTOs.isEmpty()) {
            return ResponseEntity.ok(customerDTOs);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerDTO updatedCustomerDTO
    ) {
        return customerService.updateCustomer(customerId, updatedCustomerDTO);
    }
    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }


}

