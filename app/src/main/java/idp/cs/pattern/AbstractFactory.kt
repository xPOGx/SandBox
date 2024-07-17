package idp.cs.pattern

import idp.cs.pattern.ext.Car
import idp.cs.pattern.ext.Ship
import idp.cs.pattern.ext.TransportFactory

fun main() {
    AbstractFactory.ShipFactory.createTransport().sound()
    AbstractFactory.CarFactory.createTransport().sound()
}

object AbstractFactory {
    object ShipFactory : TransportFactory<Ship> {
        override fun createTransport(): Ship = Ship()
    }

    object CarFactory : TransportFactory<Car> {
        override fun createTransport(): Car = Car()
    }
}