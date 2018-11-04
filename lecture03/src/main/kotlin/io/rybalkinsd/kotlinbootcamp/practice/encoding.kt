package io.rybalkinsd.kotlinbootcamp.practice
/**
 * NATO phonetic alphabet
 */
val alphabet = setOf("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu")
/**
 * A mapping for english characters to phonetic alphabet.
 * [ a -> Alfa, b -> Bravo, ...]
 */
val association: Map<Char, String> = alphabet.associate { it[0].toLowerCase() to it }
/**
 * Extension function for String which encode it according to `association` mapping
 *
 * @return encoded string
 *
 * Example:
 * "abc".encode() == "AlfaBravoCharlie"
 *
 */
fun String.encode(): String = map { it.toLowerCase() }.map { association[it] ?: it }.joinToString("")
/**
 * A reversed mapping for association
 * [ alpha -> a, bravo -> b, ...]
 */
val reversedAssociation: Map<String, Char> = alphabet.associate { it to it[0] }
/**
 * Extension function for String which decode it according to `reversedAssociation` mapping
 *
 * @return encoded string or null if it is impossible to decode
 *
 * Example:
 * "alphabravocharlie".encode() == "abc"
 * "charliee".decode() == null
 *
 */
fun String.decode(): String? {
    var res = ""
    var input = this
    var word = ""
    for (letter in input) {
        if (letter in '0'..'9' || letter == ' ') {
            res += letter
            continue
        }
        word += letter
        if (reversedAssociation[word] != null) {
            res += (reversedAssociation[word])
            res = res.toLowerCase()
            word = ""
        }
    }
    when (word) {
        "" -> return res
        else -> return null
    }
}