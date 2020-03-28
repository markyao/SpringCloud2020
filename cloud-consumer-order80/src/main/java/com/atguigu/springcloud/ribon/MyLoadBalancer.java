package com.atguigu.springcloud.ribon;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalancer implements LoadBalancer {
    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);;

    /**
     * Inspired by the implementation of {@link AtomicInteger#incrementAndGet()}.
     *
     * @param modulo The modulo to bound the value of the counter.
     * @return The next value.
     */
    private int incrementAndGetModulo(int modulo) {
        for (; ; ) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> list) {

        if (Objects.isNull(list) || list.isEmpty()) {
            return null;
        }
        ServiceInstance serviceInstance = list.get(incrementAndGetModulo(list.size()));
        return serviceInstance;
    }
}
