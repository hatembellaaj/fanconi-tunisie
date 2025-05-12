package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Service getServiceSample1() {
        return new Service().id(1L).codeService(1).nomService("nomService1");
    }

    public static Service getServiceSample2() {
        return new Service().id(2L).codeService(2).nomService("nomService2");
    }

    public static Service getServiceRandomSampleGenerator() {
        return new Service()
            .id(longCount.incrementAndGet())
            .codeService(intCount.incrementAndGet())
            .nomService(UUID.randomUUID().toString());
    }
}
