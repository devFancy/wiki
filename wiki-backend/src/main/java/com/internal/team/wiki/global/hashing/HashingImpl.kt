package com.internal.team.wiki.global.hashing

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashingImpl : Hashing {

    override fun generateSHA256Hash(text: String): String {
        return try {
            val messageDigest = MessageDigest.getInstance("SHA-256")
            messageDigest.update(text.toByteArray())
            bytesToHex(messageDigest.digest())
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val builder = StringBuilder()
        for (b in bytes) {
            builder.append(String.format("%02x", b))
        }
        return builder.toString()
    }
}