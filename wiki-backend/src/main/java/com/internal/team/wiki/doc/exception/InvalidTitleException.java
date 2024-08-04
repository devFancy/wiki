package com.internal.team.wiki.doc.exception;

import com.internal.team.wiki.global.error.CustomException;
import com.internal.team.wiki.global.error.ErrorCode;

public class InvalidTitleException extends CustomException {

    public InvalidTitleException() {
        super(ErrorCode.TITLE_INVALID);
    }
}
