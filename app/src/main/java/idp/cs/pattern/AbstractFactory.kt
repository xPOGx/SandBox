package idp.cs.pattern

fun main() {
    AbstractFactory.ShipFactory.createTransport().sound()
    AbstractFactory.CarFactory.createTransport().sound()
}

interface TransportFactory<T: Transport> {
    fun createTransport(): T
}

object AbstractFactory {
    object ShipFactory : TransportFactory<Ship> {
        override fun createTransport(): Ship = Ship()
    }

    object CarFactory : TransportFactory<Car> {
        override fun createTransport(): Car = Car()
    }
}