package idp.cs.pattern

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    State().apply {
        loadData()
        loadError()
    }
}

class State {
    private var status: Status = Status.NULL
        set(value) {
            field = value
            logStatus()
        }

    init {
        logStatus()
        status = Status.INIT
    }

    fun loadData() {
        runBlocking {
            status = Status.LOADING
            delay(1000)
            status = Status.DONE
        }
    }

    fun loadError() {
        runBlocking {
            status = Status.LOADING
            delay(1000)
            status = Status.ERROR
        }
    }

    private fun logStatus() {
        println(status)
    }
}

enum class Status {
    INIT,
    LOADING,
    DONE,
    ERROR,
    NULL,
}
