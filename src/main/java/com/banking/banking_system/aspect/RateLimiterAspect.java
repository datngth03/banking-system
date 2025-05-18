package com.banking.banking_system.aspect;

import com.banking.banking_system.annotation.Ratelimited;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.BucketConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimiterAspect {

    private final ProxyManager<String> proxyManager;
    private final Supplier<BucketConfiguration> bucketConfigSupplier;

    @Around("@annotation(ratelimited)")
    public Object limitRate(ProceedingJoinPoint joinPoint, Ratelimited ratelimited) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String clientKey = request.getHeader("clientId");
        if (clientKey == null) {
            clientKey = request.getRemoteAddr();
        }

        Bucket bucket = proxyManager.builder().build(clientKey, bucketConfigSupplier.get());

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            System.out.println(">>> Token consumed, remaining: " + probe.getRemainingTokens());
            return joinPoint.proceed();
        } else {
            System.out.println(">>> Too many requests, wait for: " + probe.getNanosToWaitForRefill() / 1_000_000 + " ms");
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many requests");
        }
    }
}
