package idp.kotlin.lambda

/**
 * "Фільтрування списку чисел
 * - написати функцію, яка приймає список чисел і лямбда-вираз, який визначає умову фільтрації;
 * - функція повинна повернути список елементів, які задовольняють цю умову."
 */
fun main() {
    val list = listOf(1, 2, 3)
    val result = filterNumberList(list) { it % 2 == 0 }
    println(result)
}

fun <T : Number> filterNumberList(numbers: List<T>, filter: (T) -> Boolean): List<T> {
    return numbers.filter { filter(it) }
}
