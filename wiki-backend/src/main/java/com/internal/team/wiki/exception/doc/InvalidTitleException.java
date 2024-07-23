package com.internal.team.wiki.exception.doc;

import com.internal.team.wiki.global.error.CustomException;
import com.internal.team.wiki.global.error.ErrorCode;

public class InvalidTitleException extends CustomException {

    public InvalidTitleException() {
        super(ErrorCode.TITLE_INVALID);
    }
}
