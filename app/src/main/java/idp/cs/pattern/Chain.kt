package idp.cs.pattern

fun main() {
    Chain().proceed()
}

class Chain {
    private var mainWork = "[Work data]"

    private val chain =
        listOf(
            BaseHandler {
                buildString {
                    append("[Start Edit]")
                    append(it)
                }
            },
            BaseHandler { it }, // skip
            BaseHandler {
                buildString {
                    append(it)
                    append("[End Edit]")
                }
            }
        )

    fun proceed() {
        println("Start with data $mainWork")
        chain.forEach { mainWork = it.proceedString(mainWork) }
        println("Finish with data $mainWork")
    }
}

interface Handler {
    fun proceedString(value: String): String
}

class BaseHandler(
    val action: (String) -> String,
) : Handler {
    override fun proceedString(value: String): String {
        return action(value)
    }
}
