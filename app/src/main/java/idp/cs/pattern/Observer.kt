package idp.cs.pattern

fun main() {
    val sub1 = Subscriber()
    val sub2 = Subscriber()
    val sub3 = Subscriber()

    val observer = Observer().apply {
        addS(sub1)
        addS(sub2)
        addS(sub3)
    }

    with(observer) {
        updateValue(20)

        removeS(sub2)
        updateValue(1)

        removeS(sub3)
        updateValue(777)
    }
}

class Observer {
    private val subscribers = mutableListOf<Subscriber>()
    private var value: Int = 0

    fun addS(s: Subscriber) = subscribers.add(s)

    fun removeS(s: Subscriber) = subscribers.remove(s)

    fun updateValue(newValue: Int) {
        value = newValue
        subscribers.forEach { it.update(value) }
    }
}

class Subscriber {
    fun update(message: Int) {
        println("I'm $this get message $message")
    }
}
