package com.internal.team.wiki.user.domain

import com.internal.team.wiki.global.BaseTimeEntity
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "users")
@Entity
class UserEntity(

    @Embedded
    var username: Username,

    @Embedded
    val nickname: Nickname,

    @Embedded
    var password: Password,

    @Enumerated(EnumType.STRING)
    val roleType: RoleType = RoleType.USER,

    @Column(name = "deleted", nullable = false)
    var deleted: Boolean = false,

    @Column(name = "scheduled_deletion_time", nullable = true)
    var scheduledDeletionTime: LocalDateTime?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseTimeEntity() {

    fun updateDeleted() {
        this.deleted = true
    }

    fun scheduleDeletion(scheduledDeletionTime: LocalDateTime) {
        this.scheduledDeletionTime = scheduledDeletionTime
    }
}