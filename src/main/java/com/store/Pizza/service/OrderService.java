package com.store.Pizza.service;

import com.store.Pizza.DTO.OrderDTO;
import com.store.Pizza.DTO.OrderPizzaDTO;
import com.store.Pizza.DTO.PizzaDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Order;
import com.store.Pizza.entity.Pizza;
import com.store.Pizza.mapper.OrderMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.OrderRepository;
import com.store.Pizza.repository.PizzaRepository;
import com.store.Pizza.request.OrderRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final PizzaRepository pizzaRepository;

    public List<OrderPizzaDTO> getAll() {
        return orderRepository.findAll().stream().map(order -> OrderMapper.toPizzaDTO(order)).collect(Collectors.toList());
    }

   // public void deleteOrderWithoutRemovingAssociatedEntities(Long orderId) {
     //   Order order = orderRepository.findById(orderId)
       //         .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado: " + orderId));
        //order.removeAllPizzas();

        //orderRepository.delete(order);
    //}


    @Transactional
    public OrderDTO create(OrderRequest request) {
        Optional<Customer> customer = customerRepository.findById(request.getCustomerId());

        Order newOrder = orderRepository.save(OrderMapper.toOrder(request));

        if (customer.isPresent()) {
            newOrder.addCustomer(customer.get());
        }
        return OrderMapper.toDTO(newOrder);
    }

    @Transactional
    public OrderPizzaDTO addPizzaToOrder(Long orderId, Long pizzaId) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizzaId);
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (pizzaOptional.isPresent() && orderOptional.isPresent()) {
            Pizza pizza = pizzaOptional.get();
            Order order = orderOptional.get();
            order.addPizza(pizza);
            double totalPizzaPrice = order.getPizza().stream().mapToDouble(Pizza::getPrice).sum();
            order.setOrderTotalPrice(totalPizzaPrice);
            return OrderMapper.toPizzaDTO(orderRepository.save(order));
        } else {
            return null;
        }
    }



}

