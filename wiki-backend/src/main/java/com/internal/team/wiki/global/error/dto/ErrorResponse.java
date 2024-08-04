package com.internal.team.wiki.global.error.dto;


import com.internal.team.wiki.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;

    public ErrorResponse(final ErrorCode errorCode) {
        this.status = errorCode.getHttpStatus().value();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(final ErrorCode errorCode, final String customMessage) {
        this.status = errorCode.getHttpStatus().value();
        this.code = errorCode.getCode();
        this.message = customMessage;
    }
}
