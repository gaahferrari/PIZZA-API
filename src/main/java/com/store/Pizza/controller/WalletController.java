package com.store.Pizza.controller;

import com.store.Pizza.DTO.WalletDTO;
import com.store.Pizza.request.WalletRequest;
import com.store.Pizza.responses.BaseBodyError;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/wallet")
@Validated
public class WalletController {
    private final WalletService walletService;
    @Operation(summary = "Get All Wallets", description = "Get All Wallets", tags = {"Wallet"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<WalletDTO>>> getAllWallet() {
        return ResponseEntity.status(200).body(walletService.getAll());
    }
    @Operation(summary = "Create Wallet", description = "Create Wallet", tags = {"Wallet"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @PostMapping
    public ResponseEntity<BaseBodyResponse<WalletDTO>> createWallet(@RequestBody @Valid WalletRequest request) {
        return ResponseEntity.status(201).body(walletService.create(request));
    }
}
