package idp.kotlin.lambda

/**
 * "Перевірка умови за допомогою лямбди
 * - написати функцію, яка приймає значення та лямбда-вираз, який визначає умову;
 * - функція повинна повернути true, якщо умова виконується для значення, або false в іншому випадку. "
 */
fun main() {
    val value = "Hi"
    val result = checkValue(value) { it.isBlank() }
    println(result)
}

fun <T> checkValue(value: T, filter: (T) -> Boolean): Boolean = filter(value)