package com.store.Pizza.controller;

import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.DTO.CustomerOrdersDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerOrdersDTO getOrdersById(@PathVariable Long id) {
        return customerService.getByOrder(id);
    }


    @PostMapping
    public Customer createCustomer(@RequestBody CustomerRequest request) {
        return customerService.create(request);
    }
}
