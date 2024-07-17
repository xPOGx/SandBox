package idp.kotlin.references

/**
 * "Сортування за допомогою функцій порівняння
 * - створити список об'єктів з різними властивостями (наприклад, людей з іменами і віками);
 * - написати функцію для сортування цього списку за певним критерієм, використовуючи посилання на функцію порівняння."
 */
fun main() {
    val list = Human.createHumanList()
    val result = list.sortHuman(::sortHumanByName)::onlyName
    println(result)
    val result2 = sortHumanList(list, ::sortHumanByAge)::onlyAge.invoke()
    println(result2)
}

fun <T : Comparable<T>> List<Human>.sortHuman(filter: (Human) -> T?): List<Human> =
    this.sortedBy(filter)

fun <T : Comparable<T>> sortHumanList(list: List<Human>, filter: (Human) -> T?): List<Human> =
    list.sortedBy(filter)

fun sortHumanByName(human: Human): String = human.name
fun List<Human>.onlyName(): List<String> = this.map(Human::name)

fun sortHumanByAge(human: Human): Int = human.age
fun List<Human>.onlyAge(): List<Int> = this.map(Human::age)

data class Human(
    val name: String,
    val age: Int,
    val income: Double? = null,
    val family: List<Human> = emptyList(),
) {
    companion object {
        fun createHumanList(): List<Human> {
            val mother = Human("Mom", 30, 2000.0)
            val father = Human("Dad", 25, 2000.0)
            val son = Human("Son", 6, family = listOf(mother, father))
            val daughter = Human("Daughter", 1, family = listOf(mother, father))
            return listOf(mother, father, son, daughter)
        }
    }
}
