package org.ghizlane.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    @Around("@annotation(org.ghizlane.annotations.Log)")
    public Object log(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long start = System.nanoTime();

        String methodName =
                joinPoint.getSignature().getName();

        System.out.println("[LOG] Début : " + methodName);

        try {

            // Exécuter la méthode originale

            return joinPoint.proceed();

        } finally {

            long end = System.nanoTime();

            double duration =
                    (end - start) / 1_000_000.0;

            System.out.println(
                    "[LOG] Fin : " + methodName
                            + " | Durée : " + duration + " ms"
            );
        }
    }
}