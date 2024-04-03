package com.paymentservice.paymentservice.service;

import org.springframework.stereotype.Service;

@Service
public class stripPaymentService implements PaymentService{
    @Override
    public String createPaymentLink(String orderId) {
        return null;
    }
}
