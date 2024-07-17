package idp.kotlin.lambda

data class Temp(
    val age: Int
)

/**
 * "Сортування списку об'єктів за допомогою лямбди
 * - написати функцію, яка сортує список об'єктів за певним критерієм, який визначається лямбда-виразом."
 */
fun main() {
//    val list = listOf("A", "B", "C", "a", "b", "c", "ab", "abc", "bca", "ca")
    val list = listOf(Temp(-4), Temp(1), Temp(2), Temp(0))
    val result = list.sortList { it.age != 1 }
    println(result)
}

fun <T> List<T>.sortList(filter: (T) -> Boolean): List<T> {
    return this.sortedBy { filter(it) } // false, ..., false, true, ..., true
}