package idp.cs.pattern

fun main() {
    val weather = Weather()
    val cleaner = Cleaner()

    val adapter = Adapter(cleaner)

    weather.countModules()
//    cleaner.countModules()
    adapter.countModules()

    weather.parallelBuild()
//    cleaner.parallelBuild()
    adapter.parallelBuild()
}

class Adapter(private val library: Library): MultiModule {
    override fun parallelBuild() {
        println("Building library $library...")
        if (library.checkIntegrity()) {
            library.build()
        } else {
            println("Library corrupted")
        }
    }

    override fun countModules(): Int {
        return 1
    }
}

interface MultiModule {
    fun parallelBuild()
    fun countModules(): Int
}

class Weather : MultiModule {
    override fun parallelBuild() {
        println("Building parallel Weather...")
        println("Built 5 modules")
    }

    override fun countModules(): Int {
        return 5
    }
}

interface Library {
    fun build()
    fun checkIntegrity(): Boolean
}

class Cleaner : Library {
    override fun build() {
        println("Building Cleaner...")
        println("Built")
    }

    override fun checkIntegrity(): Boolean = true
}
