package idp.cs.pattern

import kotlin.random.Random

fun main() {
    Flyweight().simulate()
}

class Flyweight {
    private val factory = SpaceFactory()

    fun simulate() {
        val repeats = 1000
        repeat(repeats) {
            factory.createSpaceObject(
                "Asteroid",
                Random.nextInt(10),
                Random.nextInt(10),
                Random.nextInt(10),
            )
        }
        val result = if (factory.cacheSize() == repeats) "Failed" else "Success"

        println("With $repeats repeats we have cache size ${factory.cacheSize()}: $result")
    }
}

data class Point(
    val x: Int,
    val y: Int,
)

data class SpaceObject(
    val name: String,
    val size: Int,
    val point: Point,
)

class SpaceFactory {
    private val spaceObjects: MutableList<SpaceObject> = mutableListOf()

    fun createSpaceObject(name: String, size: Int, x: Int, y: Int): SpaceObject {
        val spaceObject = spaceObjects.firstOrNull {
            it.name == name && it.size == size && it.point.x == x && it.point.y == y
        }
        return spaceObject ?: SpaceObject(name, size, Point(x, y)).also { spaceObjects.add(it) }
    }

    fun cacheSize(): Int = spaceObjects.size
}
