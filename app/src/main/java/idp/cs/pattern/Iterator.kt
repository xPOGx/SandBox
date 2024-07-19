package idp.cs.pattern

fun main() {
    val iterator: Iterator<File> = FileIterator()
    repeat(6) {
        iterator.getNext()
    }
    println(iterator.getCurrent()?.name)
}

interface Iterator<T> {
    fun hasNext(): Boolean
    fun hasPrevious(): Boolean
    fun getNext(): T?
    fun getPrevious(): T?
    fun getCurrent(): T?
}

class FileIterator : Iterator<File> {
    private val cache = mutableListOf<File>()
    private var currentIndex: Int = 0
    private var size: Int = 0

    init {
        repeat(10) {
            cache.add(File(it.toString()))
            size += 1
        }
    }

    override fun hasNext(): Boolean {
        return currentIndex + 1 < size
    }

    override fun hasPrevious(): Boolean {
        return currentIndex - 1 >= 0
    }

    override fun getNext(): File? {
        return if (hasNext()) {
            cache[++currentIndex]
        } else null
    }

    override fun getPrevious(): File? {
        return if (hasPrevious()) {
            cache[--currentIndex]
        } else null
    }

    override fun getCurrent(): File {
        return cache[currentIndex]
    }
}