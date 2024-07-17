package idp.cs.pattern

fun main() {
    val human = Human()
    val humanClone = human.clone()
    println(human == humanClone)
    val list = setOf(human, humanClone)
    println(list.size)
}

abstract class Prototype() : Cloneable {
    var rightLeg: Boolean = true
    var leftLeg: Boolean = true

    var rightArm: Boolean = true
    var leftArm: Boolean = true

    constructor(prototype: Prototype) : this() {
        this.rightLeg = prototype.rightLeg
        this.leftLeg = prototype.leftLeg
        this.rightArm = prototype.rightArm
        this.leftArm = prototype.leftArm
    }

    public abstract override fun clone(): Prototype
}

class Human : Prototype {
    private var age: Int = 25
    var name: String = "Asad"

    constructor() : super()

    constructor(human: Human) : super(human) {
        this.age = human.age
        this.name = human.name
    }

    override fun clone() = Human(this)

    override fun hashCode(): Int {
        return 31 * this.age.hashCode() * this.name.hashCode() *
                this.leftArm.hashCode() * this.rightArm.hashCode() *
                this.leftLeg.hashCode() * this.rightLeg.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val human: Human? = other as? Human
        human ?: return false
        return this.leftArm == human.leftArm && this.leftLeg == human.leftLeg
                && this.rightLeg == human.rightLeg && this.rightArm == human.rightArm
                && this.age == human.age && this.name == human.name
                && this.hashCode() == human.hashCode()
    }
}