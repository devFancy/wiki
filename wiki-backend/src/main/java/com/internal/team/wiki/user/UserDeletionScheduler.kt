package com.internal.team.wiki.user

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class UserDeletionScheduler(
    private val userService: UserService
) {
    // cron = "초 분 시간 일 월 요일", 매일 새벽 6시(06:00:00)에 수행됨
    @Scheduled(cron = "0 0 6 * * *")
    fun deleteScheduledUsers() {
        userService.deleteScheduledUser()
    }
}