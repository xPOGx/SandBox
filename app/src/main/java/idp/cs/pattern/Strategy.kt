package idp.cs.pattern

fun main() {
    val holder = StrategyHolder()
    holder.setStrategy(Legs).execute()

    holder.setStrategy(SuperCar).execute()

    holder.setStrategy(Rocket).execute()
}

class StrategyHolder() {
    private lateinit var strategy: Strategy

    fun setStrategy(strategy: Strategy): StrategyHolder {
        this.strategy = strategy
        return this
    }

    fun execute() {
        strategy.moveToMoon()
    }
}

interface Strategy {
    fun moveToMoon()
}

object Legs: Strategy {
    override fun moveToMoon() {
        println("Walking 2669 days")
    }
}

object SuperCar: Strategy {
    override fun moveToMoon() {
        println("Driving 99 days")
    }
}

object Rocket: Strategy {
    override fun moveToMoon() {
        println("Flying 3 days")
    }
}
