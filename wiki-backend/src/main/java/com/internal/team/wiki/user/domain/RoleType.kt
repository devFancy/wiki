package com.internal.team.wiki.user.domain

enum class RoleType (
    val roleName: String
) {
    USER("USER"),
    ADMIN("ADMIN");

    fun isNot(roleType: String): Boolean {
        return this.roleName != roleType
    }
}