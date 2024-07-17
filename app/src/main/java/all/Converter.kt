package all

fun main() {
    var text = "foo\\nbar\\u{1025}"

    text = text.replace("\\n", "\n")
    text = text.replace("\\u{1025}", "\u1025")

    println(text)
}