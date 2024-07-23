package com.internal.team.wiki.exception.user;


public class InvalidUsernameException extends RuntimeException {


    private static final String MESSAGE = "잘못된 아이디 형식입니다.";

    public InvalidUsernameException() {
        super(MESSAGE);
    }
}
