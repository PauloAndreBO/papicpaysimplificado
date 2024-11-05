package com.papicpaysimplificado.dtos.requests;

import java.math.BigDecimal;

// Objeto request(o corpo/body) da requisição que solicita uma transferência
public class RequestTransaction {

    public Long senderId;
    public Long receiverId;
    public BigDecimal amount;

}
