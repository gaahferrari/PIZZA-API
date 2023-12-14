package com.store.Pizza.controller;

import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.DTO.CustomerOrdersDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<CustomerDTO>>> getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAll()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseBodyResponse<CustomerOrdersDTO>> getOrdersById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(customerService.getByOrder(id));
    }


    @PostMapping
    public ResponseEntity<BaseBodyResponse<Customer>> createCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.status(201).body(customerService.create(request));
    }
}
