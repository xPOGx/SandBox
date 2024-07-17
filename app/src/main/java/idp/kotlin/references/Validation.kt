package idp.kotlin.references

/**
 * "Функція валідації даних
 * - створити функцію для валідації даних користувача, яка приймає рядок та функцію-перевірку, яка визначає умову валідації;
 * - використайте посилання на функцію для передачі перевірки."
 */
fun main() {
    val value = 4
    val function: (Int, (Int) -> Boolean) -> Boolean = ::validation
    val result = function(value) { it % 2 == 0 }
    println(result)

    val func = 4::valid
    val result2 = func { it % 2 == 0 }
    println(result2)

    val user = User("Pavlo", 24)
    val validAge = user::validateAge
    println(validAge())
    println(user::validateName.invoke())

    val result3 = user.validate(User::name) { it.length > 2 }
    println(result3)
}

fun <T> User.validate(
    field: (User) -> T,
    filter: (T) -> Boolean,
): Boolean = filter(field(this))

data class User(
    val name: String,
    val age: Int,
) {
    fun validateName(): Boolean = name::valid { it.isNotBlank() && it.length > 2 }
    fun validateAge(): Boolean = validation(age) { it > 0 }
}

fun <T> validation(value: T, filter: (T) -> Boolean): Boolean = filter(value)

fun <T> T.valid(filter: (T) -> Boolean): Boolean = filter(this)