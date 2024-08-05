package idp.cs.pattern

fun main() {
    Worker()
    AI()
}

abstract class Template {
    fun doSameStuff(): Boolean {
        println("Do stuff, important!")
        return true
    }
}

class Worker : Template() {
    init {
        work()
    }

    private fun work() {
        println("Worker: working 12 hours")
        if (doSameStuff()) {
            println("Paid")
        } else {
            println("Fired")
        }
    }
}

class AI : Template() {
    init {
        compute()
    }

    private fun compute() {
        println("AI: working 24/7")
        if (doSameStuff()) {
            println("Happy to help")
        } else {
            println("Refactoring...")
        }
    }
}
