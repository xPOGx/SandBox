package all

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val text = "Hello worlod"

    val size = text.length

    var lastResult = ""
    var temp = ""
    for (i in 0 until size) {
        if (temp.isNotEmpty() && text[i] == temp[0]) {
            val length = temp.length
            for (j in 0 until length) {
                try {
                    if (temp[j] == text[i + j]) {
                        if (j + 1 == length) {
                            lastResult = temp
                            temp = ""
                        }
                    }
                } catch (_: Exception) {
                    break
                }
            }
        } else {
            temp = temp.plus(text[i])
        }
    }
    println(lastResult)
}