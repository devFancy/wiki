package com.internal.team.wiki.doc.domain

import com.internal.team.wiki.global.BaseTimeEntity
import com.internal.team.wiki.user.domain.UserEntity
import javax.persistence.*

@Entity
@Table(name = "docs")
class DocEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    var writerName: String? = null,

    @Embedded
    var title: Title,

    @Embedded
    var contents: Contents,
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    constructor(user: UserEntity, writerName: String, title: String, contents: String) : this(
        user = user,
        writerName = writerName,
        title = Title(title),
        contents = Contents(contents)
    )

    fun change(title: String, contents: String) {
        this.title = Title(title)
        this.contents = Contents(contents)
    }

    fun isUser(accessUserId: Long?): Boolean {
        return user?.id == accessUserId
    }
}