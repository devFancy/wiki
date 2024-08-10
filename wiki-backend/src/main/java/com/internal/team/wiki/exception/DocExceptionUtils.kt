package com.internal.team.wiki.exception

import com.internal.team.wiki.global.error.CustomException
import com.internal.team.wiki.global.error.ErrorCode


class InvalidContentException : CustomException(ErrorCode.CONTENT_INVALID)

class InvalidTitleException : CustomException(ErrorCode.TITLE_INVALID)

class NotFoundDocException : CustomException(ErrorCode.DOCUMENT_NOT_FOUND)