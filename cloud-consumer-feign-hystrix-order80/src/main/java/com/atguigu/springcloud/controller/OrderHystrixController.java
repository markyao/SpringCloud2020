package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//@DefaultProperties(defaultFallback = "globalPaymentInfoFallBack")
@RestController
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentService;

    /**
     * 正常访问
     * http://localhost/consumer/payment/hystrix/ok/32
     *
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentService.paymentInfoOk(id);
    }

    /**
     * 超时访问
     * http://localhost/consumer/payment/hystrix/timeout/32
     *
     * @param id
     * @return
     */
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    public String paymentInfoTimeOut(Integer id) {
        return paymentService.paymentTimeOut(id);
    }

    public String paymentInfoTimeOutHandler(Integer id) {
        return "我是消费者80，对方支付系统繁忙，请10分再试,全局fallback";
    }
}
