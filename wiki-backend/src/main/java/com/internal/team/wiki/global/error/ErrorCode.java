package com.internal.team.wiki.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Auth
    Empty_Authorization_Header_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Empty_Authorization_Header_UNAUTHORIZED", "Header에 Authorization이 존재하지 않습니다."),
    InvalidToken_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "InvalidToken_UNAUTHORIZED", "유효하지 않은 토큰입니다."),
    AUTHORIZATION_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTHORIZATION_UNAUTHORIZED", "권한이 없습니다."),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "존재하지 않는 회원입니다."),
    USER_USERNAME_INVALID(HttpStatus.BAD_REQUEST, "USER_USERNAME_INVALID", "잘못된 아이디 형식입니다."),
    USER_PASSWORD_INVALID(HttpStatus.BAD_REQUEST, "USER_PASSWORD_INVALID", "올바른 비밀번호 형식이 아닙니다."),
    USER_NICKNAME_INVALID(HttpStatus.BAD_REQUEST, "USER_NICKNAME_INVALID", "잘못된 닉네임 형식입니다."),

    // Docs
    DOCUMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "DOCUMENT_NOT_FOUND", "The document was not found."),
    TITLE_INVALID(HttpStatus.BAD_REQUEST, "TITLE_INVALID", "The title is invalid"),
    CONTENT_INVALID(HttpStatus.BAD_REQUEST, "CONTENT_INVALID", "The content is invalid"),

    // Etc
    INVALID_DTO_FIELD(HttpStatus.BAD_REQUEST, "INVALID_DTO_FIELD", "The DTO field is incorrect."),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_BODY", "The RequestBody format is incorrect."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED", "The HTTP method request is not supported."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "An unexpected error occurred on the server.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    ErrorCode(final HttpStatus httpStatus, final String code, final String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
