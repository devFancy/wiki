package com.internal.team.wiki.domain.entity;

import com.internal.team.wiki.global.BaseEntityTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="documents")
@Entity
public class Docs extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writerName;

    private String title;

    private String contents;

    @Builder
    public Docs(final String writerName, final String title, final String contents) {
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }
}
