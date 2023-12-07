package com.store.Pizza.mapper;

import com.store.Pizza.DTO.WalletDTO;
import com.store.Pizza.entity.Wallet;
import com.store.Pizza.request.WalletRequest;

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
}
