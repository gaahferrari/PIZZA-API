package com.store.Pizza.controller;

import com.store.Pizza.DTO.WalletDTO;
import com.store.Pizza.request.WalletRequest;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.WalletService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;

    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<WalletDTO>>> getAllWallet() {
        return ResponseEntity.status(200).body(walletService.getAll());
    }

    @PostMapping
    public ResponseEntity<BaseBodyResponse<WalletDTO>> createWallet(@RequestBody WalletRequest request) throws BadRequestException {
        return ResponseEntity.status(201).body(walletService.create(request));
    }
}
