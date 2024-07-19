package idp.cs.pattern

fun main() {
    val editor = Editor("Hello World! ")
    PasteCommand(editor, "I'm alive!").execute()
    DeleteCommand(editor, 5).execute()
    UndoCommand(editor).execute()
}

abstract class Command(
    protected val editor: Editor,
) {
    abstract fun execute()
}

class UndoCommand(
    editor: Editor,
) : Command(editor) {
    override fun execute() = editor.undo()
}

class PasteCommand(
    editor: Editor,
    private val value: String,
) : Command(editor) {
    override fun execute() = editor.add(value)
}

class DeleteCommand(
    editor: Editor,
    private val value: Int,
) : Command(editor) {
    override fun execute() = editor.remove(value)
}

class Editor(private val init: String) {
    private var currentString = init
    private var history = mutableListOf(init)

    fun add(value: String) {
        history.add(currentString)

        currentString =
            buildString {
                append(currentString)
                append(value)
            }
        log()
    }

    fun remove(value: Int) {
        history.add(currentString)
        
        currentString = currentString.dropLast(value)
        log()
    }

    fun undo() {
        currentString = history.removeLastOrNull() ?: init
        log()
    }

    private fun log() {
        println("Current string $currentString")
    }
}
