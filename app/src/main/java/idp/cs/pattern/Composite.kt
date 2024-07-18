package idp.cs.pattern

import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    val ukraine = Composite()
    with(ukraine) {
        invest()
        invest()
        inflation()
        invest()
    }
}

class Composite {
    private var country: MutableList<Bank> = mutableListOf(Bank())

    init {
        countryValue("after Creation")
    }

    fun invest() {
        val bank = Bank()
        country.add(bank)
        countryValue("after Investment")
    }

    fun inflation() {
        country.forEach { bank ->
            val size = bank.shelter.size
            repeat(Random.nextInt(size/2..size)) {
                bank.giveMeBack()
            }
        }
        countryValue("after Inflation")
    }

    private fun countryValue(extra: String) {
        val value = country.sumOf { it.valuable() }
        println("Country value: $value $extra")
    }
}

class Bank : Money {
    val shelter: MutableList<Money> = mutableListOf()

    init {
        repeat(Random.nextInt(2, 100)) {
            shelter.add(generate())
        }
    }

    private fun generate(): Money =
        when(Random.nextInt(0..2)) {
            0 -> Dollar(Random.nextInt(1..1000))
            1 -> Euro(Random.nextInt(1..1000))
            else -> Gold(Random.nextInt(1..1000))
        }

    fun takeMyMoney(money: Money) = shelter.add(money)

    fun giveMeBack() = shelter.removeFirstOrNull()

    override fun valuable(): Double = shelter.sumOf { it.valuable() }
}

interface Money {
    fun valuable(): Double
}

class Dollar(
    private val amount: Int
) : Money {
    private val value: Double = 1.0

    override fun valuable() : Double = value * amount
}

class Euro(
    private val amount: Int
) : Money {
    private val value: Double = 1.5

    override fun valuable(): Double = value * amount
}

class Gold(
    private val amount: Int
) : Money {
    private val value: Double = 3.0

    override fun valuable(): Double = value * amount
}
