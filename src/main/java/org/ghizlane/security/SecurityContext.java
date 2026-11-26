package org.ghizlane.security;

import java.util.Set;

public class SecurityContext {

    private record User(
            String username,
            Set<String> roles
    ) {}

    private static final ThreadLocal<User> currentUser =
            new ThreadLocal<>();

    public static boolean login(
            String username,
            String password) {

        logout();

        if ("admin".equals(username)
                && "1234".equals(password)) {

            currentUser.set(
                    new User("admin", Set.of("ADMIN"))
            );

            return true;
        }

        if ("agent".equals(username)
                && "1234".equals(password)) {

            currentUser.set(
                    new User("agent", Set.of("AGENT"))
            );

            return true;
        }

        if ("user".equals(username)
                && "1234".equals(password)) {

            currentUser.set(
                    new User("user", Set.of("USER"))
            );

            return true;
        }

        return false;
    }

    public static boolean isAuthenticated() {
        return currentUser.get() != null;
    }

    public static boolean hasRole(String role) {

        return isAuthenticated()
                && currentUser.get().roles().contains(role);
    }

    public static void logout() {

        currentUser.remove();
    }
}