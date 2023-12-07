package com.store.Pizza.service;

import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.DTO.CustomerOrdersDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.mapper.CustomerMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.request.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream().map(c -> CustomerMapper.toDTO(c)).collect(Collectors.toList());
    }

    public Customer create(CustomerRequest request) {

        return customerRepository.save(CustomerMapper.toCustomer(request));
    }

    public CustomerOrdersDTO getByOrder(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

            List<Order> orders = customer.getOrders();
            return CustomerMapper.toOrdersDTO(customer, orders);

    }

}
