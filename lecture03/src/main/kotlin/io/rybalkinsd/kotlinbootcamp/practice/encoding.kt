package io.rybalkinsd.kotlinbootcamp.practice
/**
 * NATO phonetic alphabet
 */
val alphabet = setOf("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu")
/**
 * A mapping for english characters to phonetic alphabet.
 * [ a -> Alfa, b -> Bravo, ...]
 */
// 1 способ
// val association: Map<Char, String> =mapOf('a' to "Alfa",'b' to "Bravo",'c' to "Charlie", 'd' to "Delta", 'e' to "Echo", 'f' to "Foxtrot", 'g' to "Golf",'h' to "Hotel", 'i'to"India", 'j' to "Juliett", 'k' to "Kilo", 'l' to "Lima", 'm' to "Mike",'n' to "November", 'o' to "Oscar", 'p' to"Papa", 'q'to"Quebec", 'r'to"Romeo", 's'to"Sierra", 't'to"Tango", 'u' to "Uniform",'v' to "Victor",'w'to "Whiskey",'x'to "Xray", 'y' to"Yankee",'z' to "Zulu")
// 2 правильный
val association: Map<Char, String> = alphabet.associateBy { it[0].toLowerCase() }
// 3 способ
// val association: Map<Char, String> = alphabet.associateBy{ x: String ->x[0].toLowerCase() }
/**
 * Extension function for String which encode it according to `association` mapping
 *
 * @return encoded string
 *
 * Example:
 * "abc".encode() == "AlfaBravoCharlie"
 *
 */
// это экстеншгн функция
// 1 способ с ошибкой
// fun String.encode(): String = this.map{ association[it]?: it}.joinToString(separator = "")
fun String.encode(): String = map { it.toLowerCase() }.map { association[it] ?: it }.joinToString("")
/**
 * A reversed mapping for association
 * [ alpha -> a, bravo -> b, ...]
 */
val reversedAssociation: Map<String, Char> = emptyMap()
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
// pull requets в эту лектю до завтр вечера чтобы доп баллы получить 3 балла
fun String.decode(): String? = TODO()
// рекурсивно можно