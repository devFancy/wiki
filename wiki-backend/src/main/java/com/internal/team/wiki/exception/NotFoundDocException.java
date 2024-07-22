package com.internal.team.wiki.exception;

import com.internal.team.wiki.global.error.CustomException;
import com.internal.team.wiki.global.error.ErrorCode;

public class NotFoundDocException extends CustomException {

    public NotFoundDocException() {
        super(ErrorCode.DOCUMENT_NOT_FOUND);
    }
}
