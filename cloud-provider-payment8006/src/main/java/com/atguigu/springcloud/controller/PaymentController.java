package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class PaymentController {



    @GetMapping(value = "/payment/consul")
    public String consul() {
        return "consul:8006";
    }

}