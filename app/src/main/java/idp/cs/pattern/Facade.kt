package idp.cs.pattern

fun main() {
    val facade = Facade()
    val music = facade.generateMusicFile()
    println(music.data)
}

class Facade {
    fun generateMusicFile(): File {
        println("Generate music...")
        return MusicWriter().getMusic()
    }
}

data class File(
    val name: String = "FileName",
    var data: Any? = null,
)

class FileProvider {
    fun provideFile(): File {
        println("Create File")
        return File()
    }
}

class FileEditor(private val file: File) {
    fun writeData(data: Any): File {
        println("Write music data into File...")
        return file.copy(data = data)
    }
}

class MusicWriter {
    private val fileProvider = FileProvider()

    fun getMusic(): File {
        println("Create music File...")
        return FileEditor(fileProvider.provideFile()).writeData("Music")
    }
}
