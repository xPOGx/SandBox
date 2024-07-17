package idp.cs.pattern.ext

interface Transport {
    fun sound()
}

interface TransportFactory<T: Transport> {
    fun createTransport(): T
}

class Car : Transport {
    override fun sound() = println("Beep")
}

class Ship : Transport {
    override fun sound() = println("Horn")
}