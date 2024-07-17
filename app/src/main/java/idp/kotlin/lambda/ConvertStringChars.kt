package idp.kotlin.lambda

/**
 * "Перетворення рядка за допомогою лямбди
 * - напити функцію, яка приймає рядок та лямбда-вираз, який визначає правило перетворення для кожного символу в рядку;
 * - функція повинна повернути рядок, у якому кожен символ змінено згідно з лямбда-виразом."
 */
fun main() {
    val string = "Hello World"
    val result = convertStringChars(string) { if (it.code % 2 == 0) 'A' else it }
    println(result)
}

fun convertStringChars(string: String, filter: (Char) -> Char): String {
    return string.map { filter(it) }.joinToString("")
}
