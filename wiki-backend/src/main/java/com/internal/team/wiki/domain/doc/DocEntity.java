package com.internal.team.wiki.domain.doc;

import com.internal.team.wiki.global.BaseEntityTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="docs")
@Entity
public class DocEntity extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String writerName;

    @Embedded
    private Title title;

    @Embedded
    private Contents contents;

    @Builder
    public DocEntity(final String writerName, final String title, final String contents) {
        this.writerName = writerName;
        this.title = new Title(title);
        this.contents = new Contents(contents);
    }

    public void change(final String title, final String contents) {
        this.title = new Title(title);
        this.contents = new Contents(contents);
    }
}
