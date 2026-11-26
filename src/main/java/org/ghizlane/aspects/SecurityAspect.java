package org.ghizlane.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import org.ghizlane.annotations.SecuredBy;
import org.ghizlane.security.SecurityContext;

@Aspect
@Component
@Order(1)
public class SecurityAspect {

    @Around("@annotation(securedBy)")
    public Object secure(
            ProceedingJoinPoint joinPoint,
            SecuredBy securedBy) throws Throwable {

        if (!SecurityContext.isAuthenticated()) {

            throw new SecurityException(
                    "Utilisateur non authentifié"
            );
        }

        for (String role : securedBy.roles()) {

            if (SecurityContext.hasRole(role)) {

                System.out.println(
                        "[SECURITY] Accès autorisé"
                );

                return joinPoint.proceed();
            }
        }

        throw new SecurityException(
                "Accès refusé : rôle insuffisant"
        );
    }
}