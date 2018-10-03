package io.rybalkinsd.kotlinbootcamp.practice


/**
 * NATO phonetic alphabet
 */
val alphabet = setOf("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliett", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "Xray", "Yankee", "Zulu")


/**
 * A mapping for english characters to phonetic alphabet.
 * [ a -> Alfa, b -> Bravo, ...]
 */
//1 способ
//val association: Map<Char, String> =mapOf('a' to "Alfa",'b' to "Bravo",'c' to "Charlie", 'd' to "Delta", 'e' to "Echo", 'f' to "Foxtrot", 'g' to "Golf",'h' to "Hotel", 'i'to"India", 'j' to "Juliett", 'k' to "Kilo", 'l' to "Lima", 'm' to "Mike",'n' to "November", 'o' to "Oscar", 'p' to"Papa", 'q'to"Quebec", 'r'to"Romeo", 's'to"Sierra", 't'to"Tango", 'u' to "Uniform",'v' to "Victor",'w'to "Whiskey",'x'to "Xray", 'y' to"Yankee",'z' to "Zulu")
//2 правильный
val association: Map<Char, String> = alphabet.associate { it[0].toLowerCase() to it }
//3 способ
//val association: Map<Char, String> = alphabet.associateBy{ x: String ->x[0].toLowerCase() }
/**
 * Extension function for String which encode it according to `association` mapping
 *
 * @return encoded string
 *
 * Example:
 * "abc".encode() == "AlfaBravoCharlie"
 *
 */
//это экстеншгн функция
//1 способ с ошибкой
//fun String.encode(): String = this.map{ association[it]?: it}.joinToString(separator = "")
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
//pull requets в эту лектю до завтр вечера чтобы доп баллы получить 3 балла
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
                words_1 = ""
            }
        }
        if (result != "") {
            result_final = result_final + " " + result
            result = ""
        }
        else
            return null
    }
    if((result_final[0])==' ')
        return result_final.removePrefix(" ")
    else
        return result_final
}

fun find_Word(str:String?): String{
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


fun find_digits(str:String?): String{
    var result = ""
    if (str != null) {
        for(i in str) {
            if ( i in '0'..'9')
                result = result + i
        }
        return result
    }
    return result
}

//рекурсивно можно