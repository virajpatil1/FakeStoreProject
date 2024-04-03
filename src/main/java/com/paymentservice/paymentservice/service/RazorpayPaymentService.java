package com.paymentservice.paymentservice.service;


import com.razorpay.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;

@Service
@Primary
public class RazorpayPaymentService implements PaymentService{

    private RazorpayClient razorpayClient;

    public RazorpayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String createPaymentLink(String orderId) {

        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",1000);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",true);
            paymentLinkRequest.put("reference_id",orderId);
            paymentLinkRequest.put("expire_by",System.currentTimeMillis()/1000 + 3600);
            //paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("description","Payment for policy no :"+orderId);
            JSONObject customer = new JSONObject();
            customer.put("name","+917530003306");
            customer.put("contact","viraj patil");
            customer.put("email","virajspatil1998@gmail.com");
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",true);

            JSONObject options = new JSONObject();
            JSONObject notes = new JSONObject();
            notes.put("branch","Acme Corp Bangalore North");
            notes.put("name","Bhairav Kumar");

            JSONObject theme = new JSONObject();
            theme.put("hide_topbar",true);
            JSONObject checkout = new JSONObject();
            checkout.put("theme",theme);
            options.put("checkout",checkout);
            paymentLinkRequest.put("options",options);

            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
