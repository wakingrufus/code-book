package com.github.wakingrufus.codebook


interface Cipher<K : Key> {
    fun decode(key: K, cipherText: String): String
    fun encode(key: K, plainText: String): String
    fun crack(cipherText: String): Pair<K, String>
}