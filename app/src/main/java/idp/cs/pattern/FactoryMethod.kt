package idp.cs.pattern

import idp.cs.pattern.ext.Car
import idp.cs.pattern.ext.Ship
import idp.cs.pattern.ext.Transport

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

