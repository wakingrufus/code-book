package com.github.wakingrufus.codebook

import mu.KLogging

class VinegereSubstitutionCipher : Cipher<SquareKey> {
    companion object : KLogging()

    override fun decode(key: SquareKey, cipherText: String): String {
        val reverseKey = key.keys.map { it.map.entries.map { it.value to it.key }.toMap() }
        return cipherText
                .mapIndexed { index, char -> reverseKey[index % key.keys.size].getOrDefault(char.toUpperCase(), char) }
                .joinToString("")
    }

    override fun encode(key: SquareKey, plainText: String): String {
        return plainText
                .mapIndexed { index, char -> key.keys[index % key.keys.size].map.getOrDefault(char.toUpperCase(), char) }
                .joinToString("")
    }

    override fun crack(cipherText: String): Pair<SquareKey, String> {
//        val allKeysSeq = sequence {
//            val start = Instant.now()
//            while(start.plus(1,MINUTES).isAfter(Instant.now())){
//                yield(SubstitutionKey.random())
//            }
//        }
//        val allKeys = ('A'..'Z').toSet().permutations()
//                .flatMap { clear -> ('A'..'Z').toSet().permutations().map { clear.zip(it).toMap() } }
        //    logger.info(allKeys.size.toString())
        //     return allKeysSeq.first() to cipherText
        return SquareKey(listOf()) to ""
    }

}