package com.internal.team.wiki.domain.doc;

import com.internal.team.wiki.exception.doc.InvalidContentException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@Getter
@Embeddable
public class Contents {

    @Column(name = "contents", nullable = false)
    @Lob
    private String value;

    protected Contents() {
    }

    public Contents(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidContentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contents)) {
            return false;
        }
        Contents contents = (Contents) o;
        return Objects.equals(value, contents.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
