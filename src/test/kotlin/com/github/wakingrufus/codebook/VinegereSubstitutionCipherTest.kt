package com.github.wakingrufus.codebook

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class VinegereSubstitutionCipherTest {

    @Test
    fun `test decode`() {
        val key = SquareKey.keyphrase("white")
        val cipher = "zpdxvpazhslzbhiwzbkmznm"
        val expectedPlaintext = "diverttroopstoeastridge".toUpperCase()
        val actualPlaintext = VinegereSubstitutionCipher().decode(key, cipher)
        kotlin.test.assertEquals(expected = expectedPlaintext, actual = actualPlaintext)
    }

    @Test
    fun `test encode`() {
        val key = SquareKey.keyphrase("white")
        val expectedCipher = "zpdxvpazhslzbhiwzbkmznm".toUpperCase()
        val plainText = "diverttroopstoeastridge"
        val actualCipher = VinegereSubstitutionCipher().encode(key, plainText)
        kotlin.test.assertEquals(expected = expectedCipher, actual = actualCipher)
    }
}