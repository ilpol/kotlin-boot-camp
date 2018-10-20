import java.io.File
import java.lang.Integer.min
import java.util.Random

fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()

fun main(args: Array<String>) {
        val words = readFileAsLinesUsingReadLines("dictionary.txt")
        println("Welcome to Bulls and Cows game!")
        var check_end = false
        val attempt = 10
        while (!check_end) {
            var original = words[Random().nextInt(words.size - 1)]
            println("I offered a ${original.length}-letter word, your guess?")
            print("> ")
            for (i in 0..attempt) {
                var input = readLine() ?: ""
                if (input == original) {
                    println("You won!")
                    check_end = true
                    break
                }
                val (cows, bulls) = count_Bulls_Cows(input, original)
                println("Bulls: $bulls")
                println("Cows: $cows")
            }
            if (!check_end) {
                println("You lose!")
            }
            println("Wanna play again? Y/N")
            print("> ")
            if (readLine() == "N")
                check_end = true
            else
                check_end = false
        }
}
fun count_Bulls_Cows(input: String, original: String): Pair<Int, Int> {

        var Bulls = 0
        var Cows = 0
        var length = min(input.length, original.length)
        for ((index, i) in input.withIndex()) {
            if (i in original)
                Bulls += 1
            if (index < length && input[index] == original[index]) {
                Cows += 1
            }
        }
        return Pair(Cows, Bulls)
    }
