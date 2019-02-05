package com.github.wakingrufus.codebook

import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertTrue


internal class KeysTest {
    @Test
    fun `test SubstitutionKey random`() {
        val sequence = sequence {
            var i = 0
            while (i < 10000) {
                i++
                yield(SubstitutionKey.random())
            }
        }
        sequence.forEach {
            it.run {
                assertEquals(actual = this.map.keys.distinct().size, expected = this.map.size)
                assertEquals(actual = this.map.values.distinct().size, expected = this.map.size)
                this.map.keys.forEach {
                    ('A'..'Z').contains(it)
                }
                this.map.values.forEach {
                    ('A'..'Z').contains(it)
                }
            }
        }
    }

    @Test
    fun `test SubstitutionKey rot`() {
        assertEquals(actual = SubstitutionKey.rotation(1).map['A'], expected = 'B')
        assertEquals(actual = SubstitutionKey.rotation(1).map['Z'], expected = 'A')
        val sequence = sequence {
            var i = 0
            while (i < 10000) {
                i++
                yield(SubstitutionKey.rotation(Random.nextInt()))
            }
        }
        sequence.forEach {
            it.run {
                assertTrue(this.map.keys.distinct().size == this.map.size)
                assertTrue(this.map.values.distinct().size == this.map.size)
                this.map.keys.forEach {
                    ('A'..'Z').contains(it)
                }
                this.map.values.forEach {
                    ('A'..'Z').contains(it)
                }
            }
        }

    }

    @Test
    fun `test passphrase`() {
        val key = SubstitutionKey.passphrase("Julius Caesar")
        assertEquals(actual = key.map['A'], expected = 'J')
        assertEquals(actual = key.map['B'], expected = 'U')
        assertEquals(actual = key.map['C'], expected = 'L')
        assertEquals(actual = key.map['D'], expected = 'I')
        assertEquals(actual = key.map['E'], expected = 'S')
        assertEquals(actual = key.map['F'], expected = 'C')
        assertEquals(actual = key.map['G'], expected = 'A')
        assertEquals(actual = key.map['H'], expected = 'E')
        assertEquals(actual = key.map['I'], expected = 'R')
        assertEquals(actual = key.map['J'], expected = 'T')
        assertEquals(actual = key.map['K'], expected = 'V')
    }

    @Test
    fun `test square key passphrase single char`() {
        val key = SquareKey.keyphrase("a")
        assertEquals(key.keys.size, 1)
        key.keys[0].map.forEach {
            assertEquals(it.key, it.value)
        }
    }

    @Test
    fun `test square key passphrase word`() {
        val key = SquareKey.keyphrase("white")
        assertEquals(actual = key.keys.size, expected = 5)
        assertEquals(actual = key.keys[0].map, expected = SubstitutionKey.rotation(22).map)
        assertEquals(actual = key.keys[1].map, expected = SubstitutionKey.rotation(7).map)
        assertEquals(actual = key.keys[2].map, expected = SubstitutionKey.rotation(8).map)
        assertEquals(actual = key.keys[3].map, expected = SubstitutionKey.rotation(19).map)
        assertEquals(actual = key.keys[4].map, expected = SubstitutionKey.rotation(4).map)
    }
}