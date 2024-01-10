package com.store.Pizza.service;

import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.exceptions.BadRequestException;
import com.store.Pizza.exceptions.NotFoundException;
import com.store.Pizza.mapper.OrderMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.OrderRepository;
import com.store.Pizza.repository.PizzaRepository;
import com.store.Pizza.request.OrderRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final PizzaRepository pizzaRepository;

    public BaseBodyResponse<List<OrderPizzaDTO>> getAll() {
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()){
            throw new BadRequestException("A lista de pedidos está vazia");
        }
        return OrderMapper.toListResponse(orders);
    }

    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.removeOrder();
            orderRepository.delete(order);
        } else {
            throw new BadRequestException("Erro ao deletar pedido");
        }
    }


    @Transactional
    public BaseBodyResponse<OrderDTO> create(@Valid OrderRequest request) {
        Optional<Customer> customer = customerRepository.findById(request.getCustomerId());

        if(customer.isEmpty()){
            throw new BadRequestException("O usuário com o id " + request.getCustomerId() + " não existe");
        }
        Order newOrder = orderRepository.save(OrderMapper.toOrder(request));
        if (customer.isPresent()) {
            newOrder.addCustomer(customer.get());
        }
        return OrderMapper.toResponse(newOrder);
    }

    @Transactional
    public BaseBodyResponse<OrderPizzaDTO> addPizzaToOrder(Long orderId, Long pizzaId) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizzaId);
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if(pizzaOptional.isEmpty() || orderOptional.isEmpty()){
            throw new NotFoundException("Pizza ou pedido não foi encontrado");
        }
        Pizza pizza = pizzaOptional.get();
        Order order = orderOptional.get();

        order.addPizza(pizza);
        double totalPizzaPrice = order.getPizza().stream().mapToDouble(Pizza::getPrice).sum();
        order.setOrderTotalPrice(totalPizzaPrice);

        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toResponsePizza(savedOrder);
    }



}

