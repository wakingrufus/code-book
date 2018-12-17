package com.github.wakingrufus.codebook

import mu.KLogging
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MonoalphabeticSubstitutionCipherTest {
    companion object : KLogging()

    @Test
    fun `solve stage 1`() {
        val cipherText = "BT JPX RMLX PCUV AMLX ICVJP IBTWXVR CI M LMT’R PMTN, MTN YVCJX CDXV MWMBTRJ JPX AMTNGXRJBAH UQCT JPX QGMRJXV CI JPX YMGG CI JPX HBTW’R QMGMAX; MTN JPX HBTW RMY JPX QMVJ CI JPX PMTN JPMJ YVCJX. JPXT JPX HBTW’R ACUTJXTMTAX YMR APMTWXN, MTN PBR JPCUWPJR JVCUFGXN PBL, RC JPMJ JPX SCBTJR CI PBR GCBTR YXVX GCCRXN, MTN PBR HTXXR RLCJX CTX MWMBTRJ MTCJPXV. JPX HBTW AVBXN MGCUN JC FVBTW BT JPX MRJVCGCWXVR, JPX APMGNXMTR, MTN JPX RCCJPRMEXVR. MTN JPX HBTW RQMHX, MTN RMBN JC JPX YBRX LXT CI FMFEGCT, YPCRCXDXV RPMGG VXMN JPBR YVBJBTW, MTN RPCY LX JPX BTJXVQVXJMJBCT JPXVXCI, RPMGG FX AGCJPXN YBJP RAM"
        logger.info(MonoalphabeticSubstitutionCipher().crack(cipherText).second)
    }


    @Test
    fun `test decode caeser`() {
        val key = SubstitutionKey.passphrase("Julius Caesar")
        val cipher = "SH HK, UFKHS?"
        val expectedPlaintext = "ET TU, BRUTE?"
        val actualPlaintext = MonoalphabeticSubstitutionCipher().decode(key, cipher)
        assertEquals(expected = expectedPlaintext, actual = actualPlaintext)
    }


    @Test
    fun `test decode rot`() {
        val key = SubstitutionKey.rotation(3)
        val cipher = "YHQL, YLGL, YLFL"
        val expectedPlaintext = "veni, vidi, vici".toUpperCase()
        val actualPlaintext = MonoalphabeticSubstitutionCipher().decode(key, cipher)
        assertEquals(expected = expectedPlaintext, actual = actualPlaintext)
    }

    @Test
    fun `test encode rot`() {
        val key = SubstitutionKey.rotation(3)
        val expectedCipher = "YHQL, YLGL, YLFL"
        val plainText = "veni, vidi, vici"
        val actualCipher = MonoalphabeticSubstitutionCipher().encode(key, plainText)
        assertEquals(expected = expectedCipher, actual = actualCipher)
    }
}