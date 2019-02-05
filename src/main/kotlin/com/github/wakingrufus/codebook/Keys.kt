package com.github.wakingrufus.codebook

sealed class Key
class StringKey(val key: String) : Key()
class KeyPair(val publicKey: Key, val privateKey: Key) : Key()
class SubstitutionKey(val map: Map<Char, Char>) : Key() {
    companion object {
        fun random(): SubstitutionKey {
            val target = ('A'..'Z').shuffled()
            return SubstitutionKey(('A'..'Z').zip(target).toMap())
        }

        fun rotation(amount: Int): SubstitutionKey {
            val alpha = ('A'..'Z')
            return SubstitutionKey((alpha).zip((alpha).map { it.incWithLoop(amount) }).toMap())
        }

        fun passphrase(phrase: String): SubstitutionKey {
            val cleanedPhrase = phrase.toCharArray()
                    .map(Char::toUpperCase)
                    .filter { ('A'..'Z').contains(it) }
                    .distinct()
            val allLetters = ('A'..'Z').toMutableSet()
            var key = cleanedPhrase
            allLetters.removeAll(cleanedPhrase)
            var lastLetter = cleanedPhrase.last()
            while (allLetters.isNotEmpty()) {
                val next = lastLetter.incWithLoop(1)
                if (allLetters.contains(next)) {
                    key += next
                    allLetters.remove(next)
                }
                lastLetter = next
            }
            return SubstitutionKey(('A'..'Z').zip(key.toCharArray().toList()).toMap())
        }

        fun of(map: Map<Char, Char>): SubstitutionKey {
            return SubstitutionKey(map)
        }
    }
}

class SquareKey(val keys: List<SubstitutionKey>) : Key() {
    companion object {
        fun keyphrase(keyphrase: String): SquareKey {
            return SquareKey(keyphrase.map { SubstitutionKey.rotation(it.toLowerCase() - 'a') })
        }
    }
}

fun Char.incWithLoop(amount: Int): Char = 'A' + (this - 'A' + amount).rem(26)