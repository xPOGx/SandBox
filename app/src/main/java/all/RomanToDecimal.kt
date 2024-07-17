package all

fun main() {
    val result = romanToDecimal("III")
    println(result)
}

fun romanToDecimal(roman: String): Int {
    val clearRoman = roman.trim().uppercase()
    checkOnRoman(clearRoman)

    return countRoman(clearRoman)
}

fun countRoman(roman: String): Int {
    val reversed = roman.reversed()
    var result = 0
    var lastValue = 0

    for (i in reversed.indices) {
        val char = reversed[i]
        val value = Roman.natural[char]!!
        if (value >= lastValue) result += value
        else {
            val string = String(charArrayOf(char, reversed[i - 1]))
            if (!Roman.subtractive.keys.contains(string)) fail("Wrong subtraction")
            result -= value
        }
        lastValue = value
    }

    return result
}

object Roman {
    const val ROMAN = "IVXLCDM"
    val natural = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000,
    )
    val subtractive = mapOf(
        "IV" to 4,
        "IX" to 9,
        "XL" to 40,
        "XC" to 90,
        "CD" to 400,
        "CM" to 900
    )
}

fun checkOnRoman(roman: String) {
    if (roman.isBlank()) fail("Empty string")
    if (!roman.isRoman()) fail("Roman only")
    multipleNatural(roman)
    subtractiveNotation(roman)
}

fun multipleNatural(roman: String) {
    Roman.natural.keys.forEach { char ->
        if (roman.contains(char) && roman.count { char == it } > 3) {
            if (countOccurrences(roman, char.toString().repeat(4)) > 0)
                fail("Only 3 multiple natural available")
        }
    }
}

fun subtractiveNotation(roman: String) {
    Roman.subtractive.keys.forEach {
        if (roman.contains(it)) {
            if (countOccurrences(roman, it) > 1) fail("Only one same subtraction can be")

            val index = roman.indexOf(it)
            if (index != 0 && roman[index - 1] == it[0]) fail("Wrong subtraction")
        }
    }
}

fun countOccurrences(text: String, searchText: String): Int {
    var count = 0
    var startIndex = 0

    while (startIndex < text.length) {
        val index = text.indexOf(searchText, startIndex)
        if (index >= 0) {
            count++
            startIndex = index + searchText.length
        } else break
    }

    return count
}

fun String.isRoman(): Boolean = this == this.filter { char -> char in Roman.ROMAN }

class RomanException(message: String) : Exception(message)

fun fail(message: String): Unit = throw RomanException(message)