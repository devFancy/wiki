package com.internal.team.wiki.doc.exception;

import com.internal.team.wiki.global.error.CustomException;
import com.internal.team.wiki.global.error.ErrorCode;

public class InvalidContentException extends CustomException {

    public InvalidContentException() {
        super(ErrorCode.CONTENT_INVALID);
    }
}
