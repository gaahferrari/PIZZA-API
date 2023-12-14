package com.store.Pizza.service;


import com.store.Pizza.DTO.WalletDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Wallet;
import com.store.Pizza.mapper.WalletMapper;
import com.store.Pizza.repository.CustomerRepository;
import com.store.Pizza.repository.WalletRepository;
import com.store.Pizza.request.WalletRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import com.store.Pizza.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    private final CustomerRepository customerRepository;

    public BaseBodyResponse<List<WalletDTO>> getAll(){
        return WalletMapper.toListResponse(walletRepository.findAll());
    };

    @Transactional
    public BaseBodyResponse<WalletDTO> create(WalletRequest request) throws BadRequestException {
        Optional<Customer> customer = customerRepository.findById(request.getCustomerId());
        if(customer.isEmpty()){
            throw new BadRequestException("Usuário com o id " + request.getCustomerId() + " não existe");
        }

        Wallet newWallet = walletRepository.save(WalletMapper.toWallet(request));
        if (customer.isPresent()) {
            newWallet.addCustomer(customer.get());
        }


        return WalletMapper.toResponse(newWallet);
    }
}
