package idp.cs.pattern

fun main() {
    val fo = Singleton
    fo.field = "Hello"

    println(Singleton.field == "Hello")

    val foo = SingletonClass.init()
    foo.changeFiled("Hello")

    val bar = SingletonClass.init()
    println(bar.getField() == "Hello")
}

object Singleton {
    var field = "Init"
}

class SingletonClass private constructor() {
    private var field = "Init"

    fun changeFiled(newField: String) {
        field = newField
    }

    fun getField() = field

    companion object {
        @Volatile
        private var instance: SingletonClass? = null

        fun init(): SingletonClass {
            return instance ?: synchronized(this) {
                val foo = SingletonClass()
                instance = foo
                foo
            }
        }
    }
}
