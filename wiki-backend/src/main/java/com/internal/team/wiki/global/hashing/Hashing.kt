package com.internal.team.wiki.global.hashing

// 인터페이스는 객체의 설계를 정의하는 역할을 할 뿐, 실제 구현을 가지지 않기 때문에 인스턴스를 생성할 수 없음
interface Hashing {
    fun generateSHA256Hash(text: String): String
}