package com.store.Pizza.service;

import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.DTO.CustomerOrdersDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.exceptions.NotFoundException;
import com.store.Pizza.mapper.CustomerMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public BaseBodyResponse<List<CustomerDTO>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new BadRequestException("A lista de usuários está vazia");
        }
        return CustomerMapper.toListResponse(customers);
    }

    public BaseBodyResponse<Customer> create(CustomerRequest request) {
        Customer customer = customerRepository.save(CustomerMapper.toCustomer(request));
        if (customer != null){
            return CustomerMapper.toResponse(customer);
        } else {
            throw new BadRequestException("Erro ao cadastrar o usuário");
        }

    }

    public BaseBodyResponse<CustomerOrdersDTO> getByOrder(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isEmpty()){
            throw new NotFoundException("Usuário com o id: " + customerId + " não foi encontrado");
        } else {
            Customer customer = customerOptional.get();
            List<Order> orders = customer.getOrders();
            return CustomerMapper.toResponseID(customer, orders);
        }
    }

    public void deleteCustomerAndWallet(Long userId) {
        Customer customer = customerRepository.findById(userId).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
        } else {
            throw new BadRequestException("Erro ao deletar o usuário");
        }


    }

}
