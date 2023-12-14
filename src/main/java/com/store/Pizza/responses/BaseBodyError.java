package com.store.Pizza.responses;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseBodyError {

    private String error;

    private String code;

    private String message;
}
