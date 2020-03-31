package com.atguigu.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentHystrixService {

    public String paymentInfoOk(Integer id) {
        return "-------PaymentFallBackService paymentInfoOk fall back";
    }

    public String paymentTimeOut(Integer id) {
        return "-------PaymentFallBackService paymentTimeOut fall back";
    }
}
