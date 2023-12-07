package com.store.Pizza.service;


import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.DTO.WalletDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Wallet;
import com.store.Pizza.mapper.CustomerMapper;
import com.store.Pizza.mapper.WalletMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.WalletRepository;
import com.store.Pizza.request.WalletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    private final CustomerRepository customerRepository;

    public List<WalletDTO> getAll(){
        return walletRepository.findAll().stream().map(w -> WalletMapper.toDTO(w)).collect(Collectors.toList());
    };

    @Transactional
    public WalletDTO create(WalletRequest request) {
        Optional<Customer> customer = customerRepository.findById(request.getCustomerId());
        Wallet newWallet = walletRepository.save(WalletMapper.toWallet(request));
        if (customer.isPresent()) {
            newWallet.addCustomer(customer.get());
        }
        return WalletMapper.toDTO(newWallet);
    }
}
