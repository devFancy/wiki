package com.internal.team.wiki.exception

import com.internal.team.wiki.global.error.CustomException
import com.internal.team.wiki.global.error.ErrorCode

class EmptyAuthorizationHeaderException : CustomException(ErrorCode.Empty_Authorization_Header_UNAUTHORIZED)

class InvalidTokenException(message: String) : CustomException(ErrorCode.InvalidToken_UNAUTHORIZED, message)

class NotFoundUserException(message: String) : CustomException(ErrorCode.USER_NOT_FOUND, message)

class AuthorizationException : CustomException(ErrorCode.AUTHORIZATION_UNAUTHORIZED)