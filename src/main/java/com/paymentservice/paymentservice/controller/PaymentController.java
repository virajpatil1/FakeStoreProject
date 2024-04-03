package com.paymentservice.paymentservice.controller;


import com.paymentservice.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.paymentservice.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDto createPaymentLinkRequestDto) {
        return paymentService.createPaymentLink(createPaymentLinkRequestDto.getOrderId());
    }

    @PostMapping("/webhook")
    public String handlePaymentWebhook(@RequestBody String payload) {
        System.out.println("Webhook received: " + payload);
        return "OK";
        //return paymentService.handlePaymentWebhook(payload);
    }


    @GetMapping("/{orderId}")
    public String getPaymentStatus(@PathVariable("orderId") String orderId) {

        return null;
        //return paymentService.getPaymentStatus(orderId);
    }
}
