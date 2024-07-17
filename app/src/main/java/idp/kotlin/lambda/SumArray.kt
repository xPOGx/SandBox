package idp.kotlin.lambda

/**
 * "Обчислення суми елементів масиву
 * - написати функцію, яка приймає масив чисел і лямбда-вираз, який визначає, який елемент масиву додавати до суми;
 * - функція повинна повернути суму відповідних елементів."
 */
fun main() {
    val array = arrayOf(1, 2, 3)
    val result = sumArray(array) { it % 2 != 0 }
    println(result)
}

inline fun <reified T: Number> sumArray(array: Array<T>, filter: (T) -> Boolean): T {
    var result = 0 as T

    for (element in array) {
        if (filter(element)) {
            result = result.plus(element) as T
        }
    }

    return result
}

fun Number.plus(other: Number): Number {
    return when (this) {
        is Long -> this.toLong() + other.toLong()
        is Int -> this.toInt() + other.toInt()
        is Short -> this.toShort() + other.toShort()
        is Byte -> this.toByte() + other.toByte()
        is Double -> this.toDouble() + other.toDouble()
        is Float -> this.toFloat() + other.toFloat()
        else -> throw RuntimeException("Unknown numeric type")
    }
}