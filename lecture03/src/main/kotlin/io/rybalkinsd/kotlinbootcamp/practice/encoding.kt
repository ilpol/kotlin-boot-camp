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
fun String.encode(): String = map { it.toLowerCase() }.map { association.get(it) ?: it }.joinToString("")
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
    var input_str = this
    var result_final = ""
    var digits = ""
    var result = ""
    var word = ""
    for (words in input_str.split(" ")) {
        var words_1 = words
        while (words_1 != "") {
            digits = find_digits(words_1)
            words_1 = words_1.removePrefix(digits)
            word = find_Word(words_1)
            if (word != "") {
                result = result + digits + (words_1[0]).toLowerCase()
                words_1 = words_1.removePrefix(word)
            } else {
                return null
            }
        }
        if (result != "") {
            result_final = result_final + " " + result
            result = ""
        } else
            return null
    }
    if ((result_final[0]) == ' ')
        return result_final.removePrefix(" ")
    else
        return result_final
}

fun find_Word(str: String?): String {
    var tmp_String = ""
    if (str != null) {
        for (letter in str) {
            tmp_String = tmp_String + letter
            for (i in alphabet) {
                if (tmp_String.toLowerCase() == i.toLowerCase()) {
                    return tmp_String
                }
            }
        }
        return ""
    }
    return ""
}
fun find_digits(str: String?): String {
    var result = ""
    if (str != null) {
        for (i in str) {
            if (i in '0'..'9')
                result = result + i
        }
        return result
    }
    return result
}
