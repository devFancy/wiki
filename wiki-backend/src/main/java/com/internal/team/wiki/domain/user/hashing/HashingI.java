package com.internal.team.wiki.domain.user.hashing;

public interface HashingI {

    String generateSHA256Hash(String text);
}
