package com.foodie.odo.appservice.ports.input.listener.payment;

import com.foodie.odo.appservice.dto.PaymentResponse;

public interface PaymentResponseListener {
    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentCancelled(PaymentResponse paymentResponse);
}
