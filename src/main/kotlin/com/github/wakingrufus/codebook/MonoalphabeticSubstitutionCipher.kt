package com.github.wakingrufus.codebook

import com.marcinmoskala.math.permutations
import mu.KLogging
import java.time.Instant
import java.time.temporal.ChronoUnit.MINUTES

class MonoalphabeticSubstitutionCipher : Cipher<SubstitutionKey> {
    companion object : KLogging()

    override fun decode(key: SubstitutionKey, cipherText: String): String {
        val reverseMap = key.map.entries.map { it.value to it.key }.toMap()
        return cipherText
                .map { cipherChar -> reverseMap.getOrDefault(cipherChar, cipherChar) }
                .joinToString("")
    }

    override fun encode(key: SubstitutionKey, plainText: String): String {
        return plainText.map { key.map.getOrDefault(it.toUpperCase(), it) }.joinToString("")
    }

    override fun crack(cipherText: String): Pair<SubstitutionKey, String> {
        val allKeysSeq = sequence {
            val start = Instant.now()
            while(start.plus(1,MINUTES).isAfter(Instant.now())){
                yield(SubstitutionKey.random())
            }
        }
//        val allKeys = ('A'..'Z').toSet().permutations()
//                .flatMap { clear -> ('A'..'Z').toSet().permutations().map { clear.zip(it).toMap() } }
    //    logger.info(allKeys.size.toString())
        return allKeysSeq.first() to cipherText
    }

}