package idp.cs.pattern

fun main() {
    FactoryMethod.createTransport<Car>().sound()
    val ship: Ship = FactoryMethod.createTransport()
    ship.sound()
    try {
        FactoryMethod.createTransport<Transport>()
    } catch (e: Exception) {
        println(e.message)
    }
}

object FactoryMethod {
    @Throws(IllegalStateException::class)
    inline fun <reified T : Transport> createTransport(): T =
        when(T::class) {
            Car::class -> Car() as T
            Ship::class -> Ship() as T
            else -> throw IllegalStateException("Unknown type: ${T::class}")
        }
}

interface Transport {
    fun sound()
}

class Car : Transport {
    override fun sound() = println("Beep")
}

class Ship : Transport {
    override fun sound() = println("Horn")
}
