package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> = runCatching {
        val file = File(fileName)
        if (!file.exists()) {
            throw IOException("File does not exist or is not a valid file $fileName")
        }
        file.readText().uppercase()
    }

    fun processFileContent(fileName: String): String = readFile(fileName)
        .onSuccess { return "Uppercase content:\n$it" }
        .getOrElse { "Error: Cannot read file - ${it.message}" }
}
