package idp.cs.pattern

fun main() {
    with (Proxy()) {
        download("A")
        download("A")
        println(cacheSize() == 1)
    }
}

class Proxy : Downloader {
    private val cache: MutableMap<String, File> = mutableMapOf()
    private val fileDownloader = FileDownloader()

    override fun download(link: String): File {
        val file = cache[link]
        return if (file == null) {
            fileDownloader.download(link).also { cache[link] = it }
        } else {
            println("Retrieve file from cache: $link")
            file
        }
    }

    fun cacheSize() = cache.size
}

interface Downloader {
    fun download(link: String): File
}

class FileDownloader : Downloader {
    override fun download(link: String): File {
        println("Download file from link: $link")
        return FileProvider().provideFile().apply { data = link }
    }
}
