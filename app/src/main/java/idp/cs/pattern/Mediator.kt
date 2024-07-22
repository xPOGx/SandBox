package idp.cs.pattern

import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    Mediator().logic()
}

class Mediator {
    private val airport = Airport()

    private fun availablePlaneInAir(): Boolean = airport.air.isNotEmpty()
    private fun availablePlaneInAirport(): Boolean = airport.lanes.any { it.value != null }
    private fun availableLane(): Boolean = airport.lanes.any { it.value == null }

    fun logic() {
        repeat(10) {
            Random.nextInt(0..1).let {
                if (it == 0) {
                    if (availablePlaneInAirport()) {
                        airport.launchPlane()
                    } else {
                        println("Airport is empty to launch any plane")
                    }
                } else {
                    if (availablePlaneInAir()) {
                        if (availableLane()) {
                            airport.landPlane()
                        } else {
                            println("Airport is full to land any plane")
                        }
                    } else {
                        println("Air is empty to land any plane")
                    }
                }
            }
        }
    }
}

class Airport {
    val lanes: MutableMap<Int, Plane?> =
        mutableMapOf(
            0 to null,
            1 to null,
            2 to null
        )

    val air: MutableList<Plane> =
        mutableListOf(
            Plane("First", "X1"),
            Plane("Second", "X2"),
            Plane("Third", "X3"),
            Plane("Fourth", "X4")
        )

    fun launchPlane() {
        run {
            lanes.forEach { lane ->
                lane.value?.let { plane ->
                    lanes[lane.key] = null
                    air.add(plane.copy(isLanded = false))
                    println("Plane $plane now in Air")
                    return@run
                }
            }
        }
    }

    fun landPlane() {
        run {
            lanes.forEach { lane ->
                val plane = lane.value
                if (plane == null) {
                    val planeInAir = air.removeFirstOrNull()
                    lanes[lane.key] = planeInAir?.copy(isLanded = true)
                    println("Plane $planeInAir now on ${lane.key} lane")
                    return@run
                }
            }
        }
    }
}

data class Plane(
    val name: String,
    val model: String,
    var isLanded: Boolean = false,
) {
    override fun toString(): String {
        return buildString {
            append("[$name;$model;")
            append(
                if (isLanded) {
                    "onLand"
                } else {
                    "inAir"
                }
            )
            append("]")
        }
    }
}
