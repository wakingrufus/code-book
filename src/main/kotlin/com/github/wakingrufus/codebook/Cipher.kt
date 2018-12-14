package com.github.wakingrufus.codebook

sealed class Key
class StringKey(val key: String) : Key()
class KeyPair(val publicKey: Key, val privateKey: Key) : Key()

interface Cipher {
    fun generateKey(): Key
    fun decode(key: Key, cipherText: String): String
    fun encode(key: Key, plainText: String): String
    fun crack(cipherText: String): String
}