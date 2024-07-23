package com.internal.team.wiki.exception.user;


public class InvalidNicknameException extends RuntimeException {

    private static final String MESSAGE = "잘못된 닉네임 형식입니다.";

    public InvalidNicknameException() {
        super(MESSAGE);
    }
}
