package com.internal.team.wiki.domain.user;

import com.internal.team.wiki.domain.user.hashing.HashingI;
import com.internal.team.wiki.exception.user.InvalidUsernameException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Username {

    private static final Pattern PATTERN = Pattern.compile("^[0-9a-zA-Z]{4,16}$");

    @Column(name = "username")
    private String value;

    protected Username() {
    }

    public Username(String value) {
        this.value = value;
    }

    public static Username of(HashingI hashing, String value) {
        validate(value);
        return new Username(hashing.generateSHA256Hash(value));
    }

    private static void validate(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidUsernameException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Username username = (Username) o;
        return getValue().equals(username.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
