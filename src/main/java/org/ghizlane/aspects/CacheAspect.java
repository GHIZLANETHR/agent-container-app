package org.ghizlane.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import org.ghizlane.model.Agent;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(3)
public class CacheAspect {

    private record CacheKey(
            Object target,
            String method
    ) {}

    private record CacheEntry(
            long version,
            Object result
    ) {}

    private final Map<CacheKey, CacheEntry> cache =
            new HashMap<>();

    @Around("@annotation(org.ghizlane.annotations.Cachable)")
    public Object cacheResult(ProceedingJoinPoint joinPoint)
            throws Throwable {

        Agent agent = (Agent) joinPoint.getTarget();

        CacheKey key = new CacheKey(
                agent,
                joinPoint.getSignature().toLongString()
        );

        long version = agent.getVersion();

        CacheEntry entry = cache.get(key);

        // Vérifier si le résultat existe
        // et si les transactions n'ont pas changé

        if (entry != null && entry.version() == version) {

            System.out.println("[CACHE] HIT");

            return entry.result();
        }

        System.out.println("[CACHE] MISS");

        Object result = joinPoint.proceed();

        cache.put(
                key,
                new CacheEntry(version, result)
        );

        return result;
    }
}