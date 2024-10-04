package org.example.order.interfaces.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderErrorResponse {
    private String code;
    private String message;
}
