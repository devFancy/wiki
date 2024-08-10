package com.internal.team.wiki.exception

// 예외 클래스 정의
class InvalidUsernameException(message: String = "잘못된 아이디 형식입니다.") : RuntimeException(message)

class InvalidPasswordFormatException(message: String = "올바른 비밀번호 형식이 아닙니다.") : RuntimeException(message)

class InvalidNicknameException(message: String = "잘못된 닉네임 형식입니다.") : RuntimeException(message)

// 예외를 발생시키는 함수 정의

fun fail(): Nothing {
   throw IllegalArgumentException("User not found or invalid credentials")
}

fun failWithInvalidUsername(): Nothing {
   throw InvalidUsernameException()
}

fun failWithInvalidPassword(): Nothing {
   throw InvalidPasswordFormatException()
}

fun failWithInvalidNickname(): Nothing {
   throw InvalidNicknameException()
}