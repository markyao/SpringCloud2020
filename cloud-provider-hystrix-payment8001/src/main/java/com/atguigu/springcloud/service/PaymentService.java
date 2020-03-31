package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoOk, Id:" + id + "\t哈哈";
    }

    /**
     * 超时访问
     * HystrixCommand:一旦调用服务方法失败并抛出了错误信息后,会自动调用@HystrixCommand标注好的fallbckMethod调用类中的指定方法
     * execution.isolation.thread.timeoutInMilliseconds: value=3线程超时时间3秒钟
     *
     * @param id
     * @return
     */
    public String paymentInfoTimeOut(Integer id) {
        long timeOut = 2L;
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoTimeOut, Id:" + id + "\t耗时(秒)：" + timeOut;
    }

    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoTimeOutHandler, Id:" + id + " 呜呜。。。";
    }
}
