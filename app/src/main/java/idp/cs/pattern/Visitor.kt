package idp.cs.pattern

fun main() {
    val list = listOf(Car(), SuperCar, Bottle("Bo", 1, "Nakva"))
    val visitor = Visitor()
    list.forEach { visitor.visit(it) }
}

class Visitor {
    fun visit(any: Any) {
        when (any) {
            is Car -> {
                println("Simple car")
                any.sound()
            }

            is SuperCar -> {
                println("Nice car! Do nothing)")
            }

            else -> {
                println("Unknown type ${any.javaClass}")
            }
        }
    }
}
