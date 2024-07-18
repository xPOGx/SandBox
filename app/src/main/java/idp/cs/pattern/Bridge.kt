package idp.cs.pattern

import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    val robot = Robot()
    val bridge = Bridge(robot)
    bridge.moveStraight()
    bridge.randomPosition()
}

class Bridge(private val moveable: Moveable) {
    fun moveStraight() {
        repeat(10) {
            moveable.moveUp()
        }
    }

    fun randomPosition() {
        moveable.moveSomewhere()
    }
}

interface Moveable {
    fun moveUp()
    fun moveDown()
    fun moveLeft()
    fun moveRight()
    fun moveSomewhere()
}

class Robot : Moveable {
    private var coordinates = 0 to 0 // X to Y

    override fun moveUp() {
        coordinates = coordinates.first to coordinates.second + 1
        shareLocation()
    }

    override fun moveDown() {
        coordinates = coordinates.first to coordinates.second - 1
        shareLocation()
    }

    override fun moveLeft() {
        coordinates = coordinates.first - 1 to coordinates.second
        shareLocation()
    }

    override fun moveRight() {
        coordinates = coordinates.first + 1 to coordinates.second
        shareLocation()
    }

    override fun moveSomewhere() {
        repeat(10) {
            when (Random.nextInt(0..3)) {
                0 -> moveUp()
                1 -> moveRight()
                2 -> moveDown()
                3 -> moveLeft()
            }
        }
    }

    private fun shareLocation() {
        println("I'm at ${coordinates.first}:${coordinates.second}")
    }
}
