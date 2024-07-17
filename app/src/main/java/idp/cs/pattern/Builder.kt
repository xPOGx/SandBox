package idp.cs.pattern

fun main() {
    val stockTable = Table.Builder.build()
    println(stockTable)
    val customTable = Table.Builder
        .setTime(1000)
        .setLegs(10)
        .setMaterial("Meat")
        .build()
    println(customTable)
}

class Table private constructor() {
    private var time: Long? = null

    private var legs: Int? = null

    private var material: String? = null

    private constructor(
        time: Long,
        legs: Int,
        material: String,
    ) : this() {
        this.time = time
        this.legs = legs
        this.material = material
    }

    object Builder {
        private var time: Long = System.currentTimeMillis()
        private var legs: Int = 4
        private var material: String = "Wood"

        fun setTime(newTime: Long): Builder {
            time = newTime
            return this
        }

        fun setLegs(newLegs: Int): Builder {
            legs = newLegs
            return this
        }

        fun setMaterial(newMaterial: String): Builder {
            material = newMaterial
            return this
        }

        fun build(): Table =
            Table(
                time = time,
                legs = legs,
                material = material,
            )
    }

    override fun toString(): String {
        return "Table: created in $time, with $legs legs, from $material"
    }
}