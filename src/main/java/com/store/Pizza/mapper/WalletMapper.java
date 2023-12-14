package com.store.Pizza.mapper;

import com.store.Pizza.DTO.WalletDTO;
import com.store.Pizza.entity.Wallet;
import com.store.Pizza.request.WalletRequest;
import com.store.Pizza.responses.BaseBodyResponse;

import java.util.List;

public class WalletMapper {

    public static Wallet toWallet (WalletRequest request){
        return Wallet.builder()
                .balance(request.getBalance())
                .build();
    }

    public static WalletDTO toDTO (Wallet wallet){
        return WalletDTO.builder()
                .balance(wallet.getBalance())
                .id(wallet.getId())
                .customerId(wallet.getCustomer().getId())
                .customerUsername(wallet.getCustomer().getUserName())
                .customerAddress(wallet.getCustomer().getUserAddress())
                .build();
    }

    public static BaseBodyResponse<WalletDTO> toResponse(Wallet wallet){
        return BaseBodyResponse.<WalletDTO>builder()
                .company("Pizza Store")
                .description("Carteira foi criada com sucesso!")
                .result(toDTO(wallet)).build();
    }

    public static BaseBodyResponse<List<WalletDTO>> toListResponse(List<Wallet> wallets){
        List<WalletDTO> walletDTOS = wallets.stream().map(WalletMapper::toDTO).toList();
        return BaseBodyResponse.<List<WalletDTO>>builder()
                .company("Pizza Store")
                .description("Lista de carteiras")
                .result(walletDTOS)
                .build();
    }
}
