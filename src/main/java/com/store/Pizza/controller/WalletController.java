package com.store.Pizza.controller;

import com.store.Pizza.DTO.WalletDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.entity.Wallet;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.request.WalletRequest;
import com.store.Pizza.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;

    @GetMapping
    public List<WalletDTO> getAllWallet() {
        return walletService.getAll();
    }

    @PostMapping
    public WalletDTO createWallet(@RequestBody WalletRequest request){
        return walletService.create(request);
    }
}
