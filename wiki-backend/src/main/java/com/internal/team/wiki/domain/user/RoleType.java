package com.internal.team.wiki.domain.user;

public enum RoleType {
    USER("USER"), ADMIN("ADMIN");

    private final String name;

    RoleType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isNot(String roleType) {
        return !this.name.equals(roleType);
    }
}
