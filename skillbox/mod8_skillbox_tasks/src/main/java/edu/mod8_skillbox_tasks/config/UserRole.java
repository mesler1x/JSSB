package edu.mod8_skillbox_tasks.config;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_MANAGER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}