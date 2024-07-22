package idp.cs.pattern

fun main() {
    val initBottle = Bottle("Bon", 3, "Akva")
    println("Init bottle $initBottle")

    val snapshot: Memento = initBottle.createSnapshot()

    with(initBottle) {
        setName("Kar")
        setSize(1)
        setCompany("Patska")
    }
    println("Changed bottle $initBottle")

    snapshot.restore()
    println("Restored bottle $initBottle")
}

class Memento(
    private val bottle: Bottle,
    private val name: String,
    private val size: Int,
    private val company: String,
) {
    fun restore() {
        with(bottle) {
            setName(name)
            setSize(size)
            setCompany(company)
        }
    }
}

data class Bottle(
    private var name: String,
    private var size: Int,
    private var company: String,
) {
    fun createSnapshot(): Memento = Memento(this, name, size, company)

    fun setName(name: String) {
        this.name = name
    }

    fun setSize(size: Int) {
        this.size = size
    }

    fun setCompany(company: String) {
        this.company = company
    }

    override fun toString(): String {
        return buildString {
            append(name)
            append(" with size ")
            append(size)
            append(" from ")
            append(company)
        }
    }
}
